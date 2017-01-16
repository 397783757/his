package com.his.controller;

import com.his.model.DrugUsage;
import com.his.service.DrugUsageService;
import io.swagger.annotations.ApiOperation;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2016-12-12.
 */
@RestController
@RequestMapping("/usage")
public class DrugUsageController {


    @Autowired
    private DrugUsageService drugUsageService;

    @ApiOperation(value = "查询用法")
    @RequestMapping(value = "/selectDrugUsage/{type}" ,method = RequestMethod.GET)
    public Object selectDrugUsageByType(@PathVariable String type){
        //return drugUsageService.selectDrugUsageByType(type);
        return drugUsageService.getDrugUsage(type);
    }
    @ApiOperation(value = "查询次数")
    @RequestMapping(value = "/selectTimes" ,method = RequestMethod.GET)
    public Object selectTimes(){

        return drugUsageService.selectTimes();
    }

    @ApiOperation(value = "id查询次数")
    @RequestMapping(value = "/selectTimesById/{id}" ,method = RequestMethod.GET)
    public Object selectTimesById(@PathVariable Integer id){
        //return drugUsageService.selectDrugUsageByType(type);
        return drugUsageService.selectTimesById(id);
    }

    @ApiOperation(value = "添加用法或用量或备注")
    @RequestMapping(value = "/insertDrugUsage" ,method = RequestMethod.POST)
    public void insertDrugUsage(@RequestBody DrugUsage drugUsage){
        drugUsageService.insertDrugUsage(drugUsage);

    }

    @ApiOperation(value = "更新用法或用量或备注")
    @RequestMapping(value = "/updateDrugUsage" ,method = RequestMethod.POST)
    public void updateDrugUsage(@RequestBody DrugUsage drugUsage){
        drugUsageService.updateDrugUsage(drugUsage);

    }

    @ApiOperation(value = "删除用法或用量或备注")
    @RequestMapping(value = "/deleteDrugUsage/{id}" ,method = RequestMethod.GET)
    public void deleteDrugUsage(@PathVariable String id){
        drugUsageService.deleteDrugUsage(id);
    }
}
