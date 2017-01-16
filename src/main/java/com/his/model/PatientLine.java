package com.his.model;

/**
 * Created by Administrator on 2016-11-16.
 */
public class PatientLine {
    private Integer id;
    private Integer order;
    private String recorder;
    //private String name;
    private Integer drug_id;
    private Integer key;
    private String drug_allergic;
    private Integer type;
    private Integer patient_id;

    public Integer getType() {

        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {

        this.patient_id = patient_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getRecorder() {
        return recorder==null?"":recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public Integer getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(Integer drug_id) {
        this.drug_id = drug_id;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getDrug_allergic() {
        return drug_allergic==null?"": drug_allergic;
    }

    public void setDrug_allergic(String drug_allergic) {
        this.drug_allergic = drug_allergic;
    }
}
