package com.miniproject.krs.model;

import com.miniproject.krs.entity.FakultasEntity;
import com.miniproject.krs.entity.JurusanEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class FakultasModel {
    private String id;
    @NotBlank
    @NotEmpty
    private String code;
    @NotBlank
    @NotEmpty
    private String name;
    @NotBlank
    @NotEmpty
    private String alamat;
    private List<JurusanModel> jurusanList;


    //constructor
    public FakultasModel(){

    }
    public FakultasModel(String code, String name, String alamat){
        this.code = code;
        this.name = name;
        this.alamat = alamat;
    }

    public FakultasModel(FakultasEntity entity){
        BeanUtils.copyProperties(entity, this);
        if (entity.getJurusans() != null || !entity.getJurusans().isEmpty()){
            jurusanList = new ArrayList<>();
            for (JurusanEntity jrs : entity.getJurusans()){
                jurusanList.add(new JurusanModel(jrs));
            }
        }
    }
}
