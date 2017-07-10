package com.baixing.bi.format;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by zjl on 2017/7/3.
 */
public class EventDataFormatTest {
    private static EventDataFormat eventDataFormat;
    private String test = "  {\n" +
            "    \"type\": \"viewHome\",\n" +
            "    \"msg\": [\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": true\n" +
            "        },\n" +
            "        \"field\": \"httpVerb\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": true\n" +
            "        },\n" +
            "        \"field\": \"session\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": true\n" +
            "        },\n" +
            "        \"field\": \"url\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": true\n" +
            "        },\n" +
            "        \"field\": \"referer\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": true\n" +
            "        },\n" +
            "        \"field\": \"ua\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": true\n" +
            "        },\n" +
            "        \"field\": \"ip\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": true\n" +
            "        },\n" +
            "        \"field\": \"isAjax\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": true\n" +
            "        },\n" +
            "        \"field\": \"isPV\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": true\n" +
            "        },\n" +
            "        \"field\": \"src\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": true\n" +
            "        },\n" +
            "        \"field\": \"trackId\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": true\n" +
            "        },\n" +
            "        \"field\": \"wxUserId\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": true\n" +
            "        },\n" +
            "        \"field\": \"bxUserId\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": true\n" +
            "        },\n" +
            "        \"field\": \"siteId\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": true\n" +
            "        },\n" +
            "        \"field\": \"areaId\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": true\n" +
            "        },\n" +
            "        \"field\": \"categoryId\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 1,\n" +
            "          \"fromRecord\": true\n" +
            "        },\n" +
            "        \"field\": \"pageNum\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": true\n" +
            "        },\n" +
            "        \"field\": \"loadType\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": false\n" +
            "        },\n" +
            "        \"field\": \"areaCn\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": false\n" +
            "        },\n" +
            "        \"field\": \"cityCn\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": false\n" +
            "        },\n" +
            "        \"field\": \"provinceCn\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": false\n" +
            "        },\n" +
            "        \"field\": \"categoryCn\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"dataType\": {\n" +
            "          \"fieldRequired\": false,\n" +
            "          \"valueRequired\": false,\n" +
            "            \"valueType\": 0,\n" +
            "          \"fromRecord\": false\n" +
            "        },\n" +
            "        \"field\": \"siteTitle\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }";
    static {
        eventDataFormat  = new EventDataFormat();

    }

   // @Test
    public void processJsonTest() {
        eventDataFormat.processJson(test);
        System.out.println(eventDataFormat.toString());
    }

    @Test
    public void checkDataFieldRequired() {
        String testStr = "\n" +
                "{  \n" +
                "   \"type\":\"checkDataFieldRequired\",\n" +
                "   \"msg\":[  \n" +
                "      {  \n" +
                "         \"dataType\":{  \n" +
                "            \"fieldRequired\":true,\n" +
                "            \"valueRequired\":false,\n" +
                "            \"isNumber\":false,\n" +
                "            \"fromRecord\":true\n" +
                "         },\n" +
                "         \"field\":\"httpVerb\"\n" +
                "      }\n" +
                "   ]\n" +
                "}";

        eventDataFormat.processJson(testStr);
        Event event = new Event();
        event.setType("checkDataFieldRequired");
        HashMap<Object, Object> msg = new HashMap<Object, Object>();
        msg.put("url", "www.baixing.com");
        event.setMsg(msg);
        boolean res = eventDataFormat.checkData(event, true);
        Assert.assertEquals(false, res);
    }

    @Test
    public void checkDataFieldRequired1() {
        String testStr = "\n" +
                "{  \n" +
                "   \"type\":\"checkDataFieldRequired\",\n" +
                "   \"msg\":[  \n" +
                "      {  \n" +
                "         \"dataType\":{  \n" +
                "            \"fieldRequired\":true,\n" +
                "            \"valueRequired\":false,\n" +
                "            \"isNumber\":false,\n" +
                "            \"fromRecord\":true\n" +
                "         },\n" +
                "         \"field\":\"httpVerb\"\n" +
                "      }\n" +
                "   ]\n" +
                "}";

        eventDataFormat.processJson(testStr);
        Event event = new Event();
        event.setType("checkDataFieldRequired");
        HashMap<Object, Object> msg = new HashMap<Object, Object>();
        msg.put("url", "www.baixing.com");
        msg.put("httpVerb", null);
        event.setMsg(msg);
        boolean res = eventDataFormat.checkData(event, true);
        Assert.assertEquals(true, res);
    }

