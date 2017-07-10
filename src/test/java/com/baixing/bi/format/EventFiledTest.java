package com.baixing.bi.format;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjl on 2017/7/3.
 */
public class EventFiledTest {

    public static EventField eventField;
    static {
        eventField = new EventField();
    }

    @Test
    public void processJsonTest(){
        String testStr = "{\"type\":\"buySuccess\",\"fields\": [{\"field\": \"buyPirce\", alias:[\"price\", \"price1\"]},{\"field\": \"buyType\", alias:[\"type\"]}]}";
        eventField.processJson(testStr);
        System.out.println(eventField.toString());
    }


    @Test
    public void changeFiledsTest() {
        String testStr = "{\"type\":\"buySuccess\",\"fields\": [{\"field\": \"buyPirce\", alias:[\"price\", \"price1\"]},{\"field\": \"buyType\", alias:[\"type\"]}]}";
        eventField.processJson(testStr);
        Event event = new Event();
        event.setType("buySuccess");
        Map<Object, Object> msg = new HashMap<Object, Object>();
        msg.put("price", 123);
        msg.put("type", "1");
        msg.put("userId", "12312312321321");
        event.setMsg(msg);
        eventField.changeFields(event);
        Object res = event.getField("buyPirce");
        Assert.assertEquals(123, res);
    }
}
