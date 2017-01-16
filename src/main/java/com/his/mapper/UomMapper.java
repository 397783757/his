package com.his.mapper;

import com.his.model.Uom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2016-12-08.
 */
@Mapper
public interface UomMapper {
    List<Uom> selectUom();
    void deleteUom(int id);
    void insertUom(Uom uom);
    void updateUom(Uom uom);
}
