package com.miniproject.krs.model;

import com.miniproject.krs.entity.KelasEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Getter
@Setter
public class KelasModel {
    private String id;
    private String code;
    private String hari;
    private Date jam_mulai;
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
    private MataKuliahModel matkul;
    private DosenModel dosen;

    public KelasModel(){

    }

    public KelasModel(KelasEntity entity){
        BeanUtils.copyProperties(entity, this);
    }

}
