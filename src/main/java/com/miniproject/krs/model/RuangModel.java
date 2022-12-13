package com.miniproject.krs.model;

import com.miniproject.krs.entity.RuangEntity;
import org.springframework.beans.BeanUtils;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RuangModel {
    private String id;
    @NotBlank
    @NotEmpty
    private String code;
    @NotBlank
    @NotEmpty
    private String name;
    @NotBlank
    @NotEmpty
    private String gedungId;
    @NotBlank
    @NotEmpty
    private String lantaiKe;

    private String gedungName;

    //cunstructor
    public RuangModel(){

    }

    public RuangModel(RuangEntity entity){
        BeanUtils.copyProperties(entity, this);
        if (entity.getGedung() != null){
            gedungId = entity.getGedung().getId();
            gedungName = entity.getGedung().getName();
        }
    }
}
