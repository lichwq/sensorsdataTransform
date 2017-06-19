package com.baixing.bi.utils;

import com.baixing.thrift.moutan.Moutan;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zjl on 2017/6/13.
 */
public class ThriftClient {

    private static final Logger LOG = LoggerFactory.getLogger(ThriftClient.class);
    private String serviceName;
    private TTransport transport;
    private TProtocol protocol;
    private String thirftUrl;

    public ThriftClient(String url, String serviceName) {
        this.thirftUrl = url;
        this.serviceName = serviceName;
    }

    public Object initClient() {
        try {
            transport = new THttpClient(thirftUrl);
            protocol = new TBinaryProtocol(transport);
            Object classObject = getClient(serviceName);
            transport.open();
            return classObject;
        } catch (TException e) {
            LOG.error("cat not connect to " + thirftUrl);
            LOG.error(String.format("cat not connect to %s , err: %s", thirftUrl, e.getMessage()));
            return null;
        }
    }

    public void close() {
        transport.close();
    }

    private Object getClient(String serviceName) {
        if (serviceName.equals("moutan")) {
            return new Moutan.Client(protocol);
        } else {
            LOG.error("can not get the client of " + serviceName);
            return null;
        }
    }
}
