package com.his.controller;



import com.his.model.Patient;
import com.his.service.DrugService;
import com.his.service.PatientMysqlService;
import com.his.service.PatientService;
import com.google.gson.Gson;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * Created by Administrator on 2016-09-20.
 */
@RestController
@RequestMapping(value="/patients")
public class PatientController {

    @Autowired
    public PatientService patientService;
    @Autowired
    public DrugService drugService;
    @Autowired
    private PatientMysqlService patientMysqlService;

    Gson gson=new Gson();

    //static Map<Long, Patient> patients = Collections.synchronizedMap(new HashMap<Long, Patient>());

    @ApiOperation(value="获取患者列表", notes="")
    @RequestMapping(value="",method=RequestMethod.GET)
    public Object getPatients() throws Exception {

        return gson.toJson(patientService.getPatients());
}
    @ApiOperation(value="添加患者信息", notes="")
    //@ApiImplicitParam(name = "patient", value = "患者详细实体user", required = true, dataType = "Patient")
    @RequestMapping(value="onepatient",method=RequestMethod.POST)
    public String postPatient(@RequestBody final Patient patient){
        Integer id= patientService.postPatient(patient);
        if(id!=null){
            patient.setId(id);
            patientMysqlService.insertPatient(patient);
        }

        return "ok";
    }

    @ApiOperation(value="分页获取患者详细信息", notes="根据url的name来获取患者详细信息")
    //@ApiImplicitParam(name = "name", value = "患者姓名",dataType = "String", paramType = "path")
    @RequestMapping(value="code/{code}/{page}", method=RequestMethod.GET)
    public Object getPatient(@ApiParam(required = true, name = "code", value
            = "患者姓名码")@PathVariable("code") String code,
                             @ApiParam(required = true, name = "page", value
                                     = "页数")@PathVariable Integer page){

        //return gson.toJson(patientService.getPatient(code,page));
        int start=(page-1)*10;
        Map<String,Object> map=new HashMap();
        map.put("code",code);
        map.put("start",start);
        return patientMysqlService.selectPatientByCode(map);
    }
    @ApiOperation(value="获取患者详细信息", notes="根据url的phone来获取患者详细信息")
//    @ApiImplicitParam(name = "phone", value = "患者联系方式",dataType = "String", paramType = "path")
    @RequestMapping(value="phone/{phone}", method=RequestMethod.GET)
    public Object getPatient(@ApiParam(required = true, name = "phone", value
            = "患者电话号码")@PathVariable("phone") String phone){

       // return gson.toJson(patientService.getPatient(phone));
        return patientMysqlService.selectPatientByPhone(phone);
    }

    @ApiOperation(value="获取患者详细信息", notes="根据id来获取患者详细信息")
//    @ApiImplicitParam(name = "phone", value = "患者联系方式",dataType = "String", paramType = "path")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Object getThePatien(@PathVariable("id") Integer id){
            List<Patient> list= patientMysqlService.selectPatientById(id);
            if(list!=null&&list.size()>0){
            return list.get(0);
            }
            return "没有该患者";
       // return gson.toJson(patientService.getThePatien(id));
    }

    @ApiOperation(value="删除患者信息", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "患者id", required = true, dataType = "String",paramType = "path")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deletePatient(@PathVariable final Integer id) {
        patientMysqlService.deletePatient(id);
        return patientService.deletePatient(id);
    }
    @ApiOperation(value="更新患者详细信息", notes="根据url的name来指定更新对象，并根据传过来的patient信息来更新患者详细信息")
    //@ApiImplicitParam(name = "name", value = "用户姓名", required = true, dataType = "String",paramType = "path")

    @RequestMapping(value="/up", method=RequestMethod.PUT)
    public String putPatient(@RequestBody final Patient patient) {
        patientMysqlService.updatePatient(patient);
        patientService.putPatient(patient);
        return"ok";
    }

    @ApiOperation(value="分页", notes="根据url的active来获取指定对象，实现分页")
    @RequestMapping(value="/page/{active}", method=RequestMethod.GET)
    public Map getPage(@ApiParam(required = true, name = "active", value
            = "页数")@PathVariable("active") Integer active){
        //return patientService.getPage(active);
        int start=(active-1)*10;
        return patientMysqlService.selectPatient(start);
    }

    @ApiOperation(value="查询过敏项")
    @RequestMapping(value="/allergic/{name}", method=RequestMethod.GET)
    public Object getDrug_allergic(@ApiParam(required = true, name = "name", value
            = "过敏名称")@PathVariable String name){
        Map map=new HashMap();
        map.put("allergic",patientService.getAllergic_history(name));
        map.put("drugs", drugService.getDrugAllergic(name));
        return map;

    }

    @ApiOperation(value="医生假数据")
    @RequestMapping(value="/doctor", method=RequestMethod.GET)
    public Object getDoctor(){
        Map map=new HashMap();
        map.put("doctor","宋医生");
        map.put("department","精神科");
        map.put("doctor_licence","120120120");
        map.put("org_code","01");
        return map;
    }

}