    @Test
    public void checkDataFieldRequiredAndValueRequired() {
        String testStr = "\n" +
                "{  \n" +
                "   \"type\":\"checkDataFieldRequired\",\n" +
                "   \"msg\":[  \n" +
                "      {  \n" +
                "         \"dataType\":{  \n" +
                "            \"fieldRequired\":true,\n" +
                "            \"valueRequired\":true,\n" +
                "            \"isNumber\":false,\n" +
                "            \"fromRecord\":true\n" +
                "         },\n" +
                "         \"field\":\"httpVerb\"\n" +
                "      }\n" +
                "   ]\n" +
                "}";

        eventDataFormat.processJson(testStr);
        Event event = new Event();
        event.setType("checkDataFieldRequired");
        HashMap<Object, Object> msg = new HashMap<Object, Object>();
        msg.put("url", "www.baixing.com");
        msg.put("httpVerb", "post");
        event.setMsg(msg);
        boolean res = eventDataFormat.checkData(event, true);
        Assert.assertEquals(true, res);
    }

    @Test
    public void checkDataFieldRequiredAndValueRequired1() {
        String testStr = "\n" +
                "{  \n" +
                "   \"type\":\"checkDataFieldRequired\",\n" +
                "   \"msg\":[  \n" +
                "      {  \n" +
                "         \"dataType\":{  \n" +
                "            \"fieldRequired\":true,\n" +
                "            \"valueRequired\":true,\n" +
                "            \"isNumber\":false,\n" +
                "            \"fromRecord\":true\n" +
                "         },\n" +
                "         \"field\":\"httpVerb\"\n" +
                "      }\n" +
                "   ]\n" +
                "}";

        eventDataFormat.processJson(testStr);
        Event event = new Event();
        event.setType("checkDataFieldRequired");
        HashMap<Object, Object> msg = new HashMap<Object, Object>();
        msg.put("url", "www.baixing.com");
        msg.put("httpVerb", "");
        event.setMsg(msg);
        boolean res = eventDataFormat.checkData(event, true);
        Assert.assertEquals(false, res);
    }

    @Test
    public void checkDataFieldNotRequiredAndValueRequired() {
        String testStr = "\n" +
                "{  \n" +
                "   \"type\":\"checkDataFieldRequired\",\n" +
                "   \"msg\":[  \n" +
                "      {  \n" +
                "         \"dataType\":{  \n" +
                "            \"fieldRequired\":false,\n" +
                "            \"valueRequired\":true,\n" +
                "            \"isNumber\":false,\n" +
                "            \"fromRecord\":true\n" +
                "         },\n" +
                "         \"field\":\"httpVerb\"\n" +
                "      }\n" +
                "   ]\n" +
                "}";

        eventDataFormat.processJson(testStr);
        Event event = new Event();
        event.setType("checkDataFieldRequired");
        HashMap<Object, Object> msg = new HashMap<Object, Object>();
        msg.put("url", "www.baixing.com");
        msg.put("httpVerb", "");
        event.setMsg(msg);
        boolean res = eventDataFormat.checkData(event, true);
        Assert.assertEquals(false, res);
    }

    @Test
    public void checkDataFieldNotRequiredAndValueRequired1() {
        String testStr = "\n" +
                "{  \n" +
                "   \"type\":\"checkDataFieldRequired\",\n" +
                "   \"msg\":[  \n" +
                "      {  \n" +
                "         \"dataType\":{  \n" +
                "            \"fieldRequired\":false,\n" +
                "            \"valueRequired\":true,\n" +
                "            \"isNumber\":false,\n" +
                "            \"fromRecord\":true\n" +
                "         },\n" +
                "         \"field\":\"httpVerb\"\n" +
                "      }\n" +
                "   ]\n" +
                "}";

        eventDataFormat.processJson(testStr);
        Event event = new Event();
        event.setType("checkDataFieldRequired");
        HashMap<Object, Object> msg = new HashMap<Object, Object>();
        msg.put("url", "www.baixing.com");
        msg.put("httpVerb", "post");
        event.setMsg(msg);
        boolean res = eventDataFormat.checkData(event, true);
        Assert.assertEquals(true, res);
    }

    @Test
    public void checkDataFieldNotRequiredAndValueNotRequired() {
        String testStr = "\n" +
                "{  \n" +
                "   \"type\":\"checkDataFieldRequired\",\n" +
                "   \"msg\":[  \n" +
                "      {  \n" +
                "         \"dataType\":{  \n" +
                "            \"fieldRequired\":false,\n" +
                "            \"valueRequired\":false,\n" +
                "            \"isNumber\":false,\n" +
                "            \"fromRecord\":true\n" +
                "         },\n" +
                "         \"field\":\"httpVerb\"\n" +
                "      }\n" +
                "   ]\n" +
                "}";

        eventDataFormat.processJson(testStr);
        Event event = new Event();
        event.setType("checkDataFieldRequired");
        HashMap<Object, Object> msg = new HashMap<Object, Object>();
        msg.put("url", "www.baixing.com");
        //msg.put("httpVerb", "post");
        event.setMsg(msg);
        boolean res = eventDataFormat.checkData(event, true);
        Assert.assertEquals(true, res);
    }

