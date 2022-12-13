package com.miniproject.krs.model;

import com.miniproject.krs.entity.GedungEntity;
import org.springframework.beans.BeanUtils;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class GedungModel {
    private String id;
    @NotBlank
    @NotEmpty
    private String code;
    @NotBlank
    @NotEmpty
    private String name;
    @NotBlank
    @NotEmpty
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
