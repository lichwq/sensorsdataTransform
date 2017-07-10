package com.baixing.bi.format;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zjl on 2017/5/31.
 */
public class Event implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(Event.class);
    private String type;
    private Long ts;
    private Map<Object, Object> msg;

    public Event() {}

    public Event(String type, Long ts, Map<Object, Object> msg) {
        this.type = type;
        this.ts = ts;
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public Map<Object, Object> getMsg() {
        return msg;
    }

    public void setMsg(Map<Object, Object> msg) {
        this.msg = msg;
    }

    /**
     * 如果出现msg为[] 而不是{}的情况，会解析出错
     * */
    public static Event fromJson(String json) {
        try {
            Event event = JSON.parseObject(json, Event.class);
            return event;
        } catch (JSONException e) {
            LOG.warn(String.format("cat not parse json: %s", json));
            return null;
        }
    }

    public String toJson() {
        String jsonString = JSON.toJSONString(this);
        return jsonString;
    }

    public Object getField(String name) {
        return msg.get(name);
    }

    public void put(String FiledName, String value) {
        msg.put(FiledName, value);
    }

    @Override
    public String toString() {
        return toJson();

    }
}
