package com.his.service;


import com.his.common.XRClient;
import com.his.model.Patient;
import com.his.model.PatientLine;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.List;


/**
 * Created by Administrator on 2016-09-20.
 */
@Service

public class PatientService {
    //连接odoo数据库
    XmlRpcClient client = XRClient.getXmlRpcClient();
    public final String DB = XRClient.DB;
    public final int USERNAME = XRClient.USERNAME;
    public final String PASS = XRClient.PASS;
    Gson gson=new Gson();

    List fields=Arrays.asList("name","age","phone","job","sex","street","birthday","card",
            "nation","marriage","linkman","tel","somatoplasm","registrant","wubi_code",
            "pinyin_code","note");


    public Object getPatients() throws Exception {

        // 客户列表
        List<Object> customers = Arrays.asList((Object[]) client.execute(
                "execute_kw", Arrays.asList(
                        DB, USERNAME, PASS, "res.partner", "search_read",
                        Arrays.asList(Arrays.asList(
                                // 设置查询条件
                                // Arrays.asList("is_company", "=", true),
                                Arrays.asList("customer", "=", true))),
                        new HashMap() {
                            {// 查询字段, 限定最多返回100条记录
                                put("fields",fields );

                                put("limit", 100);
                            }
                        })));
        return customers;
}

    public Integer postPatient(final Patient patient){

        List<PatientLine> patientLines=patient.getPatientLines();
        Integer id =0;
        try {
            id = (Integer) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "res.partner", "create",
                            Arrays.asList(new HashMap(){
                                {
                                    put("name", patient.getName());put("age", patient.getAge());
                                    put("phone", patient.getPhone());put("job", patient.getJob());
                                    put("sex",patient.getSex());put("street",patient.getStreet());
                                    put("birthday",patient.getBirthday()==null?"":patient.getBirthday().toString());
                                    put("card",patient.getCard());
                                    put("nation",patient.getNation());put("marriage",patient.getMarriage());
                                    put("linkman",patient.getLinkman());put("somatoplasm",patient.getSomatoplasm());
                                    put("registrant",patient.getRegistrant());put("wubi_code",patient.getWubi_code());
                                    put("pinyin_code",patient.getPinyin_code());put("note",patient.getNote());
                                }
                            })));

        } catch (XmlRpcException e) {
            e.printStackTrace();
            System.out.println("添加患者基础信息失败");
            return null;
        }
        for(int i=0;i<patientLines.size();i++){
            if(patientLines.get(i).getRecorder()==null){
                patientLines.get(i).setRecorder("");
            }
            addPatientsLine(patientLines.get(i),id);
        }

        return id;
    }

    public Object getPatient( String code,Integer page){
        final  int start=(page-1)*10;
        int total =0;
        List<Object> patients=null;
        try {
            patients = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "res.partner", "search_read",
                            Arrays.asList(Arrays.asList(
                                    // 设置查询条件

                                    Arrays.asList("customer", "=", true),
                                    "|","|","|",
                                    Arrays.asList("wubi_code","like",code),
                                    Arrays.asList("pinyin_code","like",code),
                                    Arrays.asList("name","like",code),
                                    Arrays.asList("phone","like",code)
                            )),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields", fields);
                                    { put("offset",start); put("limit", 10); }
                                }
                            })));
           total = (Integer)client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "res.partner", "search_count",
                            Arrays.asList(Arrays.asList(
                                    // 设置查询条件
                                    Arrays.asList("customer", "=", true),
                                    "|","|","|",
                                    Arrays.asList("wubi_code","like",code),
                                    Arrays.asList("pinyin_code","like",code),
                                    Arrays.asList("name","like",code),
                                    Arrays.asList("phone","like",code)))));

        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        List<Object> patientLine=null;

        List<Object> allpatienWitntLine=new ArrayList<Object>();
        for(int n=0;n<patients.size();n++){
            JsonObject json=new JsonParser().parse(gson.toJson(patients.get(n))).getAsJsonObject();
            patientLine=getPatientsLine(json.get("id").getAsInt());
            Map patientwithLine=new HashMap();
            patientwithLine.put("base",patients.get(n));
            // allpatienWitntLine.add(people.get(n));
            patientwithLine.put("allergic",patientLine);
            allpatienWitntLine.add(patientwithLine);
        }

        Map map = new HashMap();
        map.put("data",allpatienWitntLine);
        map.put("total",total);
        return map;
    }

    public Object getPatient( String phone){

        List<Object> patients=null;
        try {
            patients = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "res.partner", "search_read",
                            Arrays.asList(Arrays.asList(
                                    // 设置查询条件

                                    Arrays.asList("phone","=",phone),
                                    Arrays.asList("customer", "=", true))),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields", fields);
                                    put("limit", 100);
                                }
                            })));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        int total = patients.size();
        Map map = new HashMap();
        map.put("data",patients);
        map.put("total",total);
        return map;
    }


    public Object getThePatien(Integer id){

        List<Object> patien=null;
        try {
            patien = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "res.partner", "search_read",
                            Arrays.asList(Arrays.asList(
                                    // 设置查询条件

                                    Arrays.asList("id","=",id),
                                    Arrays.asList("customer", "=", true))),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields", fields);
                                    put("limit", 100);
                                }
                            })));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        List<Object> patientLine=getPatientsLine(id);
        Map map=new HashMap();
        map.put("base",patien.get(0));
        map.put("allergic",patientLine);
