package com.his.mapper;

import com.his.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-12-01.
 */
@Mapper
public interface PreMapper {

    List<PreTemplate> selectTemplate();

    List<PreTemplate> selectTemplateByDoctor(String d_id);

    List<Detail> selectTemplateDetail(String t_id);
    List<Detail> selectDetail(String mr_id);
    Long insertPrescription(Prescription prescription);
    void updatePrescription(Prescription prescription);
    void insertDetail(Detail detail);
    int insertTemplate(PreTemplate preTemplate);
    void insertTemplateDetail(Detail detail);
    void updateTemplate(PreTemplate preTemplate);
    void deleteTemplateDetail(String t_id);
    void deleteTemplate(String t_id);
    List<PreType> selectPreType();
    //新街口
    List<Prescription> selectPrescriptionById(String mr_id);
    void deleteDetail(String mr_id);
    void deletePrescription(String mr_id);
    List<Prescription> selectPrescription(Integer start);
    List<Prescription> selectPrescriptionBypartner_id(String mr_id);
    List<Prescription> selectPrescriptionByname(Map map);
    Long countMedicalRecord();
    Long countMedicalRecord(String partner_name);


}
