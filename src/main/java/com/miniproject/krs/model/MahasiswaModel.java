package com.miniproject.krs.model;

import com.miniproject.krs.entity.MahasiswaEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class MahasiswaModel {
    private String id;
    private String name;
    private String jk;
    private String alamat;
    private String tmptLahir;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tglLahir;
    private String agama;
    private String jurusanId;
    private JurusanModel jurusan;


    //constructor
    public MahasiswaModel() {
    }
    public MahasiswaModel(MahasiswaEntity data){
        BeanUtils.copyProperties(data, this);
        //buat validasi
        if (data.getJurusan() != null){
            jurusanId = data.getJurusanId();
            jurusan = new JurusanModel(data.getJurusan());
        }
    }
}
