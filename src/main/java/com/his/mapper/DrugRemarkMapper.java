package com.his.mapper;

import com.his.model.DrugRemark;
import com.his.model.DrugUsage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-12-08.
 */
@Mapper
public interface DrugRemarkMapper {
    List<DrugRemark> selectDrugRemark();
    void deleteDrugRemark(String id);
    void insertDrugRemark(String  remark);
    void updateDrugRemark(Map map);
}
