package com.baixng.bi.event;

import com.baixing.bi.mapping.Area;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zjl on 2017/6/12.
 */

public class AreaTest {

    public static Area area = null;
    static  {
        area = new Area();
        area.loadConfigFile("/Users/zjl/Downloads/area_format.csv");

    }

    @Test
    public void testAreaCn() {
        Assert.assertEquals(area.getFiled("m6519","areaCn"), "凤泉");
    }

    @Test
    public void testCityCn() {
        Assert.assertEquals(area.getFiled("m6519","cityCn"), "新乡");
    }

    @Test
    public void testShengCn() {
        Assert.assertEquals(area.getFiled("m6519","shengCn"), "河南");
    }

    @Test
    public void testShengCnNotExists() {
        Assert.assertEquals(area.getFiled("m3140","shengCn"), "NULL");
    }

    @Test
    public void testAreaCn1() {
        Assert.assertEquals(area.getFiled("m87","areaCn"), "运城");

    }


}
