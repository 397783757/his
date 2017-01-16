package com.his.mapper;

import com.his.model.DrugUsage;
import com.his.model.Uom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-12-08.
 */
@Mapper
public interface UsagesMapper {
    List<DrugUsage> selectDrugUsageByType(String type);
    List<DrugUsage> selectDrugUsageByName(String name);
    void deleteDrugUsage(String id);
    void insertDrugUsage(DrugUsage DrugUsage);
    void updateDrugUsage(DrugUsage DrugUsage);
    Integer selectSeq();
}
