package com.his.model;

/**
 * Created by Administrator on 2016-11-30.
 */
public class DiseaseData {
    private String disease_code;
    private String disease_name;
    private String level;
    private String father_level;
    private String pinyin;

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getDisease_code() {
        return disease_code;
    }

    public void setDisease_code(String disease_code) {
        this.disease_code = disease_code;
    }

    public String getDisease_name() {
        return disease_name;
    }

    public void setDisease_name(String disease_name) {
        this.disease_name = disease_name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getFather_level() {
        return father_level;
    }

    public void setFather_level(String father_level) {
        this.father_level = father_level;
    }
}
