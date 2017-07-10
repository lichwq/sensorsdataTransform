package com.baixing.bi.format;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjl on 2017/5/15.
 */
public class Gary {

    private static final Logger LOG = LoggerFactory.getLogger(Gary.class);

    public static String[] FIELDS = {
            "arrive_time", "from_url", "to_url", "log_id", "session_id",
            "user_id", "category_name_en", "from_ip", "city_id", "click_id",
            "profile", "tp1", "tp2", "tp3", "tp4",
            "origin_url_type_id", "page_depth", "cv1", "cv2", "cv3",
            "cv4", "track_id", "banner_id", "user_agent", "content_user_id",
            "content_ad_id", "city_name_en", "reid", "pv_id", "from_pv_id",
            "from_div", "latlng", "latlng_city"
    };


    private Map<String, String> kv;

    public Gary() {}

    public Gary(Map<String, String> kv) {
        this.kv = kv;
    }

    public static Gary fromLine(String line)  {

        Map<String, String> kv = new HashMap<String, String>();
        String[] pieces = line.split(",");

        for (int i = 0; i < FIELDS.length; i++) {
            kv.put(FIELDS[i], "");
        }

        if (pieces.length != FIELDS.length) {
            LOG.error("error when parse line:" + line);
            return null;
        }

        for (int i = 0; i < FIELDS.length; i++) {
            kv.put(FIELDS[i], StringUtils.strip(pieces[i], "\""));
        }

        return new Gary(kv);
    }

    public String getField(String name) {
        return kv.get(name);
    }

    public String toJson() {
        String jsonString = JSON.toJSONString(kv);
        return jsonString;
    }


    public Map<String, String> getKv() {
        return kv;
    }

    public void setKv(Map<String, String> kv) {
        this.kv = kv;
    }

    public void put(String key, String value) {
        this.kv.put(key, value);
    }

    @Override
    public String toString() {
        return "Gary{" +
                "kv=" + kv +
                '}';
    }
}
