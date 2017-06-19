package com.baixing.bi.event;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zjl on 2017/6/12.
 */
public class SensorInput implements Serializable {
    private String distinct_id;
    private Long time;
    private String type;
    private String event;
    private String project;
    private boolean time_free;
    private Map<Object , Object> properties;

    public SensorInput() {}

    public String getDistinct_id() {
        return distinct_id;
    }

    public void setDistinct_id(String distinct_id) {
        this.distinct_id = distinct_id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public boolean isTime_free() {
        return time_free;
    }

    public void setTime_free(boolean time_free) {
        this.time_free = time_free;
    }

    public Map<Object, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<Object, Object> properties) {
        this.properties = properties;
    }

    public String toJson() {
        String jsonString = JSON.toJSONString(this);
        return jsonString;
    }

}
