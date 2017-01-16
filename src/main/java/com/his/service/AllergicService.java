package com.his.service;

import com.his.common.XRClient;
import com.his.mapper.AllergicMapper;
import com.his.model.Allergic;
import com.google.gson.Gson;
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

/**
 * Created by Administrator on 2016-11-18.
 */
@Service
public class AllergicService {

    XmlRpcClient client = XRClient.getXmlRpcClient();
    public final String DB = XRClient.DB;
    public final int USERNAME = XRClient.USERNAME;
    public final String PASS = XRClient.PASS;

    @Autowired
    AllergicMapper allergicMapper;

    public  Object getAll(){
        List<Object> allergic_history = null;
        try {
            allergic_history = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "allergic.history", "search_read",
                            Arrays.asList(Arrays.asList(

                            )),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields", Arrays.asList("name","x_pinyin","x_wubi"));

                                    put("limit", 100);
                                }
                            })
            ));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        System.out.println(allergic_history);
        return allergic_history;

    }

    public  Object getAllergic_history(String name){

        List<Object> allergic_history = null;
        try {
            allergic_history = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "allergic.history", "search_read",
                            Arrays.asList(Arrays.asList(
                                    Arrays.asList("name", "!=", "无"),
                                    "|","|",
                                    Arrays.asList("name", "like", name),
                                    Arrays.asList("x_wubi","like",name),
                                    Arrays.asList("x_pinyin","like",name)
                            )),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields", Arrays.asList("name","x_pinyin","x_wubi"));

                                    put("limit", 100);
                                }
                            })
            ));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        System.out.println(allergic_history);
        return allergic_history;

    }

    public Integer postAllergic(final Allergic allergic) {

        Integer id =0;
        try {
               id = (Integer) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "allergic.history", "create",
                            Arrays.asList(new HashMap() {{
                                put("name", allergic.getName());
                                put("x_pinyin", allergic.getX_pinyin());
                                put("x_wubi", allergic.getX_wubi());
                            }})));

            System.out.print(id);

        } catch (XmlRpcException e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }

    public String putAllergic(final Allergic allergic) {

        try {
            client.execute("execute_kw", Arrays.asList(
                    DB, USERNAME, PASS,
                    "allergic.history", "write",
                    Arrays.asList(
                            Arrays.asList(allergic.getId()),
                            new HashMap() {{
                                put("name", allergic.getName());
                                put("x_pinyin", allergic.getX_pinyin());
                                put("x_wubi", allergic.getX_wubi());
                            }})));

        } catch (XmlRpcException e) {
            e.printStackTrace();
        }

        return "success";
    }

    public String deleteAllergic(final Integer id) {

        try {
            client.execute("execute_kw", Arrays.asList(
                    DB, USERNAME, PASS,
                    "allergic.history", "unlink",
                    Arrays.asList(Arrays.asList(id))));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }

        //System.out.print(11211);


        return "success";
    }

   public List<Allergic> selectAllergic(){
       return allergicMapper.selectAllergic();
   }
   public List<Allergic> selectAllergicByCode(String code){
       return allergicMapper.selectAllergicByCode(code);
   }

   public void deleteAllergic(int id){
       allergicMapper.deleteAllergic(id);
   }
    public void insertAllergic(Allergic allergic){
        allergicMapper.insertAllergic(allergic);
    }
   public void updateAllergic(Allergic allergic){
       allergicMapper.updateAllergic(allergic);
   }
}
