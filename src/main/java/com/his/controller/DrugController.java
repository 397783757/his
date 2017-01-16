package com.his.controller;

import com.his.model.Detail;
import com.his.model.Drug;
import com.his.service.DrugMysqlService;
import com.his.service.DrugService;
import com.google.gson.Gson;
import com.his.service.DrugUsageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-09-26.
 */
@RestController
@RequestMapping(value="/drugs")
public class DrugController {


    @Autowired
    public DrugService drugService;
    @Autowired
    public DrugMysqlService drugMysqlService;
    @Autowired
    DrugUsageService drugUsageService;

    Gson gson=new Gson();

    @ApiOperation(value = "获取产品列表", notes = "")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object getDrugs() {
        //drugMysqlService.insertAll(drugService.getDrugs());
        List medicines=drugService.getDrugs();
        return medicines;
    }

    @ApiOperation(value = "获取三种产品列表", notes = "")
    @RequestMapping(value = "/getThreeDrugs/{orgCode}", method = RequestMethod.GET)
    public Map getThreeDrugs(@ApiParam(required = true, name = "orgCode", value
            = "机构代码")@PathVariable String orgCode) {
         Map map=new HashMap();
         List new_medicine=drugService.getNew_medicine(orgCode);
         List recent_medicine=drugService.getRecent_medicine(orgCode);
         List stock_medicine=drugService.getStock_medicine(orgCode);
         map.put("new_medicine",new_medicine);
         map.put("recent_medicine",recent_medicine);
         map.put("stock_medicine",stock_medicine);
         return map;
    }

    @ApiOperation(value = "添加药品信息", notes = "")
    @RequestMapping(value = "", method = RequestMethod.POST)
        public String postDrug(@RequestBody final Drug drug) {
        Integer id= drugService.postDrug(drug);
        if(id!=null){
        drug.setId(id);
        drugMysqlService.insertDrug(drug);
            return "ok";
        }

        return "添加失败";
    }

    @ApiOperation(value = "分页获取药品详细信息", notes = "根据url的名字来获取药品详细信息")
    @ApiImplicitParam(name = "name", value = "药品名字", dataType = "String", paramType = "path")
    @RequestMapping(value = "/{orgCode}/{name}/{page}", method = RequestMethod.GET)
    public Map getDrug(@ApiParam(required = true, name = "orgCode", value
            = "机构代码")@PathVariable("orgCode") String orgCode,
                       @ApiParam(required = true, name = "name", value
                               = "药品名称")@PathVariable("name") String name,
                       @ApiParam(required = true, name = "page", value
                               = "页数")@PathVariable Integer page) {
        Map map=drugService.getDrug(orgCode,name,page);
       // return drugService.getDrug(orgCode,name,page);
        return map;
    }

    @ApiOperation(value = "获取药品详细信息", notes = "根据url的名字来获取药品详细信息")
    @ApiImplicitParam(name = "id", value = "药品id", dataType = "Integer", paramType = "path")
    @RequestMapping(value = "id/{id}", method = RequestMethod.GET)
    public Object getTheDrug(@PathVariable("id") Integer id) {
        return drugService.getTheDrug(id);
    }

    @ApiOperation(value="删除药品信息", notes="根据url的id来指定删除药品")
    @ApiImplicitParam(name = "id", value = "药品id", required = true, dataType = "Integer",paramType = "path")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deletDerug(@PathVariable Integer id) {

        return drugService.deleteDrug(id);

    }

    @ApiOperation(value="更新药品详细信息", notes="根据url的id来指定更新药品，并根据传过来的drug信息来更新药品详细信息")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putDrug(@PathVariable final Integer id,@RequestBody final Drug drug) {

    return drugService.putDrug(id,drug);
    }

  /*  @ApiOperation(value="分页", notes="根据url的active来获取指定对象，实现分页")
    @RequestMapping(value="/{active}/{orgCode}", method=RequestMethod.GET)
    public Object getThePage(@PathVariable("active") Integer active,@PathVariable("orgCode") String orgCode){
        Map map = (Map) drugService.getThePage(active,orgCode);
        return map;
        //return drugService.getThePage(active,orgCode);
    }*/


    @ApiOperation(value = "分页获取产品列表", notes = "")
    @RequestMapping(value = "/page/{orgCode}/{page}", method = RequestMethod.GET)
    public Object getDrugsPage( @ApiParam(required = true, name = "orgCode", value
            = "机构代码")@PathVariable String orgCode,
                                @ApiParam(required = true, name = "page", value
                                        = "页数")@PathVariable Integer page) {

        Map map=drugService.getDrugsPage(page,orgCode);
        List medicines= (List) map.get("data");
        return map;
       // return drugService.getDrugsPage(page,orgCode);

    }

    @ApiOperation(value = "获取药品用法信息", notes = "")
    @RequestMapping(value = "/usage/{type}", method = RequestMethod.GET)
    public Object getDrugsUsage(
            @ApiParam(required = true, name = "type", value
                    = "用法类型")@PathVariable String  type) {


        return drugMysqlService.selectDrug_usage(type);

    }

    @ApiOperation(value = "更新mysql所有drug", notes = "")
    @RequestMapping(value = "/upAllDrug", method = RequestMethod.GET)
    public String deleteAllDrug() {
        drugMysqlService.deleteAllDrug();
        List<Object> list= drugService.getDrugs();
        drugMysqlService.insertAll(list);
        return "ok";
    }

    @ApiOperation(value = "处方药品判断")
    @RequestMapping(value = "/judgeDrug/{orgCode}",method = RequestMethod.GET)
    public Map judgeDrug(@ApiParam(required = true, name = "details", value
            = "处方药品数组")@RequestBody List<Detail> details,
                         @ApiParam(required = true, name = "orgCode", value
                                 = "机构代码")@PathVariable String orgCode){
        String remind="本诊所没有的药品: ";
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
                details.remove(detail);
            }

        }
        Map map=new HashMap();
        map.put("details",details);
        map.put("remind",remind);
        return  map;
    }

}