package com.baixing.bi.bolts.moutan;

import com.baixing.bi.event.Event;
import com.baixing.bi.event.Sites;
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

import static com.baixing.bi.event.Constant.MOUTAN_THRIFT_URL;
import static com.baixing.bi.event.Constant.SITE_TITLE;

/**
 * Created by zjl on 2017/6/14.
 * moutan的站名映射。通过SiteID获取其它信息
 */
public class SiteMapping implements IRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(SiteMapping.class);
    private OutputCollector collector;
    private Sites sites;


    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        sites = new Sites(stormConf.get(MOUTAN_THRIFT_URL).toString());
        sites.init();
    }

    public void execute(Tuple input) {
        Event event = (Event) input.getValue(0);
        String siteId = (String)event.getField("siteId");
        if (siteId != null) {
            event.put(SITE_TITLE, sites.getSiteTitle(siteId));
        } else {
            LOG.warn("siteId is null " + event.toString());
        }

        collector.emit(input, new Values(event));
        collector.ack(input);
    }

    public void cleanup() {
        sites.close();
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("moutanAddSiteInfo"));
    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
