package com.baixing.bi.bolts.gary;

import com.baixing.bi.format.Gary;
import com.baixing.bi.mapping.UrlTypeId;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

import static com.baixing.bi.mapping.Constant.URL_TYPE_ID_FILE;

/**
 * Created by zjl on 2017/5/25.
 * UrlType的映射
 */
public class UrlTypeIdMapping implements IRichBolt {

    private static final Logger LOG = LoggerFactory.getLogger(UrlTypeIdMapping.class);
    private OutputCollector collector;
    private UrlTypeId urlTypeId;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        urlTypeId = new UrlTypeId();

        String fileName = stormConf.get(URL_TYPE_ID_FILE).toString();
        try {
            urlTypeId.loadConfigFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void execute(Tuple input) {
        Gary gary = (Gary) input.getValue(0);

        String toUrl = gary.getField("to_url").toString();
        if (null != toUrl) {
            gary.put("to_url_type_id", urlTypeId.getType(toUrl));
        }

        String fromUrl = gary.getField("from_url").toString();
        if (null != fromUrl) {
            gary.put("from_url_type_id", urlTypeId.getType(fromUrl));
        }
        collector.emit(input, new Values(gary));
        collector.ack(input);

    }

    public void cleanup() {

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("garyAddUrlTypeId"));
    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
