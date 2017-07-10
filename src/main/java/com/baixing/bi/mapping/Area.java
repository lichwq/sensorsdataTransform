package com.baixing.bi.mapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.baixing.bi.mapping.Constant.*;

/**
 * Created by zjl on 2017/5/31.
 * moudan area matching
 *
 */
public class Area {
    private static final Logger LOG = LoggerFactory.getLogger(Area.class);
    private final String DELIMITER = ",";
    private static ArrayList<String[]> areaMapping = new ArrayList<String[]>();
    private static HashMap<String, Integer> areaMappingHead = new HashMap<String, Integer>();

    static {
        areaMappingHead.put(AREA_ID, 0);
        areaMappingHead.put(AREA_CN, 1);
        areaMappingHead.put(CITY_CN, 2);
        areaMappingHead.put(PROVINCE_CN, 3);
    }

    public Area() {}

    public void loadConfigFile(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            String str;
            while((str = reader.readLine()) != null){
                String[] arr = str.split(DELIMITER);
                areaMapping.add(arr);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * 通过 areaId 来获取 不同的filed数据, 如果获取失败了，就报警通知，通常来说，这个是肯定可以获取到的
    *
    * */
    public String getFiled(String areaId, String filedName) {

        String res = "NULL";
        if (null == areaId || null == filedName) {
            LOG.error(String.format("the input is not valid, AreaIdd: %s, filedName: %s ", areaId, filedName));
            return res;
        }

        Integer filedIndex = areaMappingHead.get(filedName);
        if (null == filedIndex) {
            LOG.error("can not find the filedIndex of " + filedName);
            return res;
        }

        Integer AreaIdIndex = areaMappingHead.get(AREA_ID);

        for (String[] arr : areaMapping) {
            if (arr[AreaIdIndex].equals(areaId)) {
                return arr[filedIndex];
            }
        }

        String errInfo = String.format("Can not get the areaMapping info of areaId %s", areaId);
        LOG.error(errInfo);
        return res;
    }

}
