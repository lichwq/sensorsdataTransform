package com.baixing.bi.transform.moutan;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by zjl on 2017/6/21.
 */
public class KeyNameTransformTest {

    private static KeyNameTransform keyNameTransform;
    static {
        keyNameTransform = new KeyNameTransform();
    }

    @Test
    public void testGetCoverType() {
        String et = "openBuy";
        String type = "days";
        String value = keyNameTransform.getCoverType(et,type);
        Assert.assertEquals(value, "payDays");
    }

    @Test
    public void testGetCoverType1() {
        String et = "wxPayFailed";
        String type = "price";
        String value = keyNameTransform.getCoverType(et,type);
        Assert.assertEquals(value, "payPrice");
    }

    @Test
    public void testGetCoverTypeNotExists() {
        String et = "wxPayFailed";
        String type = "price22";
        String value = keyNameTransform.getCoverType(et,type);
        Assert.assertEquals(value, null);
    }

    @Test
    public void testProcessMsg() {
        HashMap<Object, Object> msg = new HashMap<Object, Object>();
        msg.put("type", "123");
        msg.put("days", "111");
        msg.put("price", "1");

        String et = "wxPayFailed";
        keyNameTransform.processMsg(et, msg);
        Assert.assertEquals(msg.get("buyType"), "123");
        Assert.assertEquals(msg.get("payDays"), "111");
        Assert.assertEquals(msg.get("payPrice"), "1");
    }

}
