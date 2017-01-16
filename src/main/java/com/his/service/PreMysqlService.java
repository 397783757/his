package com.his.service;

import com.his.mapper.PreMapper;
import com.his.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016-12-07.
 */
@Service
public class PreMysqlService {
    @Autowired
    PreMapper preMapper;

    @Autowired
    PreMysqlService preMysqlService;

     public  String insertPrescription(Prescription  prescription){
         //Prescription prescription=unite.getHeader();
         prescription.setState("sale");
         prescription.setMr_id(prescription.getId());
        //Prescription prescription=baseToPrescription(base);
        preMapper.insertPrescription(prescription);
        List<Detail> details=prescription.getDetails();
        if(details!=null&&details.size()>0){
            for(int i=0;i<details.size();i++){
                details.get(i).setMr_id(prescription.getId());
                preMapper.insertDetail(details.get(i));
            }
        }
        return "ok";
    }

    public void updatePrescription(Prescription  prescription){
       // Prescription prescription=unite.getHeader();
        prescription.setState("sale");
        preMapper.deleteDetail(prescription.getId());
        //Prescription prescription=baseToPrescription(Prescription);
        preMapper.updatePrescription(prescription);
        List<Detail> details=prescription.getDetails();
        if(details!=null&&details.size()>0){
            for(int i=0;i<details.size();i++){
                details.get(i).setMr_id(prescription.getId());
                preMapper.insertDetail(details.get(i));
            }
        }
    }

    public List<Prescription> selectPrescriptionById(String mr_id){
        return preMapper.selectPrescriptionById(mr_id);
    }
    public  String deletePrescription(String mr_id){
        preMapper.deleteDetail(mr_id);
        preMapper.deletePrescription(mr_id);
        return "ok";
    }


    public List<PreTemplate>  selectTemplate(){
        return preMapper.selectTemplate();
    }
    public List<PreType>  selectPreType(){
        return preMapper.selectPreType();
    }

    public List<PreTemplate>  selectTemplateByDoctor(String d_id){
        return preMapper.selectTemplateByDoctor(d_id);
    }

    public List<Detail> selectTemplateDetail(String t_id){
        return preMapper.selectTemplateDetail(t_id);
    }

    public void insertPreTemplate(PreTemplate preTemplate){
        preMapper.insertTemplate(preTemplate);
        List<Detail> details=preTemplate.getDetails();
        if(details!=null&&details.size()>0){
            for(int i=0;i<details.size();i++){
                details.get(i).setT_id(preTemplate.getT_id());
                insertTemplateDetail(details.get(i));
            }
        }
    }

    public void updatePreTemplate(PreTemplate preTemplate){
        preMapper.updateTemplate(preTemplate);
        List<Detail> details=preTemplate.getDetails();
        if(details!=null&&details.size()>0){
            preMapper.deleteTemplateDetail(preTemplate.getT_id());
            if(details!=null&&details.size()>0){
                for(int i=0;i<details.size();i++){
                    details.get(i).setT_id(preTemplate.getT_id());
                    insertTemplateDetail(details.get(i));
                }
            }
        }
    }
    public void insertTemplateDetail(Detail detail){
        preMapper.insertTemplateDetail(detail);
    }

    public void deleteTemplate(String  t_id){
        preMapper.deleteTemplateDetail(t_id);
        preMapper.deleteTemplate(t_id);
    }
    public List<Detail> selectDetail(String mr_id){
       return preMapper.selectDetail(mr_id);
    }

}

