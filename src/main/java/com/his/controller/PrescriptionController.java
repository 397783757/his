package com.his.controller;



import com.his.model.*;
import com.his.service.DrugMysqlService;
import com.his.service.DrugUsageService;
import com.his.service.PreMysqlService;
import com.his.service.PrescriptionService;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by Administrator on 2016-10-08.
 */
@RestController
@RequestMapping(value="/prescriptions")
public class PrescriptionController {

    @Autowired
    private DrugUsageService drugUsageService;
    @Autowired
    public PrescriptionService prescriptionService;
    @Autowired
    public PreMysqlService preMysqlService;
    @Autowired
    private DrugMysqlService drugMysqlService;


    Gson gson=new Gson();

    @ApiOperation(value="查询所有处方", notes="")
    @RequestMapping(value="",method= RequestMethod.GET)
    public List getPrescription() throws Exception {
        //List list=new ArrayList();
        List prescriptions=prescriptionService.getPrescription();
        for (int i=0;i<prescriptions.size();i++){
            Map map = (Map)prescriptions.get(i);
            String id =map.get("id").toString();
            List <Detail> details=preMysqlService.selectDetail(id);
            map.put("detail",details);
        }
        return prescriptions;

    }

 /*   @ApiOperation(value="添加处方基础信息", notes="")

    @RequestMapping(value="/prescription",method=RequestMethod.POST)
    public String postPrescription(@RequestBody final Prescription prescription){

        return  prescriptionService.postPrescription(prescription);
    }

    @ApiOperation(value="添加处方明细信息", notes="")
    @RequestMapping(value="detail/na",method=RequestMethod.POST)
    public String postTheDetailPrescription(@RequestBody final List<Detail> detailList, int orderid){
        return  prescriptionService.postTheDetailPrescription(orderid,detailList);
    }*/

    @ApiOperation(value="添加处方信息(基础+明细)", notes="")
    @RequestMapping(value="augment",method=RequestMethod.POST)
    public String postThePrescription(@RequestBody final Prescription prescription){
        int seq=drugUsageService.selectSeq();
       Date date=new Date();
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
       String name=simpleDateFormat.format(date);
       prescription.setName("pr"+name+seq);
       Integer id= prescriptionService.postThePrescription(prescription);
        if(id!=null){
            prescription.setId(id.toString());
             preMysqlService.insertPrescription(prescription);
            return "ok";
        }
       return "失败";
    }

    @ApiOperation(value = "获取单个处方信息", notes = "根据url的id来获取处方详细信息")
    @RequestMapping(value = "getThePrescription/{id}", method = RequestMethod.GET)
    public Object  getPrescription(@PathVariable("id") Integer id) {
        //return prescriptionService.getPrescription(id);
        return   prescriptionService.getPrescription(id);

    }

