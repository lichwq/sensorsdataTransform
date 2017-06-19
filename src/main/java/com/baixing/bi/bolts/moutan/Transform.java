package com.baixing.bi.bolts.moutan;

import com.baixing.bi.event.Event;
import com.baixing.bi.event.SensorInput;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.baixing.bi.event.Constant.SENSOR_DATA_PROJECT;

/**
 * Created by zjl on 2017/6/12.
 * 根据业务的需要
 * 将event 转化为 神策所需要的字段
 *
 */
public class Transform extends BaseRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(Transform.class);
    private OutputCollector collector;
    /**
     * The Senseor keep filed.
     */
    private static HashMap<String, String> senseorKeepFiled;
    private static HashMap<String, String> covertType;
    private static String project = "";


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


    /**
     * 获取神策的保留字段，具体见神策的官方文档。
     *
     */
    private void prepareSensorKeepFiled() {
        senseorKeepFiled = new HashMap<String, String>();
        senseorKeepFiled.put("url", "$url");
        senseorKeepFiled.put("ip", "$ip");
        senseorKeepFiled.put("ua", "$user_agent");
    }


    /**
     * 按照业务需要，将一部分数据转换为number，将一部分数据转换为字符串
     */

    private void covertType() {
        covertType = new HashMap<String, String>();
        covertType.put("days", "number");
        covertType.put("pageNum", "number");
        covertType.put("price", "number");
        covertType.put("quotaRemain", "number");
        covertType.put("isPV", "string");
        covertType.put("isAjax", "string");
        covertType.put("isEdit", "string");
        covertType.put("orderId", "string");
        covertType.put("errorCode", "string");
    }


    /**
     * 转化为神策的所需的字段
     *
     * @param event the event
     * @return the sensor properties
     */
    public Map<Object, Object> getSensorProperties(Event event) {
        Map<Object, Object> properties = new HashMap<Object, Object>();
        HashMap<Object, Object> msg = (HashMap<Object, Object>) event.getMsg();
        for (Object o : msg.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            Object key = entry.getKey();
            Object val = entry.getValue();
            // 神策的保留字段
            if (senseorKeepFiled.containsKey(key)) {
                properties.put(senseorKeepFiled.get(key), val);
            } else {
                properties.put(key, val);
            }

            // 根据业务需要转换的字段
            if (covertType.containsKey(key) && val != null) {

                String type = covertType.get(key);
                if (type.equals("number")) {
                    properties.put(key, new BigDecimal(val.toString()));
                } else if (type.equals("string")) {
                    properties.put(key, val.toString());
                }
            }

        }

        return properties;
    }

    /**
     * 转化数据
     * {
     * "distinct_id": "2b0a6f51a3cd6775",
     * "time": 1434556935000,
     * "type": "track",
     * "event": "ViewProduct",
     * "project": "ebiz_test",
     * "time_free": true,
     * "properties": {
     * "$app_version":"1.3",
     * "$wifi":true,
     * "$ip":"180.79.35.65",
     * "$province":"湖南",
     * "$city":"长沙",
     * "$user_agent":"Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_2 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) CriOS/58.0.3029.113 Mobile/14F89 Safari/602.1",
     * "$screen_width":320,
     * "$screen_height":640,
     * "product_id":12345,
     * "product_name":"苹果",
     * "product_classify":"水果",
     * "product_price":14.0
     * }
     * }
     *
     * @param event the event
     * @return the sensor input
     */
    public SensorInput transform(Event event) {
        SensorInput sensorInput  = new SensorInput();
        sensorInput.setDistinct_id(getDistinctId(event));
        sensorInput.setTime(event.getTs());
        sensorInput.setType("track");
        sensorInput.setEvent(event.getField("action") != null ? event.getField("action").toString() : "");
        // 测试项目对应的是 default
        sensorInput.setProject(project);
        sensorInput.setTime_free(true);
        Map<Object, Object> properties = getSensorProperties(event);
        sensorInput.setProperties(properties);
        return sensorInput;
    }

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        project = stormConf.get(SENSOR_DATA_PROJECT).toString();
        prepareSensorKeepFiled();
        covertType();

    }

    public void execute(Tuple input) {
        Event event = (Event) input.getValue(0);
        SensorInput sensorInput = transform(event);
        collector.emit(input, new Values(sensorInput));
        collector.ack(input);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("moutanTransformToSensor"));
    }
}
