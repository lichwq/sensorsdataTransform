package com.baixing.bi.topology;

import com.baixing.bi.bolts.common.*;
import com.baixing.bi.bolts.event.EventFormat;
import com.baixing.bi.bolts.mapping.MoutanToKafkaMapper;
import com.baixing.bi.bolts.moutan.*;
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

import static com.baixing.bi.format.Constant.*;
import static com.baixing.bi.mapping.Constant.*;
import static org.apache.storm.StormSubmitter.submitTopology;

/**
 * Created by zjl on 2017/6/12.
 */
public class MoutanTopology {

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
        tp.setSpout("moutan_storm", new KafkaSpout<String, String>(kafkaSpoutConfig));
        tp.setBolt("event_format", new EventFormat()).shuffleGrouping("moutan_storm");
        tp.setBolt("moutan_filter", new Filter()).shuffleGrouping("event_format");
        tp.setBolt("change_type", new ChangeType()).shuffleGrouping("moutan_filter");
        tp.setBolt("event_type_filter", new EventTypeFilter()).shuffleGrouping("change_type");
        tp.setBolt("change_evnet_field", new ChangeEventField()).shuffleGrouping("event_type_filter");
        tp.setBolt("add_sensor_keep_field", new SensorKeepField()).shuffleGrouping("change_evnet_field");
        tp.setBolt("first_field_check", new FirstFieldCheck()).shuffleGrouping("add_sensor_keep_field");
        tp.setBolt("area_mapping", new AreaMapping()).shuffleGrouping("first_field_check");
        tp.setBolt("category_mapping", new CategoryMapping()).shuffleGrouping("area_mapping");
        tp.setBolt("second_field_check", new SecondFieldCheck()).shuffleGrouping("category_mapping");
        tp.setBolt("moutan_transform", new Transform()).shuffleGrouping("second_field_check");

        if (mode.equals("dev")) {
            tp.setBolt("moutan_print", new MoutanPrint()).shuffleGrouping("moutan_transform");

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
            tp.setBolt("send_to_kafka", kafkaBolt).shuffleGrouping("moutan_transform");
        }

        //Configuration
        Config conf = new Config();
        conf.setDebug(false);
        conf.setNumWorkers(5);
        conf.setMaxSpoutPending(5000);
        conf.put(MOUTAN_THRIFT_URL, projectProperties.getProperty("thriftUrl"));
        conf.put(AREA_MAPPING_FILE,  projectProperties.getProperty("areMappingFile"));
        conf.put(SENSOR_DATA_PROJECT,  projectProperties.getProperty("sensorDataProject"));
        conf.put(EVENT_DATA_FORMAT_FILE, projectProperties.getProperty("eventDataFormatFile"));
        conf.put(EVENT_TYPE_ALIAS_FILE,  projectProperties.getProperty("eventTypeAliasFile"));
        conf.put(EVENT_FIELD_ALIAS_FILE, projectProperties.getProperty("eventFieldAliasFile"));
        if (mode.equals("dev") || mode.equals("test")) {

            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology(projectProperties.getProperty("stormTopologyName"), conf, tp.createTopology());
            Thread.sleep(10000000);
            cluster.shutdown();

        } else if (mode.equals("online")) {

            submitTopology(projectProperties.getProperty("stormTopologyName"), conf, tp.createTopology());
        }

    }

}