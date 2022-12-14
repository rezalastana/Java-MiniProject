package com.miniproject.krs.model;

import com.miniproject.krs.entity.DosenEntity;
import org.springframework.beans.BeanUtils;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
public class DosenModel {
    private String id;
    @NotBlank(message = "Data tidak boleh kosong")
    private String nip;

    @NotBlank(message = "Data tidak boleh kosong")
    private String name;

    @NotBlank(message = "Data tidak boleh kosong")
    private String jk;

    @NotBlank(message = "Data tidak boleh kosong")
    private String alamat;

    @NotBlank(message = "Data tidak boleh kosong")
    private String gelar;

    //constructor
    public DosenModel() {

    }

    public DosenModel(String nip, String name, String jk, String alamat, String gelar){
        this.nip = nip;
        this.name = name;
        this.jk = jk;
        this.alamat = alamat;
        this.gelar = gelar;
    }
    
    public DosenModel(DosenEntity entity){
        BeanUtils.copyProperties(entity, this);
    }

}
