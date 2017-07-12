package com.baixing.bi.bolts.gary;

import com.baixing.bi.format.SensorInput;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by zjl on 2017/5/24.
 * 主要做测试使用
 */
public class GaryPrint extends BaseRichBolt {

    private static final Logger LOG = LoggerFactory.getLogger(GaryPrint.class);
    private OutputCollector collector;

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    public void execute(Tuple input) {
        SensorInput gary = (SensorInput) input.getValue(0);
        LOG.info(gary.toJson());
        collector.ack(input);
    }
}
