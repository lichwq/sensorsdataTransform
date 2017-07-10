package com.baixng.bi.event;

import com.baixing.bi.mapping.MoutanCategory;
import org.apache.thrift.TException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by zjl on 2017/6/13.
 */
public class MoutanCategoryTest {

    private static MoutanCategory category;


    @BeforeClass
    public static void prepare() throws TException {
        category = new MoutanCategory("http://thrift.baixing.com/Moutan");
        category.init();
    }


    @Test
    public void getCategoryTest() throws TException {
        Assert.assertEquals("房屋租售", category.getCategoryCn("fangwu"));
    }

    @Test
    public void getCategoryTest1() throws TException {
        Assert.assertEquals("所有", category.getCategoryCn("all"));
    }


    @Test
    public void getCategoryTestNotExists() throws TException {
        Assert.assertEquals("房屋租售", category.getCategoryCn("fangwu"));
        Assert.assertEquals(null, category.getCategoryCn("fangwu123"));
    }

    @AfterClass
    public static void close() {
        category.close();
    }
}
