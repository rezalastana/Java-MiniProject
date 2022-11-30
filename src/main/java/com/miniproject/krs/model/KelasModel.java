package com.miniproject.krs.model;

import com.miniproject.krs.entity.KelasEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
public class KelasModel {
    private String id;
    private String code;
    private String hari;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date jam_mulai;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date jam_selesai;
    private String ruangId;
    private String matakuliahId;
    private String dosenId;
    private String status;
    private Integer tahunAjaran;
    private String semester;
    private Integer quota;
    private Boolean bisaOnline;

    private RuangModel ruang;
    private MataKuliahModel mataKuliah;
    private DosenModel dosen;

    public KelasModel(){

    }

    public KelasModel(KelasEntity entity){
        BeanUtils.copyProperties(entity, this);
    }

}
