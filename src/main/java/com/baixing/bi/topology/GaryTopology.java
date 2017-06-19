package com.baixing.bi.topology;

import com.baixing.bi.bolts.CityMapping;
import com.baixing.bi.bolts.GaryFormat;
import com.baixing.bi.bolts.UrlTypeIdMapping;
import com.baixing.bi.bolts.mapping.GaryToKafkaMapper;
import com.baixing.bi.event.Gary;
import com.baixing.bi.event.UrlTypeId;
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

/**
 * Created by zjl on 2017/5/24.
 */
public class GaryTopology {

    public static void main(String[] args) throws InterruptedException, InvalidTopologyException, AuthorizationException,
            AlreadyAliveException {
        String bootstrapServers = "sha2hb06:9092,sha2hb07:9092";
        String topic = "gary";

        KafkaSpoutConfig<String, String> kafkaSpoutConfig = KafkaSpoutConfig.builder(bootstrapServers, topic)
                .setGroupId("gary_storm_test")
                .setFirstPollOffsetStrategy(KafkaSpoutConfig.FirstPollOffsetStrategy.UNCOMMITTED_LATEST)
                .build();

        //set producer properties.
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("acks", "1");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaBolt kafkaBolt = new KafkaBolt()
                .withProducerProperties(props)
                .withTopicSelector(new DefaultTopicSelector("test"))
                .withTupleToKafkaMapper(new GaryToKafkaMapper());

        final TopologyBuilder tp = new TopologyBuilder();
        tp.setSpout("gary_spout_test", new KafkaSpout<String, String>(kafkaSpoutConfig));
        tp.setBolt("gary_format", new GaryFormat()).shuffleGrouping("gary_spout_test");
        tp.setBolt("gary_add_city", new CityMapping()).shuffleGrouping("gary_format");
        tp.setBolt("gary_add_url_type_id", new UrlTypeIdMapping()).shuffleGrouping("gary_add_city");
//        tp.setBolt("gary_print", new GaryPrint()).shuffleGrouping("gary_add_url_type_id");
        tp.setBolt("forward_to_kafka", kafkaBolt).shuffleGrouping("gary_add_url_type_id");



        //Configuration
        Config conf = new Config();
        conf.setDebug(false);
        conf.setNumWorkers(5);
        conf.setMaxSpoutPending(5000);
//        conf.put("urlTypeIdFile", "/home/deploy/storm_conf/url_type_regex.conf");
//        conf.put("cityMappingFile", "/home/deploy/storm_conf/city_format.csv");
        conf.put("urlTypeIdFile", "/Users/zjl/Downloads/url_type_regex.conf");
        conf.put("cityMappingFile", "/Users/zjl/Downloads/city_format.csv");
        conf.registerSerialization(Gary.class);
        conf.registerSerialization(UrlTypeId.class);
//        StormSubmitter.submitTopology("mytopology", conf, tp.createTopology());

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("Getting-Started-Toplogie", conf, tp.createTopology());
        Thread.sleep(100000);
        cluster.shutdown();

    }

}
