package com.baixing.bi.format;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baixing.bi.utils.LoadFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjl on 2017/6/30.
 * 转换eventFiled，有的时候，需要将event中某些pair的field（key）
 * 转为别的，
 * 例如将 moutan 中 buySuccess 的 price 转换为 buyPrice
 * {"type":"buySuccess",
 * "fields": [{"field": "buyPirce", alias:["price"]},{"field": "buyType", alias:["type"]}]}
 *
 */
public class EventField {
    private HashMap<String, HashMap<String, String>> typeFileds;

    public EventField() {
        if (null == typeFileds) {
            typeFileds = new HashMap<String, HashMap<String, String>>();
        }
    }

    @Override
    public String toString() {
        return typeFileds.toString();
    }
    /**
     * 将每一句JSON转化为String
     * @param str   {"type":"buySuccess",
     * "fields": [{"field": "buyPirce", alias:["price"]},{"field": "buyType", alias:["type"]}]}
     */
    public void processJson(String str) {
        JSONObject oo = JSON.parseObject(str);
        String type = oo.get("type").toString();
        JSONArray fields = JSON.parseArray(oo.get("fields").toString());
        for (Object pair : fields) {
            JSONObject pairObject = JSON.parseObject(pair.toString());
            String key = pairObject.getString("field");
            Object[] values = JSON.parseArray(pairObject.getString("alias")).toArray();
            for (Object value : values) {
                insertOrUpdateTypeFileds(type, value.toString(), key);
            }
        }
    }

    /**
     * 将数据插入到typeFileds中
     * @param type event 的类型
     *
     * @param key event fields 中的pair 中的 ailas 的一个, 例如 buyPirce
     * @param value field ，例如： price
     */
    private void insertOrUpdateTypeFileds(String type, String key , String value) {
        HashMap<String, String> properties = typeFileds.get(type);
        if (null == properties) {
            properties = new HashMap<String, String>();
            properties.put(key, value);
            typeFileds.put(type, properties);
        } else {
            properties.put(key, value);
        }
    }

    /**
     * 加载配置文件
     * @param filePath
     * @throws IOException
     */
    public void loadConfig (String filePath) {
        ArrayList<String> content = LoadFile.loadData(filePath);
        for (String str: content) {
            processJson(str);
        }
    }

    /**
     * 修改fields，
     * 1. 获取type
     * 2. 从typeFiles中找到是否存在需要转换的数据
     * 3. 按照typeFiles 中的数据，对event的msg中的内容进行修改。
     * @param event
     */
    public void changeFields(Event event) {

        String type = event.getType();
        HashMap<Object, Object> msg = (HashMap<Object, Object>) event.getMsg();
        HashMap<String ,String > fields = typeFileds.get(type);
        if (null != fields) {
            for (Object o : fields.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                Object aliasType = entry.getKey();
                Object targetType = entry.getValue();

                if (msg.containsKey(aliasType)) {
                    Object msgVal = msg.get(aliasType);
                    msg.remove(aliasType);
                    msg.put(targetType, msgVal);
                }
            }
        }
    }
}
