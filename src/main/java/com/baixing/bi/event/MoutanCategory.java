package com.baixing.bi.event;

import com.baixing.thrift.moutan.Moutan;
import com.baixing.thrift.moutan.TCategory;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zjl on 2017/6/13.
 */
public class MoutanCategory {

    private static final Logger LOG = LoggerFactory.getLogger(MoutanCategory.class);
    private HashMap<String, String> categoryMapping;
    private Moutan.Client client;
    private TTransport transport;
    private TProtocol protocol;
    private String thirftUrl;

    public MoutanCategory(String thirftUrl) {
        this.thirftUrl = thirftUrl;
        categoryMapping = new HashMap<String, String>();
    }

    public void init() {
        try {
            transport = new THttpClient(thirftUrl);
            protocol = new TBinaryProtocol(transport);
            client = new Moutan.Client(protocol);
            transport.open();
        } catch (TException e) {
            LOG.error("cat not connect to " + thirftUrl);
            LOG.error(String.format("cat not connect to %s , err: %s", thirftUrl, e.getMessage()));
        }
    }

    public void getOrUpdateCategories() {

        Map<String,TCategory> res = null;

        try {
            res = client.getAllCategories();
        } catch (TException e) {
            LOG.error(String.format("getAllCategories err: %s", e.getMessage()));
        }

        Iterator iter = res.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = entry.getKey().toString();
            String val = entry.getValue() == null ? "" : ((TCategory)entry.getValue()).getName();
            categoryMapping.put(key, val);
        }
    }

    public String getCategoryCn(String categoryId) {

        if (categoryId.equals("all")) {
            return "所有";
        } else if(categoryId.equals("other")) {
            return "其他";
        }

        String cn = categoryMapping.get(categoryId);
        // 如果找不到，就更新一次
        if (null == cn) {
            getOrUpdateCategories();
        }
        cn = categoryMapping.get(categoryId);
        return cn;
    }
    public void close() {
        transport.close();
    }
}
