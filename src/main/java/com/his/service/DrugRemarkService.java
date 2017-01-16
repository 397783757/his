package com.his.service;

import com.his.mapper.DrugRemarkMapper;
import com.his.mapper.UsagesMapper;
import com.his.model.DrugRemark;
import com.his.model.DrugUsage;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-12-12.
 */
@Service
public class DrugRemarkService {

    @Autowired
    private DrugRemarkMapper drugRemarkMapper;

    public  List<DrugRemark>  selectDrugRemark(){
        return drugRemarkMapper.selectDrugRemark();
    }

}
