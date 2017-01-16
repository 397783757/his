package com.his.service;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.his.common.XRClient;
import com.his.mapper.PreMapper;
import com.his.model.*;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

/**
 * Created by Administrator on 2016-10-08.
 */
@Service
public class PrescriptionService {
    //连接odoo数据库

    XmlRpcClient client = XRClient.getXmlRpcClient();
    public final String DB = XRClient.DB;
    public final int USERNAME = XRClient.USERNAME;
    public final String PASS = XRClient.PASS;
    @Autowired
    PatientService patientService;
    @Autowired
    PreMapper preMapper;

    List fields=  Arrays.asList("id", "name", "doctor", "partner_id", "department", "doctor_licence",
            "d_time", "sex", "age", "street", "birthday", "phone", "note2", "note1",
            "diagnostic_type","times","readme","content","main_description",
            "nowill_description","historyill_description","personal_description","familyill_history",
            "doctor_advice","diagnose","prescription_type","state","allergic_note"
            ,"amount_total","org_code");
    List lineFields= Arrays.asList("standard","price_unit","name","order_id","price_total","discount",
            "product_uom_qty","product_uom","product_id","dosis","use_level","content_unit",
            "usages","times","day","total","price_total","groups","pair","remark"
            ,"unit_content");

    public List getPrescription() throws Exception {
        // 处方列表
        List<Object> detail=null;
        List<Object> list=new ArrayList<>();
        List<Object> prescriptions = Arrays.asList((Object[]) client.execute(
                "execute_kw", Arrays.asList(
                        DB, USERNAME, PASS, "sale.order", "search_read",
                        Arrays.asList(Arrays.asList(
                                // 设置查询条件
                                
                                "|", Arrays.asList("state", "=", "sale") ,
                                Arrays.asList("state","like","done")
                        )),
                        new HashMap() {
                            {// 查询字段, 限定最多返回100条记录
                                put("fields", fields);

                            }
                        })
        ));
        return prescriptions;
    }

    public String postPrescription(final Prescription prescription){
        Integer id =0;

        try {
                 id = (Integer) client.execute(
                    "execute_kw", getPrescriptionInsertList(prescription));
        } catch (XmlRpcException e) {
            e.printStackTrace();
            return "添加失败";
        }

        return id.toString();
    }

    public String postTheDetailPrescription(final int orderid,final List<Detail> detailList){
        Integer detail_id;
        for (final Detail de : detailList){
            if(de.getTimes()==null){
                de.setTimes("");
            }
            try {
               detail_id = (Integer)client.execute(
                            "execute_kw", getLineInsertList(de,orderid));

            } catch (XmlRpcException e) {
                e.printStackTrace();
                return "添加失败";
            }
        }
        return "success";
    }





    public Integer postThePrescription(final Prescription base){
        Integer id = 0;
       try {
           id = (Integer) client.execute(
                   "execute_kw", getPrescriptionInsertList(base));
           System.out.println("添加基础信息成功 id="+id);
       } catch (XmlRpcException e) {
           e.printStackTrace();
           return null;
       }

       Integer detail_id;


       for (int i=0;i<base.getDetails().size();i++) {
          final Detail detail=base.getDetails().get(i);
           try {
               detail_id = (Integer) client.execute(
                       "execute_kw", getLineInsertList(detail,id));

               System.out.println("添加明细信息成功");
           } catch (XmlRpcException e) {
               e.printStackTrace();
               deletePrescription(id);
               System.out.println("添加明细信息失败");
               return null;
           }
       }

       return id;
    }

