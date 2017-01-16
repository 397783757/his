package com.his.controller;

import com.his.model.Allergic;
import com.his.service.AllergicService;
import com.his.service.PatientService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2016-11-18.
 */
@RestController
@RequestMapping(value = "/allergic")
public class AllergicController {

        @Autowired
        PatientService patientService;
        @Autowired
        AllergicService allergicService;

        @ApiOperation(value="添加过敏类", notes="添加过敏类")
        @RequestMapping(value="/add", method=RequestMethod.POST)
        public String addAllergic(@ApiParam(required = true, name = "allergic", value
                = "过敏史信息json数据") @RequestBody final Allergic allergic,@ApiParam(required = false, name = "test", value
                = "测试数据")@RequestParam String test){
            Integer id=allergicService.postAllergic(allergic);
                if(id!=null){
                         allergic.setId(id);
                        allergicService.insertAllergic(allergic);
                         return "ok";
                }
                return "失败";

        }
        @ApiOperation(value="up", notes="更新过敏类")
        @RequestMapping(value="/up", method=RequestMethod.POST)
        public String upAllergic(@RequestBody final Allergic allergic){
                allergicService.putAllergic(allergic);
                allergicService.updateAllergic(allergic);
                return"ok";

        }
        @ApiOperation(value="删除", notes="删除过敏类")
        @RequestMapping(value="/deleteAllergic/{id}", method=RequestMethod.GET)
        public Object testdelete(@PathVariable Integer id){
            return allergicService.deleteAllergic(id);

        }

}
