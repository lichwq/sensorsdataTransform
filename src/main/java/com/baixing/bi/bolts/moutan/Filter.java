package com.baixing.bi.bolts.moutan;

import com.baixing.bi.event.Event;
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

import static com.baixing.bi.event.Constant.MOUNTA_PROJECT_NAME;

/**
 * Created by zjl on 2017/5/31.
 * 过滤event 获取moutan 的数据
 */
public class Filter extends BaseRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(Filter.class);
    private OutputCollector collector;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    public void execute(Tuple input) {
        Event event = (Event) input.getValue(0);
        if (MOUNTA_PROJECT_NAME.equals(event.getType())) {
            collector.emit(input, new Values(event));
        }
        collector.ack(input);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("moutanFilter"));
    }
}
