package com.his.mapper;

import com.his.model.DiseaseData;
import com.his.model.Icd_diagnosis;
import com.his.model.MedicalRecord;
import com.his.model.MrCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-11-28.
 */
@Mapper
public interface MrMapper {
    List<MedicalRecord> selectMedicalRecord(Map map);
    List<MrCategory> selectCategoryByCode(Map map);
    List<MrCategory> selectByType(String type);
    List<MrCategory> selectMrTemplate(String id);
    Long insertCategory(MrCategory mrCategory);
    void insertTemplate(MrCategory mrCategory);
    void deleteCategoryByPrimaryKey(String id);
    void deleteTemplateByPrimaryKey(String id);
    void deleteTemplateByCategory(String id);
    void updateTemplateByPrimaryKey(MrCategory mrCategory);
    void updateCategoryByPrimaryKey(MrCategory mrCategory);
    List<DiseaseData> selectIcdBylevel(String level);
    List<DiseaseData> selectIcdByCode(String code);
    MedicalRecord selectIcdMedicalRecordById(String mr_id);
    Long insertMedicalRecord(MedicalRecord medicalRecord);
    void inserticd_diagnosis(Icd_diagnosis icd_diagnosis);
    List<MedicalRecord> selectIcdMedicalRecordBypartner_id(String  partner_id);
    void deleteicd_diagnosis(String mr_id);
    void deletemedicalrecord(String mr_id);
    void updateMedicalRecord(MedicalRecord medicalRecord);
    List<MedicalRecord> selectIcdMedicalRecord();
    List<MedicalRecord> selectMedicalRecordByname(Map map);
    Integer countMedicalRecord(Map map);
}
