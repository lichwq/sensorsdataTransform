package com.baixing.bi.bolts.mapping;

import com.baixing.bi.event.SensorInput;
import org.apache.storm.kafka.bolt.mapper.TupleToKafkaMapper;
import org.apache.storm.tuple.Tuple;

/**
 * Created by zjl on 2017/6/12.
 */

public class MoutanToKafkaMapper<K,V> implements TupleToKafkaMapper<K, V> {

    public K getKeyFromTuple(Tuple tuple) {
        return null;
    }

    public V getMessageFromTuple(Tuple tuple) {
        SensorInput sensorInput = (SensorInput) tuple.getValue(0);
        String res = sensorInput.toJson();

        return (V)sensorInput.toJson();
    }
}
