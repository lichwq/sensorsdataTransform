package com.baixing.bi.bolts;

import com.baixing.bi.mapping.City;
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

import java.util.Map;

/**
 * Created by zjl on 2017/5/24.
 * Gary城市映射
 */
public class  CityMapping extends BaseRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(CityMapping.class);
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
        Gary gary = (Gary) input.getValue(0);
        String cityNameEn = gary.getField("city_name_en");
        if (cityNameEn != null) {
            gary.put("city_name_cn", city.getFiled(cityNameEn, "cityNameCn"));
            gary.put("sheng_name_cn", city.getFiled(cityNameEn, "shengNameCn"));
        }

        collector.emit(input, new Values(gary));
        collector.ack(input);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("garyAddCity"));
    }

}
