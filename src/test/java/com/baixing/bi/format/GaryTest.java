package com.baixing.bi.format;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zjl on 2017/7/11.
 */
public class GaryTest {

    @Test
    public void testFromLine() {
        String line = "\"2017-07-11 14:09:46\",\"http://zibo.baixing.com/m/jinrongfuwu/\"," +
                "\"http://zibo.baixing.com/m/jinrongfuwu/\",\"14992201406123792277\",\"14992201403755323681\",\"0\"," +
                "\"jinrongfuwu\",\"120.86.58.177\",\"533\",\"\",\"\",\"perf1\",\"sharp_ranking1\",\"fe_perf_reporter1\"," +
                "\"ershougongchengche_listing9\",\"4\",\"5\",\"refresh_optimization2\",\"html5_360_640_status_false\"," +
                "\"\",\"1499753383\",\"149922013952072\",\"0\"," +
                "\"mozilla/5.0 (linux; u; android 5.1; zh-cn; build/lmy47d ) applewebkit/534.30 (khtmllike gecko) version/5.1 mobile safari/534.30 gionee-gn5001s/gn5001s rv/5.0.16 gnbr/4.0.9.bx id/cdbacf4c6223bb838a2cc0a3d66c7e9d\",\"0\",\"\",\"dongguan\",\"59646ba7bb8fd71828\",\"299dsme0y6wdn29-1499753375168--1768245713-1756\",\"rjjkaf4wg3in3ik9-1499753360929--673514290-143\",\"\",\"\",\"\"";

        Gary gary = Gary.fromLine(line);
        Assert.assertEquals(gary.getField("arrive_time"), "2017-07-11 14:09:46");
    }

    @Test
    public void testPlatform() {
        String testUrl = "http://shanghai.baixing.com/m/gongren/a1137060641.html";
        String platform = Gary.getPlatform(testUrl);
        Assert.assertEquals("wap", platform);

        testUrl = "shanghai.baixing.com/wap/ershoucheqi/a.html";
        platform = Gary.getPlatform(testUrl);
        Assert.assertEquals("wap", platform);

        testUrl = "shanghai.baixing.com/ershoucheqi/a.html";
        platform = Gary.getPlatform(testUrl);
        Assert.assertEquals("web", platform);
    }

}
