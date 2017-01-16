package com.his.model;

import java.util.List;

/**
 * Created by Administrator on 2016-12-01.
 */
public class PreTemplate {
    private String t_id;
    private String t_name;
    private String d_id;
    private String type;
    private String remark;
    private List<Detail> details;
    private String remain;

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }




    public String getRemain() {
        return remain;
    }

    public void setRemain(String remain) {
        this.remain = remain;
    }


   // public Detail[] getDetails() {
        //return details;
    //}

    //public void setDetails(Detail[] details) {
        //this.details = details;
    //}

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getT_id() {
        return t_id;
    }


    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
