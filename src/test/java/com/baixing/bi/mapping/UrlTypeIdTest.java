package com.baixing.bi.mapping;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by zjl on 2017/7/11.
 */
public class UrlTypeIdTest {

    public static UrlTypeId urlTypeId = new UrlTypeId();
    static {
        try {
            urlTypeId.loadConfigFile("/Users/zjl/Downloads/storm_conf/url_type_regex.conf");
        } catch (IOException e) {

        }

    }

    @Test
    public void testViewAd() {
        String url = "http://shanghai.baixing.com/m/gongren/a1137060641.html";
        String id = urlTypeId.getType(url);
        System.out.println(id);
    }

}
