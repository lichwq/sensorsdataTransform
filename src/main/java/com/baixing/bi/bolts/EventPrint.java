package com.baixing.bi.bolts;

import com.baixing.bi.event.Event;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by zjl on 2017/5/31.
 * 主要做测试用，在屏幕输出
 */

public class EventPrint extends BaseRichBolt {

    private static final Logger LOG = LoggerFactory.getLogger(EventPrint.class);
    private OutputCollector collector;

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    public void execute(Tuple input) {
        Event event= (Event) input.getValue(0);
        LOG.info(event.toJson());
        collector.ack(input);
    }
}
