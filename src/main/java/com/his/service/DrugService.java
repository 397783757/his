package com.his.service;

import com.his.common.XRClient;
import com.his.model.Drug;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Created by Administrator on 2016-11-11.
 */
@Service
public class DrugService {
    XmlRpcClient client = XRClient.getXmlRpcClient();
    public final String DB = XRClient.DB;
    public final int USERNAME = XRClient.USERNAME;
    public final String PASS = XRClient.PASS;

    List fields= Arrays.asList("id", "name_template", "code", "prin", "ratio","source","big_unit","allergic_history","bar_code",
            "pinyin_code","wubi_code","custom_code","drug_content","drug_type","drug_spec","small_unit","note",
            "big_price","small_price","medicine_type","bbuy_price","sbuy_price","drug_classification","charge_classification",
            "c_charge_classification","usages","use_level","times","days","manufacturer","address","license_number","brand",
            "Latin","drug_category","minchen","star_date","organization_code","social_security","drip","internal","surgery",
            "gynaecology","recovered","new_medicine","recent_medicine","stock_medicine","new_medicine_text","recent_medicine_text",
            "stock_medicine_text","qty_available","uom_po_id","content_unit","stop","unit_content");


    public List getDrugs() {
        // 药品列表
        List<Object> medicines = null;

        try {
            medicines = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "product.product", "search_read",
                            Arrays.asList(Arrays.asList(
                                    Arrays.asList("purchase_ok", "=", true),
                                    Arrays.asList("sale_ok", "=", true))),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields", fields);
                                }
                            })));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }

        return medicines;
        // return gson.toJson(medicines);
    }

    public Integer postDrug(final Drug drug) {
        Integer id=0;
        try {
             id = (Integer) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "product.product", "create",
                            Arrays.asList(new HashMap() {{
                                put("name", drug.getName());put("code", drug.getCode());put("prin", drug.getPrin());put("ratio", drug.getRatio());
                                put("source",drug.getSource());put("big_unit",drug.getBig_unit());put("allergic_history",drug.getAllergic_history());
                                put("bar_code",drug.getBar_code());put("pinyin_code",drug.getPinyin_code());put("wubi_code",drug.getWubi_code());
                                put("custom_code",drug.getCustom_code());put("drug_content",drug.getDrug_content());
                                put("drug_type",drug.getDrug_type());put("drug_spec",drug.getDrug_spec());put("small_unit",drug.getSmall_unit());
                                put("note",drug.getNote());put("big_price",drug.getBig_price());put("small_price",drug.getSmall_price());
                                put("medicine_type",drug.getMedicine_type());put("bbuy_price",drug.getBbuy_price());put("sbuy_price",drug.getSbuy_price());
                                put("drug_classification",drug.getDrug_classification());put("charge_classification",drug.getCharge_classification());put("c_charge_classification",drug.getC_charge_classification());
                                put("usages",drug.getUsages());put("use_level",drug.getUse_level());put("times",drug.getTimes());put("days",drug.getDays());
                                put("manufacturer",drug.getManufacturer());put("address",drug.getAddress());put("license_number",drug.getLicense_number());
                                put("brand",drug.getBrand());put("Latin",drug.getLatin());put("drug_category",drug.getDrug_category());
                                put("minchen",drug.getMinchen());put("star_date",drug.getStar_date());
                                put("organization_code",drug.getOrganization_code());put("social_security",drug.getSocial_security());put("drip",drug.getDrip());
                                put("internal",drug.getInternal());put("surgery",drug.getSurgery());put("gynaecology",drug.getGynaecology());
                                put("recovered",drug.getRecovered());put("new_medicine",drug.getNew_medicine());put("recent_medicine",drug.getRecent_medicine());
                                put("stock_medicine",drug.getStock_medicine());put("new_medicine_text",drug.getNew_medicine_text());put("recent_medicine_text",drug.getRecent_medicine_text());
                                put("stock_medicine_text",drug.getStock_medicine_text());put("content_unit",drug.getContent_unit());
                            }})));



        } catch (XmlRpcException e) {
            e.printStackTrace();
            return null;
        }

        //drug.setId(id);

        return id;
    }


    public Map getDrug( String orgCode,String name, Integer page) {
        final  int start=(page-1)*10;
        int total=0;
        List<Object> medicines=null;
        try {
            medicines = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "product.product", "search_read",
                            Arrays.asList(Arrays.asList(
                                    // 设置查询条件
                                    Arrays.asList("purchase_ok", "=", true),
                                    Arrays.asList("sale_ok", "=", true),"|",
                                    Arrays.asList("organization_code", "=", "*"),
                                    Arrays.asList("organization_code", "like", orgCode),
                                    "|","|",
                                    Arrays.asList("wubi_code","like",name),
                                    Arrays.asList("pinyin_code","like",name),
                                    Arrays.asList("name_template", "like", name)
                            )),


                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields",fields);

                                    { put("offset",start); put("limit", 10);}
                                }
                            })));
            total=(Integer)client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "product.product", "search_count",
                            Arrays.asList(Arrays.asList(
                                    // 设置查询条件


                                    Arrays.asList("purchase_ok", "=", true),
                                    Arrays.asList("sale_ok", "=", true),"|",
                                    Arrays.asList("organization_code", "=", "*"),
                                    Arrays.asList("organization_code", "like", orgCode),
                                    "|","|",
                                    Arrays.asList("wubi_code","like",name),
                                    Arrays.asList("pinyin_code","like",name),
                                    Arrays.asList("name_template", "like", name)
                            ))));


        } catch (XmlRpcException e) {
            e.printStackTrace();

        }

        Map map = new HashMap();
        map.put("data",medicines);
        map.put("total",total);
        return map;
    }


    public Object getTheDrug(Integer id) {
        List<Object> medicines=null;
        try {
            medicines = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "product.product", "search_read",
                            Arrays.asList(Arrays.asList(
                                    // 设置查询条件

                                    Arrays.asList("id", "=", id),

                                    Arrays.asList("purchase_ok", "=", true),
                                    Arrays.asList("sale_ok", "=", true))),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields",fields);

                                    put("limit", 10);
                                }
                            })));


        } catch (XmlRpcException e) {
            e.printStackTrace();

        }
       if(medicines==null||medicines.size()<1){
           return "找不到该药品";
       }

        return medicines.get(0);
    }


    public String deleteDrug(final Integer id) {
        try {
            client.execute("execute_kw", Arrays.asList(
                    DB, USERNAME, PASS,
                    "product.product", "unlink",
                    Arrays.asList(Arrays.asList(id))));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return "success";
    }


    public String putDrug(final Integer id,final Drug drug) {

        try {
            client.execute("execute_kw", Arrays.asList(
                    DB, USERNAME, PASS,
                    "product.product", "write",
                    Arrays.asList(
                            Arrays.asList(id),
                            new HashMap() {{
                                put("name", drug.getName());put("code", drug.getCode());put("prin", drug.getPrin());
                                put("source",drug.getSource());put("ratio", drug.getRatio());put("big_unit",drug.getBig_unit());put("allergic_history",drug.getAllergic_history());
                                put("bar_code",drug.getBar_code());put("pinyin_code",drug.getPinyin_code());put("wubi_code",drug.getWubi_code());
                                put("custom_code",drug.getCustom_code());put("drug_content",drug.getDrug_content());
                                put("drug_type",drug.getDrug_type());put("drug_spec",drug.getDrug_spec());put("small_unit",drug.getSmall_unit());
                                put("note",drug.getNote());put("big_price",drug.getBig_price());put("small_price",drug.getSmall_price());
                                put("medicine_type",drug.getMedicine_type());put("bbuy_price",drug.getBbuy_price());put("sbuy_price",drug.getSbuy_price());
                                put("drug_classification",drug.getDrug_classification());put("charge_classification",drug.getCharge_classification());put("c_charge_classification",drug.getC_charge_classification());
                                put("usages",drug.getUsages());put("use_level",drug.getUse_level());put("times",drug.getTimes());put("days",drug.getDays());
                                put("manufacturer",drug.getManufacturer());put("address",drug.getAddress());put("license_number",drug.getLicense_number());
                                put("brand",drug.getBrand());put("Latin",drug.getLatin());put("drug_category",drug.getDrug_category());
                                put("minchen",drug.getMinchen());put("star_date",drug.getStar_date());
                                put("organization_code",drug.getOrganization_code());put("social_security",drug.getSocial_security());put("drip",drug.getDrip());
                                put("internal",drug.getInternal());put("surgery",drug.getSurgery());put("gynaecology",drug.getGynaecology());
                                put("recovered",drug.getRecovered());put("new_medicine",drug.getNew_medicine());put("recent_medicine",drug.getRecent_medicine());
                                put("stock_medicine",drug.getStock_medicine());put("new_medicine_text",drug.getNew_medicine_text());put("recent_medicine_text",drug.getRecent_medicine_text());
                                put("stock_medicine_text",drug.getStock_medicine_text()); put("content_unit",drug.getContent_unit());
                            }})));

        } catch (XmlRpcException e) {
            e.printStackTrace();
        }

        return "success";
    }

    public Map getDrugsPage(Integer page,String orgCode) {

        final  int start=(page-1)*10;
        int total = 0;

        // 药品分页列表
        List<Object> medicines = null;
        try {
            medicines = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "product.product", "search_read",
                            Arrays.asList(Arrays.asList(
                                    Arrays.asList("purchase_ok", "=", true),
                                    Arrays.asList("sale_ok", "=", true),"|",
                                    Arrays.asList("organization_code", "=", "*"),
                                    Arrays.asList("organization_code", "like", orgCode)
                            )),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields",fields);

                                    { put("offset",start); put("limit", 10); }
                                }
                            })));
            total = (Integer)client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "product.product", "search_count",
                            Arrays.asList(Arrays.asList(
                                    // 设置查询条件
                                    Arrays.asList("purchase_ok", "=", true),
                                    Arrays.asList("sale_ok", "=", true),"|",
                                    Arrays.asList("organization_code", "=", "*"),
                                    Arrays.asList("organization_code", "like", orgCode)

                            ))));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }

        Map map = new HashMap();
        map.put("data",medicines);
        map.put("total",total);
        return map;
    }
    public Object getDrugAllergic( String name) {

        int total=0;
        List<Object> medicines=null;
        try {
            medicines = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "product.product", "search_read",
                            Arrays.asList(Arrays.asList(
                                    // 设置查询条件
                                    Arrays.asList("purchase_ok", "=", true),
                                    Arrays.asList("sale_ok", "=", true),
                                    "|","|",
                                    Arrays.asList("wubi_code","like",name),
                                    Arrays.asList("pinyin_code","like",name),
                                    Arrays.asList("name", "like", name)
                            )),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields", Arrays.asList("id", "name"));
                                }
                            })));

        } catch (XmlRpcException e) {
            e.printStackTrace();

        }
        return medicines;
    }

    public List getRecent_medicine(String orgCode) {

        // 近期药品列表
        List<Object> medicines = null;
        try {
            medicines = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "product.product", "search_read",
                            Arrays.asList(Arrays.asList(
                                    Arrays.asList("purchase_ok", "=", true),
                                    Arrays.asList("sale_ok", "=", true),
                                    Arrays.asList("recent_medicine_text", "like", orgCode),
                                    Arrays.asList("recent_medicine", "=", true)
                                    //Arrays.asList("stock_medicine_text", "like", code),
                                    //Arrays.asList("new_medicine_text", "like", code)
                            )),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields", fields);
                                }
                            })));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return medicines;
        // return gson.toJson(medicines);
    }

    public List<Object> getStock_medicine(String orgCode) {

        // 大库存药品列表
        List<Object> medicines = null;
        try {
            medicines = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "product.product", "search_read",
                            Arrays.asList(Arrays.asList(
                                    Arrays.asList("purchase_ok", "=", true),
                                    Arrays.asList("sale_ok", "=", true),
                                    //Arrays.asList("recent_medicine_text", "like", code),
                                    // Arrays.asList("recent_medicine", "=", true)
                                    Arrays.asList("stock_medicine_text", "like", orgCode),
                                    Arrays.asList("stock_medicine", "=", true)
                                    //Arrays.asList("new_medicine_text", "like", code)
                            )),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields",fields);
                                }
                            })));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return medicines;
        // return gson.toJson(medicines);
    }

    public List getNew_medicine(String orgCode) {

        // 新药列表
        List<Object> medicines = null;
        try {
            medicines = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "product.product", "search_read",
                            Arrays.asList(Arrays.asList(
                                    Arrays.asList("purchase_ok", "=", true),
                                    Arrays.asList("sale_ok", "=", true),
                                    //Arrays.asList("recent_medicine_text", "like", code),
                                    // Arrays.asList("recent_medicine", "=", true)
                                    //Arrays.asList("stock_medicine_text", "like", code),
                                    //Arrays.asList("stock_medicine", "=", true)
                                    Arrays.asList("new_medicine_text", "like", orgCode),
                                    Arrays.asList("new_medicine", "=", true)
                            )),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields",fields);
                                }
                            })));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return medicines;
        // return gson.toJson(medicines);
    }
}
