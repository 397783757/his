package com.his.mapper;

import com.his.model.Patient;
import com.his.model.PatientLine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-12-09.
 */
@Mapper
public interface PatientMapper {
    void insertPatient(Patient patient);
    void updatePatient(Patient patient);
    void insertPatientLine(PatientLine patientLine);
    void deletePatientLine(Integer id);
    void deletePatient(Integer patient_id);
    List<Patient> selectPatient(Integer start);
    Long selectCount();
    Long selectCount(String code);
    List<Patient> selectPatientByCode(Map map);
    List<Patient> selectPatientById(Integer id);
    List<Patient> selectPatientByPhone(String phone);
}
