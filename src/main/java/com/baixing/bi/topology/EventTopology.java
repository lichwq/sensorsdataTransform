package com.baixing.bi.topology;

import com.baixing.bi.bolts.EventCityMapping;
import com.baixing.bi.bolts.EventFilter;
import com.baixing.bi.bolts.EventFormat;
import com.baixing.bi.bolts.EventPrint;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;

import java.util.Properties;

/**
 * Created by zjl on 2017/5/31.
 */
public class EventTopology {

    public static void main(String[] args) throws InterruptedException, InvalidTopologyException, AuthorizationException,
            AlreadyAliveException {
        String bootstrapServers = "sha2hb06:9092,sha2hb07:9092";
        String topic = "eventlog_filter";

        KafkaSpoutConfig<String, String> kafkaSpoutConfig = KafkaSpoutConfig.builder(bootstrapServers, topic)
                .setGroupId("event_storm_test")
                .setFirstPollOffsetStrategy(KafkaSpoutConfig.FirstPollOffsetStrategy.UNCOMMITTED_LATEST)
                .build();

        //set producer properties.
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("acks", "1");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

//        KafkaBolt kafkaBolt = new KafkaBolt()
//                .withProducerProperties(props)
//                .withTopicSelector(new DefaultTopicSelector("test"))
//                .withTupleToKafkaMapper(new GaryToKafkaMapper());

        final TopologyBuilder tp = new TopologyBuilder();
        tp.setSpout("event_spout_test", new KafkaSpout<String, String>(kafkaSpoutConfig));
        tp.setBolt("event_format", new EventFormat()).shuffleGrouping("event_spout_test");
        tp.setBolt("event_filter", new EventFilter()).shuffleGrouping("event_format");
        tp.setBolt("event_city_mapping", new EventCityMapping()).shuffleGrouping("event_filter");
        tp.setBolt("event_print", new EventPrint()).shuffleGrouping("event_city_mapping");




        //Configuration
        Config conf = new Config();
        conf.setDebug(false);
        conf.setNumWorkers(5);
        conf.setMaxSpoutPending(5000);
//        conf.put("urlTypeIdFile", "/home/deploy/storm_conf/url_type_regex.conf");
//        conf.put("cityMappingFile", "/home/deploy/storm_conf/city_format.csv");
        conf.put("urlTypeIdFile", "/Users/zjl/Downloads/url_type_regex.conf");
        conf.put("cityMappingFile", "/Users/zjl/Downloads/city_format.csv");
        conf.put("whiteList", "showcontactnumber");


//        conf.registerSerialization(Event.class);

//        StormSubmitter.submitTopology("mytopology", conf, tp.createTopology());

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("Event-Test-Toplogie", conf, tp.createTopology());
        Thread.sleep(100000);
        cluster.shutdown();

    }
}
