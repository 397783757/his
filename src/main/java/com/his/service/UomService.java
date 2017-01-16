package com.his.service;

import com.his.mapper.UomMapper;
import com.his.model.Uom;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016-12-09.
 */
public class UomService {
    @Autowired
    UomMapper uomMapper;

    public List<Uom> selectUom(){
        return uomMapper.selectUom();
    }
    public void deleteUom(int id){
        uomMapper.deleteUom(id);
    }

    public  void insertUom(Uom uom){
        uomMapper.insertUom(uom);
    }

    public void updateUom(Uom uom){
        uomMapper.updateUom(uom);
    }




}
