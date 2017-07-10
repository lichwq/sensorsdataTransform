package com.baixing.bi.format;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;

/**
 * Created by zjl on 2017/6/30.
 */
public class DataType {

    /**
     * field 是不是必须的
     */
    private boolean fieldRequired;
    /**
     * value 是不是必须的。可能会出现要求msg中必须要有某个field， 但是它的值可以为空.
     * 例如 refer, 可能为空，但是必须有这个字段。
     */
    private boolean valueRequired;
    /**
     * 0 代表String
     * 1 代表number
     */
    private boolean isNumber;

    /**
     * true 代表是从主站打点过来的数据
     * false 代表是Mapping的数据.
     */
    private boolean fromRecord;

    public DataType() {}

    public DataType(boolean fieldRequired, boolean valueRequired, boolean isNumber, boolean fromRecord) {
        this.fieldRequired = fieldRequired;
        this.valueRequired = valueRequired;
        this.isNumber = isNumber;
        this.fromRecord = fromRecord;
    }

    public boolean isFromRecord() {
        return fromRecord;
    }

    public void setFromRecord(boolean fromRecord) {
        this.fromRecord = fromRecord;
    }

    public boolean isFieldRequired() {
        return fieldRequired;
    }

    public void setFieldRequired(boolean fieldRequired) {
        this.fieldRequired = fieldRequired;
    }

    public boolean isValueRequired() {
        return valueRequired;
    }

    public void setValueRequired(boolean valueRequired) {
        this.valueRequired = valueRequired;
    }

    public boolean isNumber() {
        return isNumber;
    }

    public void setNumber(boolean number) {
        isNumber = number;
    }

    public Object changeValueType(Object value) {
        if (this.isNumber()) {
            return new BigDecimal(value.toString());

        } else  {
            return value.toString();

        }
    }

    public Boolean checkValueType(Object value) {
        if (this.isNumber()) {
            return value.getClass() == BigDecimal.class;
        } else {
            return value.getClass() == String.class;
        }
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
