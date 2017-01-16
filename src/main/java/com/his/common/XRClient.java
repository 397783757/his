package com.his.common;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016-12-28.
 */
public class XRClient {
    public static final String URL = "http://127.0.0.1:8888";
    public static final String DB = "H";
    public static final int USERNAME = 1;
    public static final String PASS = "123";
    public static XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    public static XmlRpcClient client = new XmlRpcClient();

    public static XmlRpcClient getXmlRpcClient() {
        try {
            config.setServerURL(new URL(String.format("%s/xmlrpc/2/object", URL)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        client.setConfig(config);
        return client;
    }

}
