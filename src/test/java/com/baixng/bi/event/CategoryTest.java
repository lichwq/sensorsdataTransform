package com.baixng.bi.event;

import com.baixing.bi.event.Category;
import com.baixing.bi.event.City;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zjl on 2017/5/31.
 */
public class CategoryTest {

    public static Category category = null;
    static  {
        category = new Category();
        category.loadConfigFile("/Users/zjl/Downloads/category_format.csv");

    }

    @Test
    public void testCategoryNameCn() {
        Assert.assertEquals(category.getFiled("cheliang","categoryCn"), "车辆");
    }

    @Test
    public void testCategoryNameCnNotExist() {
        Assert.assertEquals(category.getFiled("cheliang1","categoryCn"), "NULL");
    }


    /**
     * Created by zjl on 2017/5/31.
     */
    public static class CityTest {

        public static City city = null;
        static  {
            city = new City();
            city.loadConfigFile("/Users/zjl/Downloads/city_format.csv");
        }

        @Test
        public void testCityNameCn() {
            Assert.assertEquals("营口", city.getFiled("yingkou", "cityNameCn"));
    //        Assert.assertEquals("北京", city.getFiled("beijing", "cityNameCn"));
        }

        @Test
        public void testCityNameCnNotExists() {
            Assert.assertEquals("NULL", city.getFiled("yingkou1", "cityNameCn"));
        }

        @Test
        public void testShengNameCn() {
            Assert.assertEquals("辽宁", city.getFiled("yingkou", "shengNameCn"));
        }

    }
}
