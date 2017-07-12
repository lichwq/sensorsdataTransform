package com.baixing.bi.bolts.gary;

import com.baixing.bi.format.Event;
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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zjl on 2017/7/5.
 * 后期抽象一下
 */
public class GarySensorKeepField extends BaseRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(GarySensorKeepField.class);
    private OutputCollector collector;

    private static HashMap<String, String> senseorKeepFiled;
    /**
     * Gets distinct id, 如果有bxUserId 使用bxUserId，如果没有，使用trackId
     *
     * @param event the event
     * @return the distinct id
     */
    public String getDistinctId(Event event) {

        Object res =  event.getField("user_id").equals("0") ? event.getField("track_id") :
                event.getField("user_id");

        return (String)res;
    }

    public void addSensorKeepField(Event event) {
        HashMap<Object, Object> msg = (HashMap<Object, Object>) event.getMsg();
        HashMap<Object, Object> add = new HashMap<Object, Object>();
        for (Iterator<Map.Entry<Object, Object>> iterator = msg.entrySet().iterator(); iterator.hasNext();) {
            final Map.Entry<Object, Object> entry = iterator.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            if (senseorKeepFiled.containsKey(key)) {
                Object newKey = senseorKeepFiled.get(key);
                add.put(newKey, val);
                iterator.remove();
            }
        }
        msg.putAll(add);
        msg.put("distinct_id", getDistinctId(event));
    }

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        this.senseorKeepFiled = new HashMap<String, String>();
        this.senseorKeepFiled.put("url", "$url");
        this.senseorKeepFiled.put("ip", "$ip");
        this.senseorKeepFiled.put("user_agent", "$user_agent");

    }
    public void execute(Tuple input) {
        Gary gary = (Gary) input.getValue(0);
        addSensorKeepField(gary);
        collector.emit(input, new Values(gary));
        collector.ack(input);

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("sensorKeepField"));
    }
}
