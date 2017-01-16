package com.his.mapper;

import com.his.model.Allergic;
import com.his.model.Uom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2016-12-08.
 */
@Mapper
public interface AllergicMapper {
    List<Allergic> selectAllergic();
    List<Allergic> selectAllergicByCode(String code);
    void deleteAllergic(Integer id);
    void insertAllergic(Allergic allergic);
    void updateAllergic(Allergic allergic);
}