    public Object getPrescription( Integer id) {
        Gson gson=new Gson();
        List<Object> prescription=null;
        try {
            prescription=Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "sale.order", "search_read",
                            Arrays.asList(Arrays.asList(
                                    // 设置查询条件
                                   Arrays.asList("id", "=", id)
                                   //Arrays.asList("state", "=", "sale")
                            )),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields", fields);
                                    put("limit", 100);
                                }

                            })

            ));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
       if(prescription==null||prescription.size()<1){
            return  "找不到处方";
       }
        Map map= (Map) prescription.get(0);
        List<Object> detail=null;
        List<Object> SmBiUnit=null;
        List<Object> detail_count_unit=new ArrayList<Object>();

        try {
            detail=Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "sale.order.line", "search_read",
                            Arrays.asList(Arrays.asList(
                                    // 设置查询条件
                                    Arrays.asList("order_id", "=", id)
                            )),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields",lineFields);

                                    put("limit", 100);
                                }

                            })

            ));
            map.put("detail",detail);
            for(int j=0;j<detail.size();j++){
                Map detailMap=(Map) detail.get(j);
                int product_id= Integer.parseInt(Array.get(detailMap.get("product_id"),0).toString());
               // System.out.println(product_id);
                SmBiUnit=Arrays.asList((Object[]) client.execute(
                        "execute_kw", Arrays.asList(
                                DB, USERNAME, PASS, "product.product", "search_read",
                                Arrays.asList(Arrays.asList(
                                        // 设置查询条件
                                        Arrays.asList("id", "=",product_id)
                                )),
                                new HashMap() {
                                    {// 查询字段, 限定最多返回100条记录
                                        put("fields", Arrays.asList("big_unit","small_unit",
                                                "big_price","small_price","drug_type"));
                                    }

                                })

                ));

                detailMap.put("SmBiUnit",SmBiUnit.get(0));
            }
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        int patientid=Integer.parseInt(Array.get(map.get("partner_id"),0).toString());
        List<Object> patientLines=patientService.getPatientsLine(patientid);
        map.put("patientLines",patientLines);
        return map;
    }

    public String deletePrescription( final Integer id) {

        setState(id,"draft");
        try {
            client.execute("execute_kw", Arrays.asList(
                    DB, USERNAME, PASS,
                    "sale.order", "unlink",
                    Arrays.asList(Arrays.asList(id))));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return "success";
    }


    public String putPrescription(final Prescription prescription) {
        try {
            client.execute("execute_kw", Arrays.asList(
                    DB, USERNAME, PASS,
                    "sale.order", "write",
                    Arrays.asList(
                            Arrays.asList(Integer.parseInt(prescription.getId())),
                            new HashMap(){{
                                put("doctor",prescription.getDoctor());put("id",prescription.getId());
                                put("partner_id",prescription.getPartner_id());
                                put("department",prescription.getDepartment());
                                put("doctor_licence",prescription.getDoctor_licence());put("d_time",prescription.getD_time());
                                put("sex",prescription.getSex());put("age",prescription.getAge());
                                put("street",prescription.getStreet());
                                put("birthday",prescription.getBirthday());put("phone",prescription.getPhone());
                                put("note1",prescription.getNote1());put("note2",prescription.getNote2());
                                put("diagnostic_type",prescription.getDiagnostic_type());
                                put("prescription_type",prescription.getPrescription_type());
                                put("times",prescription.getTimes());
                                put("readme",prescription.getReadme());put("content",prescription.getContent());
                                put("state","sale");put("main_description",prescription.getMain_description());
                                put("nowill_description",prescription.getNowill_description());
                                put("historyill_description",prescription.getHistoryill_description());
                                put("personal_description",prescription.getPersonal_description());
                                put("familyill_history",prescription.getFamilyill_history());
                                put("doctor_advice",prescription.getDoctor_advice());
                                put("diagnose",prescription.getDiagnose());put("allergic_note",prescription.getAllergic_note());
                                put("org_code",prescription.getOrg_code());
                            }})));

        } catch (XmlRpcException e) {
            e.printStackTrace();
            return "失败";
        }
        return "success";
    }

    public String putPrescriptionof(Integer order_id,final Detail detail) {
            if(detail.getTimes()==null){
                detail.setTimes("");
            }
        try {
            client.execute("execute_kw",getLineInsertList(detail,order_id));

        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return "success";
    }

    public Map<String,Object> getPrescriptionPage(Integer page,String org_code) throws Exception {
        final  int start=(page-1)*10;
        // 处方列表
        List<Object> detail=null;
        List<Object> prescriptions = Arrays.asList((Object[]) client.execute(
                "execute_kw", Arrays.asList(
                        DB, USERNAME, PASS, "sale.order", "search_read",
                        Arrays.asList(Arrays.asList(
                                // 设置查询条件

                                "|", Arrays.asList("state", "=", "sale") ,
                                Arrays.asList("state","=","done"),
                                Arrays.asList("org_code","=",org_code)
                        )),
                        new HashMap() {
                            {// 查询字段, 限定最多返回100条记录
                                put("fields",fields);

                                { put("offset",start); put("limit", 10); }
                            }
                        })
        ));

        int total =0;
        for (int i=0;i<prescriptions.size();i++){
           // Map<String,Object> map = new HashMap();
            Map<String,Object> base = (Map)prescriptions.get(i);
            int id = Integer.valueOf(base.get("id").toString());
            try {
                detail=Arrays.asList((Object[]) client.execute(
                        "execute_kw", Arrays.asList(
                                DB, USERNAME, PASS, "sale.order.line", "search_read",
                                Arrays.asList(Arrays.asList(
                                        // 设置查询条件
                                        Arrays.asList("order_id", "=", id)
                                )),
                                new HashMap() {
                                    {// 查询字段, 限定最多返回100条记录
                                        put("fields", lineFields);


                                    }
                                })
                ));
            } catch (XmlRpcException e) {
                e.printStackTrace();
            }


            base.put("detail",detail);



        }
        Map<String,Object> result=new HashMap();
        total = (Integer)client.execute(
                "execute_kw", Arrays.asList(
                        DB, USERNAME, PASS, "sale.order", "search_count",
                        Arrays.asList(Arrays.asList(
                                // 设置查询条件
                                "|", Arrays.asList("state", "=", "sale") ,
                                Arrays.asList("state","=","done"),
                                Arrays.asList("org_code","=",org_code)
                        ))));

        result.put("total",total);
        result.put("data",prescriptions);
        return result;

    }

    public Map<String, Object> getPrescriptionByname(String name,Integer page,String org_code) {
        final int start = (page - 1) * 10;
        // 处方列表
        List<Object> prescriptions = null;
        try {
            prescriptions = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "sale.order", "search_read",
                            Arrays.asList(Arrays.asList(
                                    // 设置查询条件

                                    Arrays.asList("partner_id","like",name),"|",
                                    Arrays.asList("state", "=", "sale") ,
                                    Arrays.asList("state","=","done"),
                                    Arrays.asList("org_code","=",org_code)
                            )),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields",fields);

                                    {
                                        put("offset", start);
                                        put("limit", 10);
                                    }
                                }
                            })
            ));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }

        int total = 0;
        for (int i = 0; i < prescriptions.size(); i++) {

            Map<String, Object> base = (Map) prescriptions.get(i);
            int id = Integer.valueOf(base.get("id").toString());

            List<Object> detail =null;
            try {
                detail = Arrays.asList((Object[]) client.execute(
                        "execute_kw", Arrays.asList(
                                DB, USERNAME, PASS, "sale.order.line", "search_read",
                                Arrays.asList(Arrays.asList(
                                        // 设置查询条件
                                        Arrays.asList("order_id", "=", id)


                                )),
                                new HashMap() {
                                    {// 查询字段, 限定最多返回100条记录
                                        put("fields",lineFields);


                                    }
                                })
                ));
            } catch (XmlRpcException e) {
                e.printStackTrace();
            }

            base.put("detail", detail);
        }

        try {
            total = (Integer) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "sale.order", "search_count",
                            Arrays.asList(Arrays.asList(
                                    // 设置查询条件


                                    Arrays.asList("partner_id","like",name),"|",
                                    Arrays.asList("state", "=", "sale") ,
                                    Arrays.asList("state","=","done"),
                                    Arrays.asList("org_code","=",org_code)
                            ))));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }

        Map<String,Object> result = new HashMap();

        result.put("total",total);
        result.put("data",prescriptions);
        return result;


    }

    public String putPrescriptionAnddetail(final Prescription prescription) {
        Gson gson=new Gson();
        List<Object> deleteDetails =null;
        // Prescription base=unite.getHeader();
        deleteDetails=getDetailsByorderId(Integer.parseInt(prescription.getId()));
        List<Detail> details= prescription.getDetails();

        for(int i=0;i<deleteDetails.size();i++){
            JsonObject json=new JsonParser().parse(gson.toJson(deleteDetails.get(i))).getAsJsonObject();
            deletedetail(json.get("order_id").getAsJsonArray().get(0).getAsInt(),json.get("id").getAsInt());
        }
        String basemessage=putPrescription(prescription);
        String message=postTheDetailPrescription(Integer.parseInt(prescription.getId()),details);
        System.out.print("基础"+basemessage+" 明细"+message);
        return "success";
    }

    public String deletedetail( final Integer order_id,final Integer id) {
        setState(order_id,"draft");
        try {
            client.execute("execute_kw", Arrays.asList(
                    DB, USERNAME, PASS,
                    "sale.order.line", "unlink",
                    Arrays.asList(id)));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        setState(order_id,"sale");
        return "success";
    }

    public List<Object> getDetailsByorderId(Integer order_id) {
        List<Object> deleteDetails =null;
        try {
            deleteDetails=Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "sale.order.line", "search_read",
                            Arrays.asList(Arrays.asList(
                                    // 设置查询条件
                                    Arrays.asList("order_id", "=", order_id)
                            )),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields",lineFields);


                                }
                            })
            ));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return deleteDetails;
    }
    //修改订单状态
    public String setState(final int id,final  String state) {

        try {
            client.execute("execute_kw", Arrays.asList(
                    DB, USERNAME, PASS,
                    "sale.order", "write",
                    Arrays.asList(
                            Arrays.asList(id),
                            new HashMap() {{

                              put("state",state);

                            }})));

        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return "success";
    }

    public List getPrescriptionInsertList(final Prescription prescription){
        List Insertlist=Arrays.asList(

                DB, USERNAME, PASS, "sale.order", "create",
                Arrays.asList(new HashMap(){{
                    put("doctor",prescription.getDoctor());put("id",prescription.getId());
                    put("partner_id",prescription.getPartner_id());
                    put("department",prescription.getDepartment());
                    put("doctor_licence",prescription.getDoctor_licence());put("d_time",prescription.getD_time());
                    put("sex",prescription.getSex());put("age",prescription.getAge());
                    put("street",prescription.getStreet());
                    put("birthday",prescription.getBirthday());put("phone",prescription.getPhone());put("note1",prescription.getNote1());
                    put("note2",prescription.getNote2());
                    put("diagnostic_type",prescription.getDiagnostic_type());
                    put("prescription_type",prescription.getPrescription_type());
                    put("times",prescription.getTimes());put("name",prescription.getName());
                    put("readme",prescription.getReadme());put("content",prescription.getContent());
                    put("state","sale");put("main_description",prescription.getMain_description());
                    put("nowill_description",prescription.getNowill_description());
                    put("historyill_description",prescription.getHistoryill_description());
                    put("personal_description",prescription.getPersonal_description());
                    put("familyill_history",prescription.getFamilyill_history());
                    put("doctor_advice",prescription.getDoctor_advice());
                    put("diagnose",prescription.getDiagnose());put("allergic_note",prescription.getAllergic_note());
                    put("org_code",prescription.getOrg_code());
                }})

        );
        return Insertlist;
    }

    public List getLineInsertList(final Detail detail,final Integer id){
        List insertList= Arrays.asList(
                DB, USERNAME, PASS, "sale.order.line", "create",
                Arrays.asList(new HashMap() {{
                    put("product_id", detail.getProduct_id());
                    put("price_unit", detail.getPrice_unit());
                    put("product_uom_qty",detail.getProduct_uom_qty());
                    put("product_uom", detail.getProduct_uom());
                    put("dosis", detail.getDosis());
                    put("use_level", detail.getUse_level());
                    put("content_unit", detail.getContent_unit());
                    put("unit_content",detail.getUnit_content());
                    put("usages", detail.getUsages());
                    put("times",detail.getTimes());
                    put("day",detail.getDay());
                    put("order_id", id);
                    put("standard", detail.getStandard());
                    put("groups", detail.getGroups());
                    put("pair",detail.getPair());
                    put("remark",detail.getRemark());
                }}));
        return insertList;
    }



}
