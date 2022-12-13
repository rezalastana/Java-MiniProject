package com.miniproject.krs.model;

import com.miniproject.krs.entity.MahasiswaEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
public class MahasiswaModel {
    private String id;

    @NotBlank
    @NotEmpty
    private String nim;

    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    private String jk;

    @NotBlank
    @NotEmpty
    private String alamat;

    @NotBlank
    @NotEmpty
    private String tmptLahir;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tglLahir;

    @NotBlank
    @NotEmpty
    private String agama;

    @NotBlank
    @NotEmpty
    private String jurusanId;
//    private JurusanModel jurusan; tanpa memanggil jurusanmodel
    private String jurusanName; //langsung panggil JurusanName

    //constructor
    public MahasiswaModel() {

    }

    public MahasiswaModel(MahasiswaEntity data){
        BeanUtils.copyProperties(data, this);
        //buat validasi
        if (data.getJurusan() != null){
            jurusanId = data.getJurusanId();//pemanggilan jurusanId dari mahasiswa entity langsung 
            jurusanName = data.getJurusan().getName(); //pemanggilan name jurusan langsung
        }
    }

    public MahasiswaModel(String id, String nim, String name, String jk, String alamat){
        this.id = id;
        this.nim = nim;
        this.name = name;
        this.jk = jk;
        this.alamat = alamat;
    }
}
