package com.his.model;


/**
 * Created by Administrator on 2016-10-09.
 */
public class Detail {
    private Integer product_id;
   // private String product_name;
    private String standard;
    private Double price_unit;
    private Integer product_uom_qty;
    private Integer product_uom;
    private String product_uom_name;
    private Integer id;
    private Integer order_id;
    private String dosis;
    private String use_level;
    private String content_unit;
    private String content_unit_name;
    private String times;
    private String day;
    private String total;
    private String drug_name;
    private String t_id;
    private String mr_id;
    private String money;
    private String remark;
    private String stock;
    private String groups;
    private String usages;
    private String pair;
    private String unit_content;
    private Boolean stop;

    public String getPair() {
        return pair==null?"":pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public String getProduct_uom_name() {
        return product_uom_name;
    }

    public void setProduct_uom_name(String product_uom_name) {
        this.product_uom_name = product_uom_name;
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }

    public String getUsages() {
        return usages==null?"":usages;
    }

    public void setUsages(String usages) {
        this.usages =usages;
    }

    public String getGroups() {
        return groups==null?"":groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getRemark() {
        return remark==null?"":remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getMr_id() {
        return mr_id;
    }

    public void setMr_id(String mr_id) {
        this.mr_id = mr_id;
    }

    public String getContent_unit_name() {
        return content_unit_name;
    }

    public void setContent_unit_name(String content_unit_name) {
        this.content_unit_name = content_unit_name;
    }

    public String getDrug_name() {
        return drug_name==null?"":drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getDosis() {
        return dosis== null ? "" : dosis.trim();
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getUse_level() {
        return use_level== null ? "" : use_level.trim();
    }

    public void setUse_level(String use_level) {
        this.use_level = use_level;
    }

    public String getContent_unit() {
        return content_unit== null ? "" : content_unit.trim();
    }

    public void setContent_unit(String content_unit) {
        this.content_unit = content_unit;
    }

    public String getUnit_content() {
        return unit_content==null?"":unit_content;
    }

    public void setUnit_content(String unit_content) {
        this.unit_content = unit_content;
    }

    public String getTimes() {
        return times== null ? "" : times.trim();
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getDay() {
        return day== null ? "" : day.trim();
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTotal() {
        return total==null?"":total.trim();
    }

    public void setTotal(String total) {
        this.total = total;
    }




    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public String getName() {
//        return name== null ? "" : name.trim();
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public Double getPrice_unit() {
        return price_unit==null?0.0:price_unit;
    }

    public void setPrice_unit(Double price_unit) {
        this.price_unit = price_unit;
    }

    public Integer getProduct_uom_qty() {
        return product_uom_qty==null?1:product_uom_qty ;
    }

    public void setProduct_uom_qty(Integer product_uom_qty) {
        this.product_uom_qty =product_uom_qty;
    }

    public Integer getProduct_uom() {
        return product_uom==null?1:product_uom;
    }

    public void setProduct_uom(Integer product_uom) {
        this.product_uom = product_uom;
    }

    public String getStandard() {
        return standard== null ? "" : standard.trim();
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }
}
