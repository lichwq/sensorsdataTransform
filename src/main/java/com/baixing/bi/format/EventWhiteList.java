package com.baixing.bi.format;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baixing.bi.utils.LoadFile;

import java.io.IOException;
import java.util.*;

/**
 * Created by zjl on 2017/6/30.
 * 过滤Event,这个白名单，是event转化后的白名单。
 * 因为百姓网所有的event 的信息都在一个topic中，所以开始肯定需要有一个过滤其他大部分事件的bolt，
 * 过滤之后，保留下来的应该是需要做转换的type，
 * 做了转换之后，在对这些type做过滤。
 * 例如moutan，首先要将moutan时间从eventlog中过滤出来，然后将moutan中的action转换为type，
 * 再对这些type进行过滤。
 *
 */
public class EventWhiteList {

    private static Set<String> whileList;

    public EventWhiteList() {
        if (whileList == null) {
            whileList = new HashSet<String>();
        }
    }

    /**
     * 这里是读取EventDataFormat的内容，然后获取里面的type做过滤
     * @param filePath
     * @throws IOException
     */
    public void loadConfig(String filePath) {
        ArrayList<String> content = LoadFile.loadData(filePath);
        for (String str: content) {
            JSONObject oo = JSON.parseObject(str);
            String type = oo.get("type").toString();
            whileList.add(type);

        }
    }


    public Boolean isInWhilteList(String key) {
        return whileList.contains(key);
    }
}
