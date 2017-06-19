package com.baixing.bi.bolts.moutan;

import com.baixing.bi.event.SensorInput;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by zjl on 2017/6/12.
 */
public class MoutanPrint extends BaseRichBolt {


    private static final Logger LOG = LoggerFactory.getLogger(MoutanPrint.class);
    private OutputCollector collector;

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    public void execute(Tuple input) {
        SensorInput sensorInput= (SensorInput) input.getValue(0);
        LOG.info(sensorInput.toJson());
        collector.ack(input);
    }
}
