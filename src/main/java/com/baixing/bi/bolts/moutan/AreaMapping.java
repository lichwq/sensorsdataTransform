package com.baixing.bi.bolts.moutan;

import com.baixing.bi.mapping.Area;
import com.baixing.bi.format.Event;
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

import static com.baixing.bi.mapping.Constant.*;

/**
 * Created by zjl on 2017/6/12.
 * 地区映射
 */

public class AreaMapping extends BaseRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(AreaMapping.class);
    private OutputCollector collector;
    private static Area area;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        area = new Area();
        area.loadConfigFile((String) stormConf.get(AREA_MAPPING_FILE));
    }

    /**
     *
     * 通过 area id 来获取其它字段
     * */
    public void execute(Tuple input) {
        Event event = (Event) input.getValue(0);
        try {
            String areaId = (String)event.getField("areaId");
            if (null != areaId) {
                event.put(AREA_CN, area.getFiled(areaId, AREA_CN));
                event.put(CITY_CN, area.getFiled(areaId, CITY_CN));
                event.put(PROVINCE_CN, area.getFiled(areaId, PROVINCE_CN));
            }
        } catch (Exception e) {
            String err = String.format("get area error, json: %s, err: %s", event.toString(), e.getMessage());
            LOG.error(err);
        }
//        LOG.info("AreaMapping " + event.toString());
        collector.emit(input, new Values(event));
        collector.ack(input);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("moutanAddArea"));
    }

}
