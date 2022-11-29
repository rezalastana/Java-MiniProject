package com.miniproject.krs.model;

import com.miniproject.krs.entity.GedungEntity;
import org.springframework.beans.BeanUtils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GedungModel {
    private String id;
    private String code;
    private String name;
    private String jmlLantai;
    private List<RuangModel> ruangList;

    //constructor
    public GedungModel(){

    }

    public GedungModel(GedungEntity entity){
        BeanUtils.copyProperties(entity, this);
    }

    public GedungModel(String code, String name, String jmlLantai){
        this.code = code;
        this.name = name;
        this.jmlLantai = jmlLantai;
    }
}
