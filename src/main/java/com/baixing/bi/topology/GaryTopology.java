package com.baixing.bi.topology;

import com.baixing.bi.bolts.common.ChangeType;
import com.baixing.bi.bolts.gary.*;
import com.baixing.bi.bolts.mapping.MoutanToKafkaMapper;
import com.baixing.bi.utils.ProjectProperties;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.bolt.KafkaBolt;
import org.apache.storm.kafka.bolt.selector.DefaultTopicSelector;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;

import java.util.Properties;

import static com.baixing.bi.format.Constant.EVENT_TYPE_ALIAS_FILE;
import static com.baixing.bi.mapping.Constant.SENSOR_DATA_PROJECT;
import static com.baixing.bi.mapping.Constant.URL_TYPE_ID_FILE;
import static org.apache.storm.StormSubmitter.submitTopology;

/**
 * Created by zjl on 2017/5/24.
 */
public class GaryTopology {

    public static void main(String[] args) throws InterruptedException, InvalidTopologyException, AuthorizationException,
            AlreadyAliveException {

        ProjectProperties projectProperties = new ProjectProperties();
        projectProperties.loadProperties();

        String mode =  projectProperties.getProperty("mode");
        String bootstrapServers = projectProperties.getProperty("kafkaBootstrapServers");
        String[] topic = projectProperties.getProperty("kafkaConsumerTopic").split(",");
        String consumerGroupId = projectProperties.getProperty("kafkaConsumerGroupId");
        KafkaSpoutConfig<String, String> kafkaSpoutConfig = KafkaSpoutConfig.builder(bootstrapServers, topic)
                .setGroupId(consumerGroupId)
                .setFirstPollOffsetStrategy(KafkaSpoutConfig.FirstPollOffsetStrategy.UNCOMMITTED_LATEST)
                .build();

        //set producer properties.
        final TopologyBuilder tp = new TopologyBuilder();
        tp.setSpout("gary_spout", new KafkaSpout<String, String>(kafkaSpoutConfig));
        tp.setBolt("gary_format", new GaryFormat()).shuffleGrouping("gary_spout");
        tp.setBolt("gary_add_url_type_id", new UrlTypeIdMapping()).shuffleGrouping("gary_format");
        tp.setBolt("change_type", new ChangeType()).shuffleGrouping("gary_add_url_type_id");
        tp.setBolt("type_filter", new GaryTypeFilter()).shuffleGrouping("change_type");
        tp.setBolt("sensor_keep_field", new GarySensorKeepField()).shuffleGrouping("type_filter");
        tp.setBolt("transform", new GaryTransform()).shuffleGrouping("sensor_keep_field");

        if (mode.equals("dev")) {
            tp.setBolt("gary_print", new GaryPrint()).shuffleGrouping("transform");

        } else if (mode.equals("online") || mode.equals("test")) {
            Properties props = new Properties();
            props.put("bootstrap.servers", bootstrapServers);
            props.put("acks", "1");
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            KafkaBolt kafkaBolt = new KafkaBolt()
                    .withProducerProperties(props)
                    .withTopicSelector(new DefaultTopicSelector(projectProperties.getProperty("kafkaProducerTopic")))
                    .withTupleToKafkaMapper(new MoutanToKafkaMapper());
            tp.setBolt("send_to_kafka", kafkaBolt).shuffleGrouping("transform");
        }

        //Configuration
        Config conf = new Config();
        conf.setDebug(false);
        conf.setNumWorkers(5);
        conf.setMaxSpoutPending(5000);
        conf.put(URL_TYPE_ID_FILE, projectProperties.getProperty("urlTypeIdFile"));
        conf.put(SENSOR_DATA_PROJECT,  projectProperties.getProperty("sensorDataProject"));
//        conf.put(EVENT_DATA_FORMAT_FILE, projectProperties.getProperty("eventDataFormatFile"));
        conf.put(EVENT_TYPE_ALIAS_FILE,  projectProperties.getProperty("eventTypeAliasFile"));
//        conf.put(EVENT_FIELD_ALIAS_FILE, projectProperties.getProperty("eventFieldAliasFile"));


        if (mode.equals("dev") || mode.equals("test")) {
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology(projectProperties.getProperty("stormTopologyName"), conf, tp.createTopology());
            Thread.sleep(1000000);
            cluster.shutdown();

        } else if (mode.equals("online")) {
            submitTopology(projectProperties.getProperty("stormTopologyName"), conf, tp.createTopology());
        }

    }

}
