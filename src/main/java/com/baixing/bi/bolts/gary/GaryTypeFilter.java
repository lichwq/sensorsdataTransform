package com.baixing.bi.bolts.gary;

import com.baixing.bi.format.Gary;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by zjl on 2017/7/11.
 */
public class GaryTypeFilter extends BaseRichBolt {

    private static final Logger LOG = LoggerFactory.getLogger(GaryTypeFilter.class);
    private OutputCollector collector;
    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;

    }

    @Override
    public void execute(Tuple input) {
        Gary gary = (Gary) input.getValue(0);
        if (gary.getType().equals("viewAd") && gary.getField("platform").equals("wap")) {
            collector.emit(input, new Values(gary));
        }
        collector.ack(input);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
            declarer.declare(new Fields("garyEventTypeFilter"));
    }
}
