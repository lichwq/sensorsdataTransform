package com.baixing.bi.format;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baixing.bi.utils.LoadFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zjl on 2017/6/30.
 */
public class EventDataFormat {

    private static final Logger LOG = LoggerFactory.getLogger(EventDataFormat.class);

    private HashMap<String, HashMap<String,DataType>> dataFormatRecord;
    private HashMap<String, HashMap<String,DataType>> dataFormatMapping;

    public EventDataFormat() {
        if (null == dataFormatRecord) {
            dataFormatRecord = new HashMap<String, HashMap<String,DataType>>();
        }
        if (null == dataFormatMapping) {
            dataFormatMapping = new HashMap<String, HashMap<String,DataType>>();
        }

    }


    /**
     *
     * @param str
     */
    public void processJson(String str) {

        JSONObject oo = JSON.parseObject(str);
        String type = oo.get("type").toString();
        Iterator iterator = oo.getJSONArray("msg").iterator();
        while (iterator.hasNext()){
            Object object = iterator.next();
            Pair pair = JSON.parseObject(object.toString(), Pair.class);
            insertOrUpdateData(type, pair.getField(), pair.getDataType());
        }

    }

    public String toString() {
        return String.format("dataFormatRecord: %s, dataFormatMapping: %s" ,
                dataFormatRecord.toString(),
                dataFormatMapping.toString());

    }

    private void insertOrUpdateData(String type, String key , DataType value) {
        HashMap<String, HashMap<String,DataType>> dataFormat;
        if (value.isFromRecord()) {
            dataFormat = dataFormatRecord;
        } else {
            dataFormat = dataFormatMapping;
        }
        HashMap<String, DataType> properties = dataFormat.get(type);
        if (null == properties) {
            properties = new HashMap<String, DataType>();
            properties.put(key, value);
            dataFormat.put(type, properties);
        } else {
            properties.put(key, value);
        }
    }

    public void loadConfig(String filePath) {
        ArrayList<String> content = LoadFile.loadData(filePath);
        for (String str: content) {
            processJson(str);
        }
    }

    /**
     * 过滤不需要的字段，如果发现有不需要的字段写入warn日志
     * @param event
     */
    public void removeNoNeedData(Event event) {
        String type = event.getType();
        HashMap<Object, Object> msg = (HashMap<Object, Object>)event.getMsg();
        // 去掉msg中多余的字段
        Iterator<Map.Entry<Object, Object>> it = msg.entrySet().iterator();
        HashMap<String,DataType> recordPair = dataFormatRecord.get(type);
        HashMap<String,DataType> mappingPair = dataFormatMapping.get(type);

        if (null == recordPair && null == mappingPair) return;

        while(it.hasNext()) {
            Map.Entry<Object, Object> entry = it.next();
            String key = entry.getKey().toString();
            if ((null != recordPair && !recordPair.containsKey(key)) &&
                    (null != mappingPair && !mappingPair.containsKey(key))) {
//                LOG.warn("msg cotain unneeded field: " + key + " " + event.toJson());
                it.remove();
            }
        }
    }

    /**
     *
     * 2. 如果发现缺少字段，报错，并返回false
     * 3. 如果发现字段格式不对，尝试转换，如果转换失败，log error, return false
     * 4. 全部转换成功，返回 true
     * @param event
     * @param fromRecord 检查主站的数据，还是mapping的数据
     * @return
     */
    public boolean checkData(Event event, boolean fromRecord) {
        removeNoNeedData(event);

        String type = event.getType();
        HashMap<String,DataType> pair;
        if (fromRecord) {
            pair = dataFormatRecord.get(type);
        } else {
            pair = dataFormatMapping.get(type);
        }

        if (null == pair) return true;

        HashMap<Object, Object> msg = (HashMap<Object, Object>)event.getMsg();
        for (Object o: pair.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            String field = entry.getKey().toString();
            DataType dataType = (DataType) entry.getValue();
            //如果msg没有这个field, 并且这个这个field 必须存在，报错
            if (!msg.containsKey(field) && dataType.isFieldRequired()) {
                LOG.error("msg less error field: " + field + " " + event.getType());
                return false;
            } else if(msg.containsKey(field)) {
                //如果这个msgValue 为空或者为null， 同时 要求这个field必须有值的时候，报错
                Object msgValue = msg.get(field);
                if ((null == msgValue || msgValue.toString().trim().equals("")) && dataType.isValueRequired()) {
                    LOG.error("no value error field: " + field + " " + event.getType());
                    return false;
                }
                // 如果 msgValue 不为空， 但是类型不对，尝试修改类型
                if (msgValue != null && !dataType.checkValueType(msgValue)) {
                    try {
                        Object newValue = dataType.changeValueType(msgValue);
                        msg.put(field, newValue);
                    } catch (Exception e) {
                        LOG.error("cast value error field: " + field + " " + event.getType() + " " + msgValue);
                        return false;
                    }
                }
            }
        }
        
        return true;
    }

    public static class Pair {

        private String field;
        private DataType dataType;

        public Pair() {}

        public Pair(String field, DataType dataType) {
            this.field = field;
            this.dataType = dataType;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public DataType getDataType() {
            return dataType;
        }

        public void setDataType(DataType dataType) {
            this.dataType = dataType;
        }

        public String toString() {
            return field + " " + dataType.toString();
        }
    }
}
