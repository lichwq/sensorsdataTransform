package com.baixing.bi.bolts.common;

import com.baixing.bi.format.Event;
import com.baixing.bi.format.EventType;
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

import static com.baixing.bi.format.Constant.EVENT_TYPE_ALIAS_FILE;

/**
 * Created by zjl on 2017/7/3.
 */
public class ChangeType extends BaseRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(ChangeType.class);
    private OutputCollector collector;
    private EventType eventType;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        this.eventType = new EventType();
        eventType.loadConfig(stormConf.get(EVENT_TYPE_ALIAS_FILE).toString());
    }

    public void execute(Tuple input) {
        Event event = (Event) input.getValue(0);
        eventType.changeType(event);
//        LOG.info("after change type: " + event.toString());
        collector.emit(input, new Values(event));
        collector.ack(input);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("changeType"));
    }
}
