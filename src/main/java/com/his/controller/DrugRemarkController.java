package com.his.controller;

import com.his.model.DrugRemark;
import com.his.model.DrugUsage;
import com.his.service.DrugRemarkService;
import com.his.service.DrugUsageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-12-12.
 */
@RestController
@RequestMapping("/usage")
public class DrugRemarkController {


    @Autowired
    private DrugRemarkService drugRemarkService;

    @ApiOperation(value = "查询药品备注")
    @RequestMapping(value = "/selectDrugRemark" ,method = RequestMethod.GET)
    public  List<DrugRemark>  selectDrugRemark(){
        return drugRemarkService.selectDrugRemark();
    }

}
