package com.miniproject.krs.model;

import java.util.UUID;

import com.miniproject.krs.entity.MataKuliahEntity;
import org.springframework.beans.BeanUtils;


public class MataKuliahModel {
    public String id;
    public String code;
    public String name;
    public Integer sks;
    
    //constructor
    public MataKuliahModel(){

    }

    public MataKuliahModel(MataKuliahEntity entity){
        BeanUtils.copyProperties(entity, this);
        this.id = UUID.randomUUID().toString();
    }
}
