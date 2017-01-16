package com.his.service;

import com.his.mapper.PatientMapper;
import com.his.model.Patient;
import com.his.model.PatientLine;
import com.sun.tools.javac.jvm.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-12-09.
 */
@Service
public class PatientMysqlService {
        @Autowired
        private PatientMapper patientMapper;

        public  void insertPatient(Patient patient){
        patientMapper.insertPatient(patient);
        List<PatientLine> patientLines=patient.getPatientLines();
        if(patientLines!=null&&patientLines.size()>0){
            for(int i=0;i<patientLines.size();i++){
                PatientLine patientLine= patientLines.get(i);
                patientLine.setOrder(patient.getId());
                patientLine.setPatient_id(patient.getId());
                patientMapper.insertPatientLine(patientLine);
            }
        }

    }

   public void updatePatient(Patient patient){
     patientMapper.updatePatient(patient);
       List<PatientLine> patientLines=patient.getPatientLines();
       if(patientLines!=null&&patientLines.size()>0){
           patientMapper.deletePatientLine(patient.getId());
           for(int i=0;i<patientLines.size();i++){
               PatientLine patientLine= patientLines.get(i);
               patientLine.setOrder(patient.getId());
               patientLine.setPatient_id(patient.getId());
               patientMapper.insertPatientLine(patientLine);
           }
       }
   }

    public void insertPatientLine(PatientLine patientLine){
        patientMapper.insertPatientLine(patientLine);
    }

    public Map selectPatient(Integer start){
        Map map=new HashMap();
        map.put("data",patientMapper.selectPatient(start));
        map.put("total",selectCount());
        return map;
    }

    public  Long selectCount(){
        return patientMapper.selectCount();
    }

    public  Long selectCount(String code){
        return patientMapper.selectCount(code);
    }
    public Map selectPatientByCode(Map map){
        Map Resultmap=new HashMap();
        Resultmap.put("data",patientMapper.selectPatientByCode(map));
        Resultmap.put("total",selectCount(map.get("code").toString()));
        return Resultmap;
    }

    public List<Patient> selectPatientById(Integer id){
        return patientMapper.selectPatientById(id);
    }

    public List<Patient> selectPatientByPhone(String phone){
        return patientMapper.selectPatientByPhone(phone);
    }

    public void deletePatient(Integer patient_id){
        patientMapper.deletePatientLine(patient_id);
        patientMapper.deletePatient(patient_id);
    }



}
