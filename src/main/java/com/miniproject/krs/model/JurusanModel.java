package com.miniproject.krs.model;

import com.miniproject.krs.entity.JurusanEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class JurusanModel {
    private String id;

    @NotBlank(message = "Data tidak boleh kosong")
    private String code;

    @NotBlank(message = "Data tidak boleh kosong")
    private String name;

    @NotBlank(message = "Data tidak boleh kosong")
    private String fakultasId;
    private String fakultasName;

    //constructor
    public JurusanModel(){

    }

    public JurusanModel(String code, String name, String fakultasId){
        this.code = code;
        this.name = name;
        this.fakultasId = fakultasId;
    }
    public JurusanModel(JurusanEntity entity){
        BeanUtils.copyProperties(entity, this);
        if (entity.getFakultas() != null){
            fakultasId = entity.getFakultas().getId();
            fakultasName = entity.getFakultas().getName();
        }
    }
}
