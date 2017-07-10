package com.baixing.bi.bolts.mapping;

import com.baixing.bi.format.Gary;
import org.apache.storm.kafka.bolt.mapper.TupleToKafkaMapper;
import org.apache.storm.tuple.Tuple;

/**
 * Created by zjl on 2017/5/25.
 */
public class GaryToKafkaMapper<K,V> implements TupleToKafkaMapper<K, V> {

    public K getKeyFromTuple(Tuple tuple) {
        return null;
    }

    public V getMessageFromTuple(Tuple tuple) {
        Gary gary = (Gary) tuple.getValue(0);
        return (V)gary.toJson();
    }
}