    @ApiOperation(value="删除处方信息", notes="根据url的id来指定删除处方")
    //@ApiImplicitParam(name = "id", value = "处方id", required = true, dataType = "Integer",paramType = "path")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deletePrescription(@PathVariable final Integer id) {
        preMysqlService.deletePrescription(id.toString());
        prescriptionService.deletePrescription(id);
        return"ok";
    }

  /* @ApiOperation(value="更新处方信息", notes="根据url的id来指定更新处方，并根据传过来的prescription信息来更新处方信息")
    @RequestMapping(value="ma", method=RequestMethod.PUT)
    public String putPrescription(@RequestBody Prescription prescription) {
       return prescriptionService.putPrescription(prescription);
    }

    @ApiOperation(value="更新处方药品信息", notes="根据url的id来指定更新处方，并根据传过来的prescription信息来更新处方信息")
    @RequestMapping(value="drug/{order_id}", method=RequestMethod.PUT)
    public String putPrescriptionof(@PathVariable Integer order_id,@RequestBody final Detail detail) {

        return prescriptionService.putPrescriptionof(order_id,detail);
    }*/

    @ApiOperation(value="分页查询所有处方", notes="")
    @RequestMapping(value="/page/{page}/{orgCode}",method= RequestMethod.GET)
    public Map getPrescriptionPage(@ApiParam(required = true, name = "page", value
            = "页数")@PathVariable Integer page,
                                   @ApiParam(required = true, name = "orgCode", value
                                           = "机构代码")@PathVariable String orgCode) throws Exception {
      // return prescriptionService.getPrescriptionPage(page);
        Map map= prescriptionService.getPrescriptionPage(page,orgCode);
        return map;
    }

    @ApiOperation(value = "获取某位患者处方信息", notes = "根据name来获取处方详细信息")
    @RequestMapping(value = "/{name}/{page}/{orgCode}", method = RequestMethod.GET)
    public Map getPrescriptionByname(@ApiParam(required = true, name = "name", value
            = "患者姓名")@PathVariable("name") String name,
                                     @ApiParam(required = true, name = "page", value
                                             = "页数") @PathVariable Integer page,
                                     @ApiParam(required = true, name = "orgCode", value
                                             = "机构代码") @PathVariable String orgCode ) {
      // return  prescriptionService.getPrescriptionByname(name,page);
        Map map= prescriptionService.getPrescriptionByname(name,page,orgCode);
        return map;
    }

    @ApiOperation(value="更新处方基础+明细", notes="根据url的id来指定更新处方，并根据传过来的prescription信息来更新处方信息")
    @RequestMapping(value="up", method=RequestMethod.POST)
    public String putPrescriptionAnddetail(@RequestBody final Prescription prescription) {
        preMysqlService.updatePrescription(prescription);
        prescriptionService.putPrescriptionAnddetail(prescription);
        return "ok";
    }

    @ApiOperation(value = "获取全部处方模板")
    @RequestMapping(value = "/getTemplate/{orgCode}",method = RequestMethod.GET)
    public  List<PreTemplate> getPreTemplate(@ApiParam(required = true, name = "orgCode", value
            = "机构代码")@PathVariable String orgCode){
         List<PreTemplate> preTemplates=preMysqlService.selectTemplate();
        for(PreTemplate preTemplate:preTemplates){
            String remind="本诊所没有的药品: ";
            List<Detail> details=preTemplate.getDetails();
            for(int i=0;i<details.size();i++){
                Drug drug=drugMysqlService.selectDrugById(details.get(i).getProduct_id().toString());
                Detail detail=details.get(i);
                if(drug.getOrganization_code().contains("*")||drug.getOrganization_code().contains(orgCode)){
                    detail.setDrug_name(drug.getName());
                    detail.setPrice_unit(Double.parseDouble(drug.getSmall_price()));
                    detail.setProduct_uom(Integer.parseInt(drug.getSmall_unit()));
                    detail.setStop(drug.getStop());
                    if(detail.getStandard()!=drug.getStandard()){
                        detail.setStandard("(旧)"+detail.getStandard()+"(新)"+drug.getStandard());

                    }
                    if(detail.getContent_unit()!=drug.getDrug_content()){
                        detail.setContent_unit("(旧)"+detail.getContent_unit()+"(新)"+drug.getDrug_content());
                        detail.setContent_unit_name("(旧)"+detail.getContent_unit_name()+"(新)"+drug.getContent_unit_name());
                    }
                }else {
                    remind=remind+"("+drug.getName()+")";
                    preTemplate.setRemain(remind);
                    details.remove(detail);
                }

            }
        }
        return preTemplates;
    }

    @ApiOperation(value = "获取某位医生处方模板")
    @RequestMapping(value = "/getTemplateByDoctor/{d_id}/{orgCode}",method = RequestMethod.GET)
    public Object getPreTemplateByDoctor(@ApiParam(required = true, name = "d_id", value
            = "医生id")@PathVariable String d_id,
                                         @ApiParam(required = true, name = "orgCode", value
            = "机构代码")@PathVariable String orgCode){
       List<PreTemplate> preTemplates= preMysqlService.selectTemplateByDoctor(d_id);
        for(PreTemplate preTemplate:preTemplates){
            String remind="本诊所没有的药品: ";
            List<Detail> details=preTemplate.getDetails();
            for(int i=0;i<details.size();i++){
                Drug drug=drugMysqlService.selectDrugById(details.get(i).getProduct_id().toString());
                Detail detail=details.get(i);
                if(drug.getOrganization_code().contains("*")||drug.getOrganization_code().contains(orgCode)){
                    detail.setDrug_name(drug.getName());
                    detail.setPrice_unit(Double.parseDouble(drug.getSmall_price()));
                    detail.setProduct_uom(Integer.parseInt(drug.getSmall_unit()));
                    detail.setStop(drug.getStop());
                    if(detail.getStandard()!=drug.getStandard()){
                        detail.setStandard("(旧)"+detail.getStandard()+"(新)"+drug.getStandard());

                    }
                    if(detail.getContent_unit()!=drug.getDrug_content()){
                        detail.setContent_unit("(旧)"+detail.getContent_unit()+"(新)"+drug.getDrug_content());
                        detail.setContent_unit_name("(旧)"+detail.getContent_unit_name()+"(新)"+drug.getContent_unit_name());
                    }
                }else {
                    remind=remind+"("+drug.getName()+")";
                    preTemplate.setRemain(remind);
                    details.remove(detail);
                }

            }
        }
        return preTemplates;
    }

   /* @ApiOperation(value = "获取模板详细信息")
    @RequestMapping(value = "/gettemplateDetail/{t_id}",method = RequestMethod.GET)
    public List<Detail> gettemplateDetail(@PathVariable String t_id){
        return  preMysqlService.selectTemplateDetail(t_id);
    }*/

    @ApiOperation(value = "添加模板")
    @RequestMapping(value = "/insertPreTemplate",method = RequestMethod.POST)
    public String insertPreTemplate(@RequestBody PreTemplate preTemplate){
        preMysqlService.insertPreTemplate(preTemplate);
        return "ok";
    }
    @ApiOperation(value = "修改模板")
    @RequestMapping(value = "/updatePreTemplate",method = RequestMethod.POST)
    public String updatePreTemplate(@RequestBody PreTemplate preTemplate){
        preMysqlService.updatePreTemplate(preTemplate);
        return "ok";
    }

    @ApiOperation(value = "删除模板")
    @RequestMapping(value = "/deletePreTemplate/{t_id}",method = RequestMethod.GET)
    public String deletePreTemplate(@PathVariable String t_id){
        preMysqlService.deleteTemplate(t_id);
        return "ok";
    }
    @ApiOperation(value = "获取模板类型信息")
    @RequestMapping(value = "/selectPreType",method = RequestMethod.GET)
    public List selectPreType(){
        return preMysqlService.selectPreType();

    }


}
