package com.baixing.bi.bolts.moutan;

import com.baixing.bi.format.Event;
import com.baixing.bi.format.EventDataFormat;
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
 * Created by zjl on 2017/7/4.
 * 检测从主站打点过来的数据是不是完整，如果不完整就抛弃
 */
public class FirstFieldCheck extends BaseRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(BaseRichBolt.class);
    private OutputCollector collector;
    private EventDataFormat eventDataFormat;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        eventDataFormat = new EventDataFormat();
        eventDataFormat.loadConfig(stormConf.get(EVENT_DATA_FORMAT_FILE).toString());

    }
    public void execute(Tuple input) {
        Event event = (Event) input.getValue(0);
        if (eventDataFormat.checkData(event,true)) {
            collector.emit(input, new Values(event));
        }
        collector.ack(input);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("firstFieldCheck"));
    }
}

