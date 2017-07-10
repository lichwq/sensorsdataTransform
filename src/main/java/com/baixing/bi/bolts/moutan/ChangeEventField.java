package com.baixing.bi.bolts.moutan;

import com.baixing.bi.format.Event;
import com.baixing.bi.format.EventField;
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

import static com.baixing.bi.format.Constant.EVENT_FIELD_ALIAS_FILE;

/**
 * Created by zjl on 2017/7/3.
 */
public class ChangeEventField extends BaseRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(ChangeEventField.class);
    private OutputCollector collector;
    private EventField eventField;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        this.eventField = new EventField();
        eventField.loadConfig(stormConf.get(EVENT_FIELD_ALIAS_FILE).toString());
    }

    public void execute(Tuple input) {
        Event event = (Event) input.getValue(0);
        eventField.changeFields(event);
//        LOG.info("ChangeEventField " + event.toString());
        collector.emit(input, new Values(event));
        collector.ack(input);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("changeEventField"));
    }
}
