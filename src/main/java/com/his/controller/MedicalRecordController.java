package com.his.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.his.model.DiseaseData;
import com.his.model.MedicalRecord;

import com.his.model.MrCategory;
import com.his.service.DrugUsageService;
import com.his.service.MedicalRecordService;
import com.google.gson.Gson;
import com.his.service.MrMysqlService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.SourceExtractor;
import org.springframework.jdbc.datasource.embedded.OutputStreamFactory;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-10-18.
 */
@RestController
@RequestMapping(value="/medicalRecords")
public class MedicalRecordController {

    @Autowired
    public MedicalRecordService medicalRecordService;

    @Autowired
    private MrMysqlService mrMysqlService;

    @Autowired
    private DrugUsageService drugUsageService;

    @ApiOperation(value="查询所有病历", notes="")
    @RequestMapping(value="",method= RequestMethod.GET)
    public Object getMedicalRecord() throws Exception {


        return medicalRecordService.getMedicalRecord();
    }

    @ApiOperation(value="添加病历信息", notes="")
    @RequestMapping(value="medicineRecord",method=RequestMethod.POST)
    public String postMedicineRecord( @RequestBody final MedicalRecord medicalRecord){
        int seq=drugUsageService.selectSeq();
        Date date=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String name=simpleDateFormat.format(date);
        medicalRecord.setName("mr"+name+seq);
        System.out.println("mr"+name+seq);
        String id= medicalRecordService.postMedicineRecord(medicalRecord);
        if(id!=null){
        medicalRecord.setMr_id(id);
        medicalRecord.setState("draft");
        mrMysqlService.insertMedicalRecord(medicalRecord);
        }
        return "ok";
    }

    @ApiOperation(value = "获取单个病历信息", notes = "根据url的id来获取病历详细信息")

    //@ApiImplicitParam(name = "id", value = "处方id", dataType = "Integer", paramType = "path")

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getMedicineRecord(@ApiParam(required = true, name = "id", value
            = "病历id") @PathVariable("id") String id) {

        return mrMysqlService.selectIcdMedicalRecordById(id);
    }

