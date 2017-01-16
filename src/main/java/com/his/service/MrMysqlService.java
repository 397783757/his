package com.his.service;

import com.his.mapper.MrMapper;
import com.his.model.DiseaseData;
import com.his.model.Icd_diagnosis;
import com.his.model.MedicalRecord;
import com.his.model.MrCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-12-09.
 */
@Service
public class MrMysqlService {

    @Autowired
    private MrMapper mrMapper;

    public List<MrCategory> getMrCategory(String type){
        return mrMapper.selectByType(type);
    }

    public List<MrCategory> getTemplate(String category_id){
        return mrMapper.selectMrTemplate(category_id);
    }

    public void addCategory(MrCategory mrCategory){
        mrMapper.insertCategory(mrCategory);
        if(mrCategory.getT_name()!=null&mrCategory.getT_name().trim()!=""){
            mrCategory.setCategory_id(mrCategory.getId());
            mrMapper.insertTemplate(mrCategory);
        }
        if(mrCategory.getContent()!=null&mrCategory.getContent().trim()!=""){
            mrCategory.setCategory_id(mrCategory.getId());
            mrMapper.insertTemplate(mrCategory);
        }
    }

    public void addTemplate(MrCategory mrCategory){
        mrMapper.insertTemplate(mrCategory);
    }

    public void deleteMrTemplate(String  id){
        mrMapper.deleteTemplateByPrimaryKey(id);
    }

    public void deleteCategory(String  id){
        mrMapper.deleteCategoryByPrimaryKey(id);
        mrMapper.deleteTemplateByCategory(id);
    }

    public void updateTemplateByPrimaryKey(MrCategory mrCategory){
        mrMapper.updateTemplateByPrimaryKey(mrCategory);
    }
    public void updateCategoryByPrimaryKey(MrCategory mrCategory){
        mrMapper.updateCategoryByPrimaryKey(mrCategory);
        if(mrCategory.getT_id()!=null&&mrCategory.getT_id().trim()!="") {
            mrMapper.updateTemplateByPrimaryKey(mrCategory);
        }
    }
    public List<MrCategory> selectCategoryByCode(String code,String type){
        Map map=new HashMap();
        map.put("code",code);
        map.put("type",type);
        return mrMapper.selectCategoryByCode(map);
    }

    public List<DiseaseData> selectIcdBylevel(String level){
        return mrMapper.selectIcdBylevel(level);
    }

    public List<DiseaseData> selectIcdByCode(String code){
        return mrMapper.selectIcdByCode(code);
    }

    public MedicalRecord selectIcdMedicalRecordById(String  mr_id){

        return mrMapper.selectIcdMedicalRecordById(mr_id);
    }

    public String insertMedicalRecord(MedicalRecord medicalRecord){
        mrMapper.insertMedicalRecord(medicalRecord);
        Icd_diagnosis[] icd_diagnosises=medicalRecord.getIcd_diagnosises();
        if(icd_diagnosises!=null&&icd_diagnosises.length>0){
            for(int i=0;i<icd_diagnosises.length;i++){
                icd_diagnosises[i].setMr_id(medicalRecord.getMr_id());
                mrMapper.inserticd_diagnosis(icd_diagnosises[i]);
            }
        }
        return medicalRecord.getMr_id();
    }

    public List<MedicalRecord> selectIcdMedicalRecordBypartner_id(String partner_id){

        return mrMapper.selectIcdMedicalRecordBypartner_id(partner_id);
    }

    public String updateMedicalRecord(MedicalRecord medicalRecord){
        mrMapper.deleteicd_diagnosis(medicalRecord.getId());
        mrMapper.updateMedicalRecord(medicalRecord);
        Icd_diagnosis[] icd_diagnosises=medicalRecord.getIcd_diagnosises();
        if(icd_diagnosises!=null&&icd_diagnosises.length>0){
            for(int i=0;i<icd_diagnosises.length;i++){
                icd_diagnosises[i].setMr_id(medicalRecord.getId());
                mrMapper.inserticd_diagnosis(icd_diagnosises[i]);
            }
        }
        return medicalRecord.getId();
    }

    public List<MedicalRecord> selectIcdMedicalRecord(){

        return mrMapper.selectIcdMedicalRecord();
    }

    public Map selectMedicalRecord(Integer start,String org_code){
        Map map=new HashMap();
        Map param=new HashMap();
        param.put("start",start);
        param.put("org_code",org_code);
        map.put("data",mrMapper.selectMedicalRecord(param));
        map.put("total",mrMapper.countMedicalRecord(param));
        return  map;
    }

    public Map selectMedicalRecordByname(String partner_name ,Integer start,String org_code){
        Map selectMap=new HashMap();
        selectMap.put("partner_name",partner_name);
        selectMap.put("start",start);
        selectMap.put("org_code",org_code);
        Map map=new HashMap();
        map.put("data",mrMapper.selectMedicalRecordByname(selectMap));
        map.put("total",mrMapper.countMedicalRecord(selectMap));
        return  map;
    }

    public String deleteMedicineRecord(Integer id) {
        String mr_id=id.toString();
        mrMapper.deleteicd_diagnosis(mr_id);
        mrMapper.deletemedicalrecord(mr_id);
        return "ok";
    }

}
