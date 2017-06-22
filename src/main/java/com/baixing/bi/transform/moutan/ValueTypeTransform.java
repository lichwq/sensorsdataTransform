package com.baixing.bi.transform.moutan;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by zjl on 2017/6/21.
 */
public class ValueTypeTransform {

    private static HashMap<String, String> covertType;

    public ValueTypeTransform() {
        covertType = new HashMap<String, String>();
        covertType.put("days", "number");
        covertType.put("pageNum", "number");
        covertType.put("price", "number");
        covertType.put("quotaRemain", "number");
        covertType.put("isPV", "string");
        covertType.put("isAjax", "string");
        covertType.put("isEdit", "string");
        covertType.put("orderId", "string");
        covertType.put("errorCode", "string");
    }

    public String getValueType(Object key) {
        return covertType.get(key);
    }

    public Object covertValueType(Object value, String type) {
        if (type.equals("number")) {
            return new BigDecimal(value.toString());
        } else if (type.equals("string")) {
            return value.toString();
        }
        return value;
    }

}
