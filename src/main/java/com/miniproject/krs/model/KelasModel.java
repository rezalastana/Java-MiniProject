package com.miniproject.krs.model;

import com.miniproject.krs.entity.KelasEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class KelasModel {
    private String id;
    private String code;
    private String hari;
    private String jam_mulai;
    private String jam_selesai;
    private String ruangId;
    private String matakuliahId;
    private String dosenId;
    private String status;
    private String tahunAjaran;
    private String semester;
    private String quota;
    private String bisaOnline;

    private RuangModel ruang;
    private MataKuliahModel matkul;
    private DosenModel dosen;

    public KelasModel(){

    }

    public KelasModel(KelasEntity entity){
        BeanUtils.copyProperties(entity, this);
    }

}
