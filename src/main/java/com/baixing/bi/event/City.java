package com.baixing.bi.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zjl on 2017/5/31.
 * 主站城市
 */
public class City {
    private static final Logger LOG = LoggerFactory.getLogger(City.class);
    private final String DELIMITER = ",";
    private static ArrayList<String[]> cityMapping = new ArrayList<String[]>();
    private static HashMap<String, Integer> cityMappingHead = new HashMap<String, Integer>();

    static {
        cityMappingHead.put("cityId", 0);
        cityMappingHead.put("cityNameEn", 1);
        cityMappingHead.put("cityNameCn", 2);
        cityMappingHead.put("shengNameCn", 3);
    }

    public City() {}

    public void loadConfigFile(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            String str;
            while((str = reader.readLine()) != null){
                String[] arr = str.split(DELIMITER);
                cityMapping.add(arr);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getFiled(String cityNameEn, String filedName) {

        String res = "NULL";
        if (null == cityNameEn || null == filedName) {
            LOG.error(String.format("the input is not valid, cityNameEnd: %s, filedName: %s ", cityNameEn, filedName));
            return res;
        }

        Integer filedIndex = cityMappingHead.get(filedName);
        if (null == filedIndex) {
            LOG.error("can not find the filedIndex of " + filedName);
            return res;
        }
        Integer cityNameEnIndex = cityMappingHead.get("cityNameEn");

        for (String[] arr : cityMapping) {
            if (arr[cityNameEnIndex].equals(cityNameEn)) {
                return arr[filedIndex];
            }
        }
        return res;
    }

}
