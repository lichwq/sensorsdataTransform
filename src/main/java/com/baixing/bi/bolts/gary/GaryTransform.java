package com.baixing.bi.bolts.gary;

import com.baixing.bi.format.Event;
import com.baixing.bi.format.Gary;
import com.baixing.bi.format.SensorInput;
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

import static com.baixing.bi.mapping.Constant.SENSOR_DATA_PROJECT;

/**
 * Created by zjl on 2017/6/12.
 * 根据业务的需要
 * 将event 转化为 神策所需要的字段
 *
 */
public class GaryTransform extends BaseRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(GaryTransform.class);
    private OutputCollector collector;

    private static String project = "";

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
        sensorInput.setDistinct_id(event.getField("distinct_id").toString());
        sensorInput.setTime(event.getTs());
        sensorInput.setType("track");
        sensorInput.setEvent(event.getType());
        // 测试项目对应的是 default
        sensorInput.setProject(project);
        sensorInput.setTime_free(true);
        Map<Object, Object> properties = event.getMsg();
        properties.remove("distinct_id");
        sensorInput.setProperties(properties);
        return sensorInput;
    }

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        project = stormConf.get(SENSOR_DATA_PROJECT).toString();

    }

    public void execute(Tuple input) {
        Gary gary = (Gary) input.getValue(0);
//        LOG.info("before GaryTransform: " + gary.toString());
        SensorInput sensorInput = transform(gary);
//        LOG.info("after GaryTransform: " + gary.toString());
        collector.emit(input, new Values(sensorInput));
        collector.ack(input);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("garyTransformToSensor"));
    }
}
