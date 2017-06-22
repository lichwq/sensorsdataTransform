package com.baixng.bi.event;

import com.baixing.bi.event.Event;
import org.junit.Test;

/**
 * Created by zjl on 2017/5/31.
 */
public class EventTest {

    @Test
    public void testFromJson() {
        String test = "{\"type\":\"weixin_subscribe\",\"ts\":1496223263424,\"msg\":{\"m\":[\"123\",\"123\"]}}";
        Event event = Event.fromJson(test);
        System.out.println(event.getType());
        System.out.println(event.getField("m"));
    }
}