    @ApiOperation(value="删除病历信息", notes="根据url的id来指定删除病历")
    //@ApiImplicitParam(name = "id", value = "处方id", required = true, dataType = "Integer",paramType = "path")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteMedicineRecord(@PathVariable final Integer id) {
         medicalRecordService.deleteMedicineRecord(id);
         mrMysqlService.deleteMedicineRecord(id);
        return "ok";
    }

    @ApiOperation(value="更新病历信息", notes="根据url的id来指定更新病历，并根据传过来的medicalRecord信息来更新病历信息")
    @RequestMapping(value="medicineRecord/up", method=RequestMethod.POST)
    public String putMedicineRecord(@RequestBody final MedicalRecord medicalRecord) {
                mrMysqlService.updateMedicalRecord(medicalRecord);
                medicalRecordService.putMedicineRecord(medicalRecord);
                  return "ok";
    }

    @ApiOperation(value="分页", notes="根据url的active来获取指定对象，实现分页")
    @RequestMapping(value="/page/{active}/{org_code}", method=RequestMethod.GET)
    public Map getThePage(@ApiParam(required = true, name = "active", value
            = "页数")@PathVariable("active") Integer active,
                          @ApiParam(required = true, name = "org_code", value
            = "机构代码")@PathVariable("org_code") String org_code){

            int start=(active-1)*10;
            return mrMysqlService.selectMedicalRecord(start,org_code);
    }

    @ApiOperation(value="分页搜索查询病人病历", notes="")
    @RequestMapping(value = "/partnerName/{partnerName}/{org_code}/{page}", method = RequestMethod.GET)
    public Object getMedicalRecordByname(@ApiParam(required = true, name = "partnerName", value
            = "病人名字")@PathVariable("partnerName")  String partnerName,
                                         @ApiParam(required = true, name = "page", value
                                                 = "页数") @PathVariable Integer page,
                                         @ApiParam(required = true, name = "org_code", value
                                                 = "机构代码")@PathVariable("org_code")  String org_code) throws Exception {
        int start=(page-1)*10;
        return mrMysqlService.selectMedicalRecordByname(partnerName,start,org_code);
        //return gson.toJson(medicalRecordService.getMedicalRecordByname(partnerid, page));
    }

    @ApiOperation(value="获取某位病人全部病历", notes="")
    @RequestMapping(value = "/getMedicalRecordByPatientId/{patientId}", method = RequestMethod.GET)
    public List<MedicalRecord> getMedicalRecordByPatientId(@ApiParam(required = true, name = "patientId", value
            = "病人id")@PathVariable Integer patientId){

        return mrMysqlService.selectIcdMedicalRecordBypartner_id(patientId.toString());
       // return medicalRecordService.getMedicineRecordByPatientId(patientId);
    }

    @ApiOperation(value="获取病历类别", notes="")
    @RequestMapping(value = "/mrCategory/type", method = RequestMethod.GET)
    public Object getMrCategory(@ApiParam(required = true, name = "type", value
            = "病历模板类别")@RequestParam String type){


        return mrMysqlService.getMrCategory(type);
    }

    /*@ApiOperation(value="获取模板", notes="")
    @RequestMapping(value = "/mrTemplate/{category_id}", method = RequestMethod.GET)
    public Object getMrTemplate(@PathVariable String category_id){


        return mrMysqlService.getTemplate(category_id);
    }*/

    @ApiOperation(value="添加病历类别", notes="")
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public String addCategory(@RequestBody MrCategory mrCategory){

        mrMysqlService.addCategory(mrCategory);
        return "ok";
    }

    /*@ApiOperation(value="添加病历模板", notes="")
    @RequestMapping(value = "/addTemplate", method = RequestMethod.POST)
    public String addTemplate(@RequestBody MrCategory mrCategory){

        mrMysqlService.addTemplate(mrCategory);
        return "ok";
    }

    @ApiOperation(value="删除病历模板", notes="")
    @RequestMapping(value = "/delelteTemplate/{id}", method = RequestMethod.GET)
    public String deleteMrTemplate(@PathVariable String id){

        mrMysqlService.deleteMrTemplate(id);
        return "ok";
    }*/

    @ApiOperation(value="删除病历类别", notes="")
    @RequestMapping(value = "/delelteCategory/{id}", method = RequestMethod.GET)
    public String deleteMrcategory(@PathVariable String id){

        mrMysqlService.deleteCategory(id);
        return "ok";
    }

    /*@ApiOperation(value="更新病历模板", notes="")
    @RequestMapping(value = "/updateTemplate", method = RequestMethod.POST)
    public String updateTemplate(@RequestBody MrCategory mrCategory){

        mrMysqlService.updateTemplateByPrimaryKey(mrCategory);
        return "ok";
    }*/

    @ApiOperation(value="更新病历类别", notes="")
    @RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
    public String updateCategory(@RequestBody MrCategory mrCategory){

        mrMysqlService.updateCategoryByPrimaryKey(mrCategory);
        return "ok";
    }

    @ApiOperation(value="拼音五笔查询病历类别", notes="")
    @RequestMapping(value = "/selectCategory/{type}/{code}", method = RequestMethod.GET)
    public List<MrCategory> selectCategoryByCode(@ApiParam(required = true, name = "type", value
            = "病历模板类别")@PathVariable String type,
                                                 @ApiParam(required = true, name = "code", value
            = "拼音五笔码")@PathVariable String code){

        return mrMysqlService.selectCategoryByCode(code,type);

    }

    @ApiOperation(value="疾病资料", notes="")
    @RequestMapping(value = "/selectDisease/{level}", method = RequestMethod.GET)
    public List<DiseaseData> selectDiseaseByLevel( @ApiParam(required = true, name = "level", value
            = "父级别")@PathVariable String level){

        return mrMysqlService.selectIcdBylevel(level);

    }

    @ApiOperation(value="搜索疾病资料", notes="")
    @RequestMapping(value = "/selectDiseaseByCode", method = RequestMethod.GET)
    public List<DiseaseData> selectDiseaseBycode( @ApiParam( name = "request", value
            = "icd编码")HttpServletRequest request){
        String code = request.getParameter("code");
        //System.out.println(code);
        return mrMysqlService.selectIcdByCode(code);

    }
}
