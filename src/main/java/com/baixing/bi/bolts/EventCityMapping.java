package com.baixing.bi.bolts;

import com.baixing.bi.event.City;
import com.baixing.bi.event.Event;
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
 * Event 城市映射
 */

public class EventCityMapping extends BaseRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(EventCityMapping.class);
    private OutputCollector collector;
    private static City city;



    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        city = new City();
        city.loadConfigFile((String) stormConf.get("cityMappingFile"));
    }

    /**
     *
     * 通过city_name_en来获取其它字段，但是
     * */
    public void execute(Tuple input) {
        Event event = (Event) input.getValue(0);
        String cityNameEn = (String) event.getField("_city");
        if (cityNameEn != null) {
            event.put("_cityNameCn", city.getFiled(cityNameEn, "cityNameCn"));
            event.put("_shengNameCn", city.getFiled(cityNameEn, "shengNameCn"));
        }

        collector.emit(input, new Values(event));
        collector.ack(input);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("eventAddCity"));
    }

}