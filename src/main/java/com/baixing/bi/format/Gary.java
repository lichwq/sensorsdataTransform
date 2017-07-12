package com.baixing.bi.format;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjl on 2017/5/15.
 */
public class Gary extends Event{

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

    public Gary() {}

    public Gary(String eventType, Long ts, Map<Object,Object> msg) {
        super(eventType, ts, msg);
    }


    public static Gary fromLine(String line)  {

        Map<Object, Object> kv = new HashMap<Object, Object>();
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

        String toUrl = kv.get("to_url").toString().trim();
        kv.put("platform", getPlatform(toUrl));
        Long ts = Timestamp.valueOf(kv.get("arrive_time").toString()).getTime();
        return new Gary("gary",ts, kv);

    }

    public static String getPlatform(String url) {
        if (url.contains("com/m/") || url.contains("com/wap/")) {
            return "wap";
        } else {
            return "web";
        }
    }

}
