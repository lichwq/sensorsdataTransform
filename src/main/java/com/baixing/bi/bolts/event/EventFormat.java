package com.baixing.bi.bolts.event;

import com.baixing.bi.format.Event;
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
 * Created by zjl on 2017/5/31.
 */
public class EventFormat extends BaseRichBolt {

    private static final Logger LOG = LoggerFactory.getLogger(EventFormat.class);
    private OutputCollector collector;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    /**
     * 将json转化为Event对象
     * 使用 DefaultRecordTranslator
     * 默认返回的
     * 0->topic
     * 1->partition
     * 2->offset
     * 3->key
     * 4->value
     * */
    public void execute(Tuple input) {
        String line = input.getString(4);
        Event event = Event.fromJson(line);
        if (null != event) {
            collector.emit(input, new Values(event));
        }
        collector.ack(input);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("eventFormat"));
    }
}
