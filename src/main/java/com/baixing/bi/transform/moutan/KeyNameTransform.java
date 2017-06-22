package com.baixing.bi.transform.moutan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjl on 2017/6/21.
 * 将 moutan 事件中 的部分key 转换为另外一个名字，符合神策的要求
 */
public class KeyNameTransform {

    private static HashMap<String, HashMap<String,String>> coverTypes;

    /**
     * Instantiates a new Sensor key transform.
     */
    public KeyNameTransform() {
        coverTypes = genCoverType();
    }


    /**
     * 生成一个key list,格式如下：
     * 事件类型，原始key名字，修改后key名字
     */
    private ArrayList<String> getcoverList() {
        ArrayList<String> coverStr = new ArrayList<String>();

        coverStr.add("bindSucc,mobile,bindMobile");
        coverStr.add("contact,mobile,contactMobile");

        coverStr.add("openBuy,type,buyType");
        coverStr.add("buySucc,type,buyType");
        coverStr.add("buyFailed,type,buyType");
        coverStr.add("openWxPay,type,buyType");
        coverStr.add("closeWxPay,type,buyType");
        coverStr.add("wxPaySucc,type,buyType");
        coverStr.add("wxPayFailed,type,buyType");

        coverStr.add("shareSucc,type,shareType");
        coverStr.add("shareCancel,type,shareType");
        coverStr.add("shareFail,type,shareType");

        coverStr.add("openBuy,days,payDays");
        coverStr.add("buySucc,days,payDays");
        coverStr.add("buyFailed,days,payDays");
        coverStr.add("openWxPay,days,payDays");
        coverStr.add("closeWxPay,days,payDays");
        coverStr.add("wxPaySucc,days,payDays");
        coverStr.add("wxPayFailed,days,payDays");

        coverStr.add("buySucc,price,payPrice");
        coverStr.add("buyFailed,price,payPrice");
        coverStr.add("openWxPay,price,payPrice");
        coverStr.add("closeWxPay,price,payPrice");
        coverStr.add("wxPaySucc,price,payPrice");
        coverStr.add("wxPayFailed,price,payPrice");

        coverStr.add("shareSucc,position,sharePosition");
        coverStr.add("shareCancel,position,sharePosition");
        coverStr.add("shareFail,position,sharePosition");

        return coverStr;
    }

    /**
     * 将list转化为hashmap 方便使用
     * @return 一个map
     */
    private HashMap<String, HashMap<String, String>>  genCoverType() {
        ArrayList<String> coverStr = getcoverList();

        coverTypes = new HashMap<String, HashMap<String,String>>();

        for (String str : coverStr) {
            String[] strArr = str.split(",");

            HashMap<String,String> coverInfo = null;
            coverInfo = coverTypes.get(strArr[0]);
            if (null == coverInfo) {
                coverInfo = new HashMap<String,String>();
            }
            coverInfo.put(strArr[1], strArr[2]);
            coverTypes.put(strArr[0], coverInfo);
        }
        return coverTypes;
    }

    /**
     * Gets cover type.
     *
     * @param et   the et
     * @param type the type
     * @return the cover type
     */
    public String getCoverType(String et, Object type) {

        HashMap<String,String> coverInfo = coverTypes.get(et);
        if (null == coverInfo) {
            return null;
        } else {
            String res = coverInfo.get(type);
            if (null == res) {
                return null;
            } else {
                return res;
            }
        }
    }

    /**
     * 转化msg中的数据，没有生成新的hash，直接在原来的msg上修改。
     * @param et  事件类型
     * @param msg 事件消息
     */
    public void processMsg( String et, HashMap<Object, Object> msg) {

        HashMap<String,String> coverInfo = coverTypes.get(et);
        if (null != coverInfo) {
            for (Object o : coverInfo.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                Object key = entry.getKey();
                Object val = entry.getValue();
                if (msg.containsKey(key)) {
                    Object value = msg.get(key);
                    msg.remove(key);
                    msg.put(val.toString(), value);
                }
            }
        }
    }

}
