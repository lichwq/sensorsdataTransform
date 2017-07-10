package com.baixing.bi.bolts.moutan;

import com.baixing.bi.format.Event;
import com.baixing.bi.format.SensorKeepedKey;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjl on 2017/7/5.
 */
public class SensorKeepField extends BaseRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(SensorKeepField.class);
    private OutputCollector collector;
    private SensorKeepedKey sensorKeepedKey;
    /**
     * Gets distinct id, 如果有bxUserId 使用bxUserId，如果没有，使用trackId
     *
     * @param event the event
     * @return the distinct id
     */
    public String getDistinctId(Event event) {
        Object res =  event.getField("bxUserId") != null ?
                event.getField("bxUserId"): event.getField("trackId");
        return (String)res;
    }

    public void addSensorKeepField(Event event) {
        HashMap<Object, Object> msg = (HashMap<Object, Object>) event.getMsg();
        HashMap<Object, Object> add = new HashMap<Object, Object>();
        for (Object o : msg.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            Object key = entry.getKey();
            Object val = entry.getValue();
            // 神策的保留字段
            if (sensorKeepedKey.containsKey(key)) {
                Object newKey = sensorKeepedKey.get(key);
                add.put(newKey, val);
            }
        }

        msg.putAll(add);
        msg.put("distinct_id", getDistinctId(event));
    }


    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        this.sensorKeepedKey = new SensorKeepedKey();

    }
    public void execute(Tuple input) {
        Event event = (Event) input.getValue(0);
        addSensorKeepField(event);
        collector.emit(input, new Values(event));
        collector.ack(input);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("sensorKeepField"));
    }
}
