package com.baixing.bi.utils;

import com.alibaba.fastjson.JSONReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by zjl on 2017/6/30.
 */
public class LoadFile {
    private static final Logger LOG = LoggerFactory.getLogger(LoadFile.class);

    /**
     * 注释符号
     */
    private static String ANNOTATION = "#";

    /**
     * 从文件中读取每一行，并返回一个ArrayList
     * @param filePath
     * @return
     * @throws IOException
     */
//    public static ArrayList<String> loadData(String filePath) {
//        ArrayList<String> content = new ArrayList<String>();
//        try {
//            FileReader fileReader = new FileReader(filePath);
//            BufferedReader reader = new BufferedReader(fileReader);
//            String str;
//            while((str = reader.readLine()) != null){
//                str = str.trim();
//                if (! str.startsWith(ANNOTATION)) {
//                    content.add(str);
//                }
//            }
//        } catch (IOException e) {
//            LOG.error("can not load data from file: " + filePath);
//        }
//
//        return content;
//    }

    public static ArrayList<String> loadData(String filePath) {
        ArrayList<String> content = new ArrayList<String>();
        JSONReader reader = null;
        try {
            reader = new JSONReader(new FileReader(filePath));
            reader.startArray();
            while (reader.hasNext()) {
                content.add(reader.readString());
            }
        } catch (IOException e) {
            LOG.error("can not load data from file: " + filePath);
        }
        return content;
    }

}