    @Test
    public void checkDataFieldNotRequiredAndValueNotRequired1() {
        String testStr = "\n" +
                "{  \n" +
                "   \"type\":\"checkDataFieldRequired\",\n" +
                "   \"msg\":[  \n" +
                "      {  \n" +
                "         \"dataType\":{  \n" +
                "            \"fieldRequired\":false,\n" +
                "            \"valueRequired\":false,\n" +
                "            \"isNumber\":false,\n" +
                "            \"fromRecord\":true\n" +
                "         },\n" +
                "         \"field\":\"httpVerb\"\n" +
                "      }\n" +
                "   ]\n" +
                "}";

        eventDataFormat.processJson(testStr);
        Event event = new Event();
        event.setType("checkDataFieldRequired");
        HashMap<Object, Object> msg = new HashMap<Object, Object>();
        msg.put("url", "www.baixing.com");
        msg.put("httpVerb", "post");
        event.setMsg(msg);
        boolean res = eventDataFormat.checkData(event, true);
        Assert.assertEquals(true, res);
    }

    @Test
    public void checkDataFieldRequiredAndValueRequiredAndFromRecord() {
        String testStr = "\n" +
                "{  \n" +
                "   \"type\":\"checkDataFieldRequired\",\n" +
                "   \"msg\":[  \n" +
                "      {  \n" +
                "         \"dataType\":{  \n" +
                "            \"fieldRequired\":true,\n" +
                "            \"valueRequired\":true,\n" +
                "            \"isNumber\":false,\n" +
                "            \"fromRecord\":false\n" +
                "         },\n" +
                "         \"field\":\"httpVerb\"\n" +
                "      }\n" +
                "   ]\n" +
                "}";

        eventDataFormat.processJson(testStr);
        Event event = new Event();
        event.setType("checkDataFieldRequired");
        HashMap<Object, Object> msg = new HashMap<Object, Object>();
        msg.put("url", "www.baixing.com");
        //msg.put("httpVerb", "post");
        event.setMsg(msg);
        boolean res = eventDataFormat.checkData(event, true);
        Assert.assertEquals(true, res);
    }

    @Test
    public void checkDataFieldRequiredAndValueRequiredAndFromRecord1() {
        String testStr = "\n" +
                "{  \n" +
                "   \"type\":\"checkDataFieldRequired\",\n" +
                "   \"msg\":[  \n" +
                "      {  \n" +
                "         \"dataType\":{  \n" +
                "            \"fieldRequired\":true,\n" +
                "            \"valueRequired\":true,\n" +
                "            \"isNumber\":false,\n" +
                "            \"fromRecord\":false\n" +
                "         },\n" +
                "         \"field\":\"httpVerb\"\n" +
                "      }\n" +
                "   ]\n" +
                "}";

        eventDataFormat.processJson(testStr);
        Event event = new Event();
        event.setType("checkDataFieldRequired");
        HashMap<Object, Object> msg = new HashMap<Object, Object>();
        msg.put("url", "www.baixing.com");
        //msg.put("httpVerb", "post");
        event.setMsg(msg);
        boolean res = eventDataFormat.checkData(event, false);
        Assert.assertEquals(false, res);
    }

    @Test
    public void checkDataFieldRequiredAndValueRequiredAndFromRecord2() {
        String testStr = "\n" +
                "{  \n" +
                "   \"type\":\"checkDataFieldRequired\",\n" +
                "   \"msg\":[  \n" +
                "      {  \n" +
                "         \"dataType\":{  \n" +
                "            \"fieldRequired\":true,\n" +
                "            \"valueRequired\":true,\n" +
                "            \"isNumber\":true,\n" +
                "            \"fromRecord\":false\n" +
                "         },\n" +
                "         \"field\":\"httpVerb\"\n" +
                "      }\n" +
                "   ]\n" +
                "}";

        eventDataFormat.processJson(testStr);
        Event event = new Event();
        event.setType("checkDataFieldRequired");
        HashMap<Object, Object> msg = new HashMap<Object, Object>();
        msg.put("url", "www.baixing.com");
        msg.put("httpVerb", "123");
        event.setMsg(msg);
        boolean res = eventDataFormat.checkData(event, false);
        Assert.assertEquals(true, res);
        Assert.assertEquals(event.getField("httpVerb").getClass(), BigDecimal.class);
    }

    @Test
    public void checkDataTest() {
        eventDataFormat.processJson(test);

        Event event = new Event();
        event.setType("viewHome");
        HashMap<Object, Object> msg = new HashMap<Object, Object>();
        msg.put("url", "www.baixing.com");
        msg.put("buyPirce",1);
        msg.put("buyTupe", "weixin");
        event.setMsg(msg);
        eventDataFormat.checkData(event, true);
        Assert.assertEquals(event.getField("url"),"www.baixing.com");

    }

//    @Test
    public void loadConfigTest() {
        String fileName = "/Users/zjl/Downloads/eventDataFormatFile.txt";
        eventDataFormat.loadConfig(fileName);
        System.out.println(eventDataFormat.toString());
    }
}
