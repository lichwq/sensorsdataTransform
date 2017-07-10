package com.baixing.bi.mapping;

import com.baixing.bi.utils.ThriftClient;
import com.baixing.thrift.moutan.Moutan;
import com.baixing.thrift.moutan.TSite;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Created by zjl on 2017/6/14.
 */
public class Sites {
    private static final Logger LOG = LoggerFactory.getLogger(Sites.class);
    private static String thirftUrl;
    private HashMap<String, TSite> sites;
    private ThriftClient thriftClient;
    private Moutan.Client client;

    public Sites(String thirftUrl) {
        this.thirftUrl = thirftUrl;
    }

    public void init() {
        sites = new HashMap<String, TSite>();
        thriftClient = new ThriftClient(thirftUrl, "moutan");
        client = (Moutan.Client)thriftClient.initClient();
    }

    public TSite getOrUpdateSite(String siteId) throws TException {
        TSite tSite = sites.get(siteId);
        if (null == tSite) {
            tSite = client.getSite(siteId);
            sites.put(siteId, tSite);
        }
        return tSite;
    }

    public String getSiteTitle(String siteId) {
        TSite tSite = null;
        try {
            tSite = getOrUpdateSite(siteId);
        } catch (TException e) {
            LOG.error("getSite error + " + e.getMessage());
            return null;
        }

        return tSite.getTitle();

    }
//
//    public String getAreaId(String siteId) {
//        TSite tSite = null;
//        try {
//            tSite = getOrUpdateSite(siteId);
//        } catch (TException e) {
//            LOG.error(e.getMessage());
//        }
//
//        if (null != tSite) {
//            return tSite.getArea();
//        } else {
//            return "";
//        }
//    }
//
//    public String getCityID(String siteId) {
//        TSite tSite = null;
//        try {
//            tSite = getOrUpdateSite(siteId);
//        } catch (TException e) {
//            LOG.error(e.getMessage());
//        }
//
//        if (null != tSite) {
//            return tSite.getCity();
//        } else {
//            return "";
//        }
//    }

    public void close() {
        thriftClient.close();
    }
}