;
        return map;
    }

    public String deletePatient( final Integer id) {


        try {
            client.execute("execute_kw", Arrays.asList(
                    DB, USERNAME, PASS,
                    "res.partner", "unlink",
                    Arrays.asList(Arrays.asList(id))));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }

        return "success";
    }

    public String putPatient(final Patient patient) {

        try {
            client.execute("execute_kw", Arrays.asList(
                    DB, USERNAME, PASS,
                    "res.partner", "write",
                    Arrays.asList(
                            Arrays.asList(patient.getId()),
                            new HashMap() {{put("name", patient.getName());put("age", patient.getAge());
                                put("phone", patient.getPhone());put("job", patient.getJob());
                                put("street", patient.getStreet());put("sex", patient.getSex());
                                put("birthday",patient.getBirthday()==null?"":patient.getBirthday().toString());
                                put("card",patient.getCard());put("nation",patient.getNation());
                                put("marriage",patient.getMarriage());put("linkman",patient.getLinkman());
                                put("somatoplasm",patient.getSomatoplasm());put("registrant",patient.getRegistrant());
                                put("wubi_code",patient.getWubi_code());put("pinyin_code",patient.getPinyin_code());
                                put("note",patient.getNote());}})));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        List<Object> deletepatientLines=getPatientsLine(patient.getId());
        for(int i=0;i<deletepatientLines.size();i++){
            JsonObject json=new JsonParser().parse(gson.toJson(deletepatientLines.get(i))).getAsJsonObject();
            deletePatientsLine(json.get("id").getAsInt());
        }

        List<PatientLine> patientLines=patient.getPatientLines();
        for(int i=0;i<patientLines.size();i++){
            if(patientLines.get(i).getRecorder()==null){
                patientLines.get(i).setRecorder("");
            }
            addPatientsLine(patientLines.get(i),patient.getId());
        }
        return "success";
    }


    public Object getPage( Integer active) {
        int len = 10;
        // 患者列表
        List<Object> patients = null;
        try {
            patients = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "res.partner", "search_read",
                            Arrays.asList(Arrays.asList(
                                    // 设置查询条件
                                    Arrays.asList("customer", "=", true))),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields",fields);
                                    put("limit", 100);
                                }
                            })));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }


        List<Object> people = new ArrayList();
        int i = active;
        int j;
        if (patients.size() != 0) {

            int total = patients.size();
            if (i <= total)
                for (j = len * (i - 1); j < len * i && j < total; j++) {

                    people.add(patients.get(j));

                }
            List<Object> patientLine=null;

            List<Object> allpatienWitntLine=new ArrayList<Object>();
            for(int n=0;n<people.size();n++){
                JsonObject json=new JsonParser().parse(gson.toJson(people.get(n))).getAsJsonObject();
                patientLine=getPatientsLine(json.get("id").getAsInt());
                Map patientwithLine=new HashMap();
                patientwithLine.put("base",people.get(n));
               // allpatienWitntLine.add(people.get(n));
                patientwithLine.put("allergic",patientLine);
                allpatienWitntLine.add(patientwithLine);
            }
            Map map = new HashMap();
            map.put("data", allpatienWitntLine);
            map.put("total", total);

            //people.add(page_num);
            return map;
        }

        return "无患者信息";

    }

    public  Object getAllergic_history(String name){

        List<Object> allergic_history = null;
        try {
            allergic_history = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "allergic.history", "search_read",
                            Arrays.asList(Arrays.asList(

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
        return allergic_history;

    }

    public List<Object> getPatientsLine(Integer p_id) {

        // 客户列表
        List<Object> PatientsLine = null;
        try {
            PatientsLine = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "patient.line", "search_read",
                            Arrays.asList(Arrays.asList(
                                    Arrays.asList("order", "=", p_id)
                            )),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields", fields);
                                    put("limit", 100);
                                }
                            })));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return PatientsLine;
    }

    public String deletePatientsLine(Integer id) {


        try {
            client.execute("execute_kw", Arrays.asList(
                    DB, USERNAME, PASS,
                    "patient.line", "unlink",
                    Arrays.asList(Arrays.asList(id))));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return  "success";
    }
    public String addPatientsLine(final PatientLine patientLine,final int order) {

        try {
            Integer id = (Integer) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "patient.line", "create",
                            Arrays.asList(new HashMap(){{
                                put("order", order);
                                put("recorder", patientLine.getRecorder());
                                put("drug_id", patientLine.getDrug_id());
                                put("drug_allergic", patientLine.getDrug_allergic());
                                put("key", patientLine.getType());
                            }})));



        } catch (XmlRpcException e) {
            e.printStackTrace();
            return "添加过敏信息失败";
        }
        return "success";
    }
    public String putPatientsLine(final PatientLine patientLine) {

        try {
            Integer id = (Integer) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "patient.line", "write",
                            Arrays.asList(
                                    Arrays.asList(patientLine.getId()),
                                    new HashMap(){{
                                        put("order", patientLine.getOrder());
                                        put("recorder","");
                                        put("drug_id", patientLine.getDrug_id());
                                        put("drug_allergic", patientLine.getDrug_allergic());
                                        put("key", patientLine.getKey());
                            }})));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
            return "success";
    }
    //另外添加唯一字段
    public List<Object> keyOne(List<Object> list){
        List<Object> other = new ArrayList<Object>();
        for(int n=0;n<list.size();n++){
            JsonObject json=new JsonParser().parse(gson.toJson(list.get(n))).getAsJsonObject();
            String key=json.get("id").getAsString();
           // json.add("key",key);


        }
        return other;
    }



    }
