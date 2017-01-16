package com.his.mapper;

import com.his.model.Drug;
import com.his.model.DrugUsage;
import com.his.model.PreTemplate;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016-12-01.
 */
@Mapper
public interface DrugMapper {

    void insertDrug(Drug drug);
    List<DrugUsage> selectDrug_usage(String type);
    void  updateDrug(Drug drug);
    void deleteAllDrug();
    Drug selectDrugById(String id);

}
