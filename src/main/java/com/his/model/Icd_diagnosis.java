package com.his.model;

/**
 * Created by Administrator on 2016-12-02.
 */
public class Icd_diagnosis {
    private String doctor_code;
    private String disease_code;
    private String dia_type;
    private String disease_name;
    private String mr_id;

    public String getDoctor_code() {
        return doctor_code;
    }

    public void setDoctor_code(String doctor_code) {
        this.doctor_code = doctor_code;
    }

    public String getDisease_code() {
        return disease_code;
    }

    public void setDisease_code(String disease_code) {
        this.disease_code = disease_code;
    }

    public String getDia_type() {
        return dia_type;
    }

    public void setDia_type(String dia_type) {
        this.dia_type = dia_type;
    }

    public String getDisease_name() {
        return disease_name;
    }

    public void setDisease_name(String disease_name) {
        this.disease_name = disease_name;
    }

    public String getMr_id() {
        return mr_id;
    }

    public void setMr_id(String mr_id) {
        this.mr_id = mr_id;
    }
}
