package com.baixing.bi.format;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baixing.bi.utils.LoadFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zjl on 2017/6/30.
 * 转换event type。
 * 将event的type转换为最终需要的type。
 *
 */
public class EventType {

    private static Map<String, String> eventTypes;
    private static final Logger LOG = LoggerFactory.getLogger(EventType.class);

    public EventType() {
        if (null == eventTypes) {
            eventTypes = new HashMap<String, String>();
        }
    }

    /**
     * 每一行的数据如下：
     *{"type": "$action",alias: ["123","456"]}
     *如果type的属性值以"$"开头，那么这个值就是alias中事件包含的属性值。
     * 例如moutan，这里就应该是$action
     * 如果不以$开头，它就是一个字符串
     * 例如：
     * {"type":"webContact", alias:["showcontactnumber"]}
     * 但是，如果要将moutan中的几个action 合并为一个，就比较蛋疼了
     * 不过目前也不考虑，等真的出现再解决，
     * @param filePath
     * @throws IOException
     */
    public void loadConfig(String filePath) {
        ArrayList<String> content = LoadFile.loadData(filePath);
        for (String str: content) {
            JSONObject oo = JSON.parseObject(str);
            String type = oo.get("type").toString();
            List<String> typeAlias = oo.getJSONArray("alias").toJavaList(String.class);
            for (String alias : typeAlias) {
                eventTypes.put(alias, type);
            }
        }
    }

    /**
     * 修改Event的TYPE, 如果在eventTypes中存在对应的值就修改，如果不存在，就不做任何处理
     * @param event
     */
    public void changeType(Event event) {
        String oldType = event.getType();
        String newType = eventTypes.get(oldType);

        if (null == newType || newType.equals("")) {
            return;
        }
        if (newType.startsWith("$")) {
            String filed = (String) event.getField(newType.substring(1));
            event.setType(filed);
        } else {
            event.setType(newType);
        }
    }

    public String toString() {
        return eventTypes.toString();
    }
}
