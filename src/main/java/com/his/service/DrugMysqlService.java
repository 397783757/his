package com.his.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.his.mapper.DrugMapper;
import com.his.model.Drug;
import com.his.model.DrugUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-12-06.
 */
@Service
public class DrugMysqlService {

    @Autowired
    private DrugMapper drugMapper;
    public Drug selectDrugById(String id){
        return drugMapper.selectDrugById(id);
    }
    public void insertDrug(Drug drug){
        drugMapper.insertDrug(drug);
    }
    public List<DrugUsage> selectDrug_usage(String type){
        return drugMapper.selectDrug_usage(type);
    }
    public void deleteAllDrug(){
        drugMapper.deleteAllDrug();
    }
    public void insertAll(List<Object> list){
        for(int j=0;j<list.size();j++){
           Map map= (Map<String,Object>) list.get(j);
            Drug drug=toDrug(map);
            drugMapper.insertDrug(drug);
        }
    }
    public Drug toDrug(Map<String,Object> map){
        Drug drug=new Drug();

        drug.setStock_medicine((Boolean) map.get("stock_medicine"));
        if(map.get("content_unit").toString()!="false"){
            drug.setContent_unit(Array.get(map.get("content_unit"),0).toString());
            drug.setContent_unit_name(Array.get(map.get("content_unit"),1).toString());
        }
        if(map.get("unit_content").toString()!="false"){
            drug.setUnit_content(map.get("unit_content").toString());
        }
        if(map.get("big_unit").toString()!="false"){
            drug.setBig_unit(Array.get(map.get("big_unit"),0).toString());
            drug.setBig_unit_name(Array.get(map.get("big_unit"),1).toString());
        }
        if(map.get("small_unit").toString()!="false"){
            drug.setSmall_unit(Array.get(map.get("small_unit"),0).toString());
            drug.setSmall_unit_name(Array.get(map.get("small_unit"),1).toString());
        }
        drug.setGynaecology((Boolean) map.get("gynaecology"));
        if(map.get("license_number").toString()!="false"){
            drug.setLicense_number( map.get("license_number").toString());

        }
        if(map.get("organization_code").toString()!="false"){
        drug.setOrganization_code(map.get("organization_code").toString());
        }

        drug.setSurgery((Boolean) map.get("surgery"));
        drug.setStop((Boolean) map.get("stop"));

        if(map.get("drug_type").toString()!="false"){
            drug.setDrug_type(Array.get(map.get("drug_type"),0).toString());
        }
        if(map.get("big_price").toString()!="false"){
            drug.setBig_price(map.get("big_price").toString());
        }
        if(map.get("bbuy_price").toString()!="false"){
            drug.setBbuy_price(map.get("bbuy_price").toString());
        }
        if(map.get("medicine_type").toString()!="false"){
            drug.setMedicine_type(map.get("medicine_type").toString());
        }
            drug.setSocial_security((Boolean) map.get("social_security"));
        if(map.get("custom_code").toString()!="false"){
            drug.setCustom_code(map.get("custom_code").toString());
        }
        if(map.get("drug_spec").toString()!="false"){
            drug.setStandard(map.get("drug_spec").toString());
        }
        if(map.get("code").toString()!="false"){
            drug.setCode(map.get("code").toString());
        }

            drug.setPrin((Boolean)map.get("prin"));

        if(map.get("ratio").toString()!="false"){
            drug.setRatio(map.get("ratio").toString());
        }
        if(map.get("manufacturer").toString()!="false"){
            drug.setManufacturer(map.get("manufacturer").toString());
        }
        if(map.get("new_medicine_text").toString()!="false"){
            drug.setNew_medicine_text(map.get("new_medicine_text").toString());
        }
        if(map.get("usages").toString()!="false"){
            drug.setUsages(Array.get(map.get("usages"),0).toString());
        }
        if(map.get("drug_content").toString()!="false"){
            drug.setDrug_content(map.get("drug_content").toString());
        }
        if(map.get("use_level").toString()!="false"){
            drug.setUse_level(map.get("use_level").toString());
        }
        if(map.get("sbuy_price").toString()!="false"){
            drug.setSbuy_price(map.get("sbuy_price").toString());
        }
        if(map.get("id").toString()!="false"){
            drug.setId(Integer.parseInt(map.get("id").toString()));
        }

            drug.setDrip((Boolean) map.get("drip"));

        if(map.get("times").toString()!="false"){
            drug.setTimes(Array.get(map.get("times"),0).toString());
        }
        if(map.get("small_price").toString()!="false"){
            drug.setSmall_price(map.get("small_price").toString());
        }
        if(map.get("name_template").toString()!="false"){
            drug.setName(map.get("name_template").toString());
        }

        drug.setInternal((Boolean) map.get("internal"));
        if(map.get("note").toString()!="false"){
            drug.setNote(map.get("note").toString());
        }
        if(map.get("bar_code").toString()!="false"){
            drug.setBar_code(map.get("bar_code").toString());
        }
        drug.setNew_medicine((Boolean) map.get("new_medicine"));
        drug.setRecent_medicine((Boolean) map.get("recent_medicine"));

        if(map.get("minchen").toString()!="false"){
            drug.setMinchen(map.get("minchen").toString());
        }
        if(map.get("drug_category").toString()!="false"){
            drug.setDrug_category(map.get("drug_category").toString());
        }
        if(map.get("stock_medicine_text").toString()!="false"){
            drug.setStock_medicine_text(map.get("stock_medicine_text").toString());
        }
        drug.setRecovered((Boolean) map.get("recovered"));
        if(map.get("days").toString()!="false"){
            drug.setDays(map.get("days").toString());
        }

        if(map.get("source").toString()!="false"){
            drug.setSource(map.get("source").toString());
        }
        if(map.get("address").toString()!="false"){
            drug.setAddress(map.get("address").toString());
        }
        if(map.get("recent_medicine_text").toString()!="false"){
            drug.setRecent_medicine_text(map.get("recent_medicine_text").toString());
        }
        if(map.get("brand").toString()!="false"){
            drug.setBrand(map.get("brand").toString());
        }
        if(map.get("drug_classification").toString()!="false"){

            drug.setDrug_classification(Array.get(map.get("drug_classification"),0).toString());
        }
        if(map.get("allergic_history").toString()!="false"){

            drug.setAllergic_history(Array.get(map.get("allergic_history"),0).toString());
        }
        if(map.get("charge_classification").toString()!="false"){

            drug.setCharge_classification(Array.get(map.get("charge_classification"),0).toString());
        }
        if(map.get("c_charge_classification").toString()!="false"){

            drug.setC_charge_classification(Array.get(map.get("c_charge_classification"),0).toString());
        }
        if(map.get("star_date").toString()!="false"){

            drug.setStar_date(map.get("star_date").toString());
        }

        return drug;
    }
}
