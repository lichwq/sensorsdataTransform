package com.baixing.bi.bolts.moutan;

import com.baixing.bi.event.Event;
import com.baixing.bi.event.MoutanCategory;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.baixing.bi.event.Constant.CATEGORY_CN;
import static com.baixing.bi.event.Constant.MOUTAN_THRIFT_URL;

/**
 * Created by zjl on 2017/6/12.
 * 困意兔八哥色鬼
 */
public class CategoryMapping implements IRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(CategoryMapping.class);
    private OutputCollector collector;
    private MoutanCategory moutanCategory;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        moutanCategory = new MoutanCategory((String)stormConf.get(MOUTAN_THRIFT_URL));
        moutanCategory.init();

    }


    public void execute(Tuple input) {
        Event event = (Event) input.getValue(0);
        try {
            String categoryId = (String)event.getField("categoryId");
            if (null != categoryId) {
                String categoryCn = moutanCategory.getCategoryCn(categoryId);
                if (null != categoryCn) {
                    event.put(CATEGORY_CN, categoryCn);
                }

            }
        } catch(Exception e) {
            String err = String.format("get category error, json: %s, err: %s", event.toString(), e.getMessage());
            LOG.error(err);
        }

        collector.emit(input, new Values(event));
        collector.ack(input);
    }


    public void cleanup() {
        moutanCategory.close();
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("moutanAddCategory"));
    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

}

