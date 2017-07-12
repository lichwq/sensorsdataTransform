package com.baixing.bi.bolts.common;

import com.baixing.bi.format.Event;
import com.baixing.bi.format.EventWhiteList;
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

import static com.baixing.bi.format.Constant.EVENT_DATA_FORMAT_FILE;

/**
 * Created by zjl on 2017/7/3.
 */
public class EventTypeFilter extends BaseRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(EventTypeFilter.class);
    private OutputCollector collector;
    private EventWhiteList eventWhiteList;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        this.eventWhiteList = new EventWhiteList();
        eventWhiteList.loadConfig(stormConf.get(EVENT_DATA_FORMAT_FILE).toString());
    }

    public void execute(Tuple input) {
        Event event = (Event) input.getValue(0);

        if (eventWhiteList.isInWhilteList(event.getType())) {
//            LOG.info("GaryTypeFilter: " + event.toString());
            collector.emit(input, new Values(event));
        }
        collector.ack(input);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("eventTypeFilter"));
    }
}
