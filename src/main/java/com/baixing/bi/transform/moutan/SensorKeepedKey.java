package com.baixing.bi.transform.moutan;

import java.util.HashMap;

/**
 * Created by zjl on 2017/6/21.
 * 这个类保存了神策的一些保留字段，需要将moutan中所有的消息中的对应的key替换为神策保留key
 */
public class SensorKeepedKey {

    private static HashMap<String, String> senseorKeepFiled;

    public SensorKeepedKey() {
        senseorKeepFiled = new HashMap<String, String>();
        senseorKeepFiled.put("url", "$url");
        senseorKeepFiled.put("ip", "$ip");
        senseorKeepFiled.put("ua", "$user_agent");
    }

    public String get(Object key) {
        return senseorKeepFiled.get(key);
    }

    public boolean containsKey(Object key) {
        return senseorKeepFiled.containsKey(key);
    }
}
