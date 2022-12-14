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
    @NotBlank(message = "Data tidak boleh kosong")
    private String code;
    @NotBlank(message = "Data tidak boleh kosong")
    private String name;
    @NotBlank(message = "Data tidak boleh kosong")
    private String gedungId;
    @NotBlank(message = "Data tidak boleh kosong")
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
