package com.baixing.bi.bolts;

import com.baixing.bi.event.Event;
import org.apache.commons.lang3.StringUtils;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by zjl on 2017/5/31.
 * Event过滤
 */
public class EventFilter extends BaseRichBolt{
    private static final Logger LOG = LoggerFactory.getLogger(BaseRichBolt.class);
    private OutputCollector collector;
    private Set<String> whiteList;
    private static final String DELIMITER = ",";

    /**
     * 通过白名单做过滤
     */
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        String str = stormConf.get("whiteList").toString();
        String[] arr = str.split(DELIMITER);
        whiteList = new HashSet<String>();
        for (String name: arr) {
            whiteList.add(StringUtils.strip(name));
        }
        LOG.info(whiteList.toString());
        this.collector = collector;
    }

    public void execute(Tuple input) {
        Event event = (Event) input.getValue(0);
        if (whiteList.contains(event.getType())) {
            collector.emit(input, new Values(event));
        }
        collector.ack(input);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("eventFilter"));
    }
}
