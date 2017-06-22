package com.baixing.bi.transform.moutan;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by zjl on 2017/6/21.
 */
public class ValueTypeTransformTest {
    private static ValueTypeTransform valueTypeTransform;
    static {
        valueTypeTransform = new ValueTypeTransform();
    }

    @Test
    public void getValueTypeTest() {
        String type = "days";
        String val = "123";

        String resType = valueTypeTransform.getValueType(type);
        Assert.assertEquals(resType, "number");
    }


    @Test
    public void covertValueTypeeTest() {
        String type = "days";
        String val = "123";

        String resType = valueTypeTransform.getValueType(type);
        Object resVal = valueTypeTransform.covertValueType(val, resType);
        Assert.assertEquals(resVal, new BigDecimal(123));
    }
}
