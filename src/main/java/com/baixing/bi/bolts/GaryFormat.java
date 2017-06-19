package com.baixing.bi.bolts;

import com.baixing.bi.event.Gary;
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
 * 将 gary 格式化成k,v格式，方便后面进行处理。
 *
 */
public class GaryFormat extends BaseRichBolt {

    private static final Logger LOG = LoggerFactory.getLogger(GaryFormat.class);
    private OutputCollector collector;

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("garyFormat"));
    }

    /*
    * 使用 DefaultRecordTranslator
    * 默认返回的
    * 0->topic
    * 1->partition
    * 2->offset
    * 3->key
    * 4->value
    * */
    public void execute(Tuple input) {
        String line = input.getString(4);
        Gary gary = Gary.fromLine(line);
        if (gary != null) {
            collector.emit(input, new Values(gary));
        }
        collector.ack(input);
    }

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }


}
