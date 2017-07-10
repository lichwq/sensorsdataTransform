package com.baixing.bi.format;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjl on 2017/7/3.
 */
public class EventTypeTest {
    private static EventType eventType;
    static {
        eventType = new EventType();
    }


//    @Test
//    public void test1() {
//
//       String type = "buyFailed";
//        String fields = "httpVerb\n" +
//                "session\n" +
//                "url\n" +
//                "referer\n" +
//                "ua\n" +
//                "ip\n" +
//                "isAjax\n" +
//                "isPV\n" +
//                "src\n" +
//                "trackId\n" +
//                "wxUserId\n" +
//                "bxUserId\n" +
//                "siteId\n" +
//                "areaId\n" +
//                "categoryId\n" +
//                "postId\n" +
//                "draftId\n" +
//                "buyType\n" +
//                "payDays\n" +
//                "srcAction\n" +
//                "payPrice\n" +
//                "areaCn\n" +
//                "cityCn\n" +
//                "provinceCn\n" +
//                "categoryCn\n" +
//                "siteTitle"
//                ;
//                String[] fieldArray = fields.split("\n");
//       ArrayList<String> res =  new ArrayList<String>();
//       for (String field: fieldArray) {
//           if (field.equals("pageNum") || field.equals("payDays") || field.equals("payPrice") || field.equals("quotaRemain")) {
//               EventDataFormat.Pair pair = new EventDataFormat.Pair(field, new DataType(false, false, 1));
//               res.add(JSON.toJSONString(pair));
//           } else {
//               EventDataFormat.Pair pair = new EventDataFormat.Pair(field, new DataType(false, false, 0));
//               res.add(JSON.toJSONString(pair));
//           }
//
//
//       }
//
//       String event = String.format("\"type\":\"%s\",\"msg\":%s", type, res.toString());
//       System.out.println(event);
//    }
    @Test
    public void loadCOnfigTest(){
        String filePath = "/Users/zjl/Downloads/eventTypeAliasFile.txt";
        eventType.loadConfig(filePath);
        System.out.println(eventType.toString());
    }

    @Test
    public void changeType() {
        String filePath = "/Users/zjl/Downloads/eventTypeAliasFile.txt";
        eventType.loadConfig(filePath);
        Event event = new Event();
        event.setType("moutan");
        Map<Object, Object> msg = new HashMap<Object, Object>();
        msg.put("price", 123);
        msg.put("type", "1");
        msg.put("userId", "12312312321321");
        msg.put("action", "viewHome");
        event.setMsg(msg);
        eventType.changeType(event);
        System.out.println(event.toString());
    }
}
