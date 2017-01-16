package com.his.service;

import com.his.common.XRClient;
import com.his.mapper.UsagesMapper;
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
public class DrugUsageService {
    XmlRpcClient client = XRClient.getXmlRpcClient();
    public final String DB = XRClient.DB;
    public final int USERNAME = XRClient.USERNAME;
    public final String PASS = XRClient.PASS;

    @Autowired
    private UsagesMapper usagesMapper;


    public Object getDrugUsage(final String type) {

        // 药品用法列表
        List<Object> usage = null;
        try {
            usage = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "drug.usage", "search_read",
                            Arrays.asList(Arrays.asList(
                                    Arrays.asList("type", "=", type)
                                    )),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields", Arrays.asList("id","name","remark"));

                                }
                            })));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return usage;
    }

    public Object selectTimes() {

        // 次数列表
        List<Object> tiimes = null;
        try {
            tiimes = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "drug.times", "search_read",
                            Arrays.asList(Arrays.asList(
                            )),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields", Arrays.asList("id","name","remark","times"));

                                }
                            })));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return tiimes;
    }

    public Object selectTimesById(Integer id) {


        List<Object> tiimes = null;
        try {
            tiimes = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "drug.times", "search_read",
                            Arrays.asList(Arrays.asList(
                                    Arrays.asList("id", "=", id)
                            )),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields", Arrays.asList("id","name","remark","times"));

                                }
                            })));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        if(tiimes.size()>0){
            return tiimes.get(0);
        }
        return tiimes;
    }

    public List<DrugUsage> selectDrugUsage(String type){

        return usagesMapper.selectDrugUsageByType(type);
    }

    public void insertDrugUsage(DrugUsage drugUsage){
        usagesMapper.insertDrugUsage(drugUsage);

    }

    public void updateDrugUsage(DrugUsage drugUsage){
        usagesMapper.updateDrugUsage(drugUsage);

    }

    public void deleteDrugUsage(String id){
        usagesMapper.deleteDrugUsage(id);
    }

    public Integer selectSeq(){
        return usagesMapper.selectSeq();
    }
}
