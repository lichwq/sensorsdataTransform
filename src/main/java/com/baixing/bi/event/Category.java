package com.baixing.bi.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;

/**
 * Created by zjl on 2017/5/31.
 */
public class Category {
    private static final Logger LOG = LoggerFactory.getLogger(Category.class);
    private static final String DELIMITER = ",";
    private static HashMap<String, String[]> categoryMapping = new HashMap<String, String[]> ();
    private static HashMap<String, Integer> categoryappingHead = new HashMap<String, Integer>();
    static {
        int index = 0;
//        categoryappingHead.put(Constant.CATEGORY_EN, 0);
        categoryappingHead.put(Constant.CATEGORY_CN, index++);
        categoryappingHead.put(Constant.TOP_CATEGORY_EN, index++);
        categoryappingHead.put(Constant.TOP_CATEGORY_CN, index++);
    }

    public Category() {}

    /**
     * 读取配置文件
     * 文件格式为 category_name_en,category_name_cn,top_category_name_en,top_category_name_cn
     *
    * **/
    public void loadConfigFile(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            String str;
            while((str = reader.readLine()) != null){
                String[] arr = str.split(DELIMITER);
                String[] needArr =  new String[]{arr[1],arr[2],arr[3]};
                categoryMapping.put(arr[0], needArr);
            }
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
    }

    /**
    * 根据类目英文名获取其它的信息，categoryNameCn, TopCategoryNameEn, TopCategoryNameCn
     * 如果获取成功，返回对应的名字
     * getFiled("cheliang", "categoryNameCn")，返回 "车辆"
     * 其它情况，返回 "NULL"
     */
    public String getFiled(String categoryNameEn, String filedName) {
        String res = "NULL";
        if (categoryNameEn == null || filedName == null ) {
            LOG.error("the categoryNameEn or filedName is null ");
            return res;
        }

        Integer num = categoryappingHead.get(filedName);
        if (null == num) {
            LOG.error("the filed name is not matching: " + filedName);
            return res;
        }

        String[] arr = categoryMapping.get(categoryNameEn);
        if (null == arr) {
            LOG.error("Can not get the categoryInfo of categoryEn: " + categoryNameEn);
            return res;
        } else {
            return arr[num];
        }
    }



}
