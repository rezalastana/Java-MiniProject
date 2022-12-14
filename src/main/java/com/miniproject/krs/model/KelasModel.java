package com.miniproject.krs.model;

import com.miniproject.krs.entity.KelasEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class KelasModel {
    private String id;
    @NotBlank(message = "Data tidak boleh kosong")
    private String code;
    @NotBlank(message = "Data tidak boleh kosong")
    private String hari;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date jam_mulai;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date jam_selesai;
    @NotBlank(message = "Data tidak boleh kosong")
    private String ruangId;
    @NotBlank(message = "Data tidak boleh kosong")
    private String mataKuliahId;
    @NotBlank(message = "Data tidak boleh kosong")
    private String dosenId;
    @NotBlank(message = "Data tidak boleh kosong")
    private String status;
    @Min(value = 1, message = "The value must be not zero")
    @NotNull(message = "Data tidak boleh kosong")
    private Integer tahunAjaran;
    @NotBlank(message = "Data tidak boleh kosong")
    private String semester;
    @Min(value = 1, message = "The value must be not zero")
    @NotNull(message = "Data tidak boleh kosong")
    private Integer quota;
    @NotBlank(message = "Data tidak boleh kosong")
    private String bisaOnline;

    private String ruangName;
    private String mataKuliahName;
    private String dosenName;

    public KelasModel(){

    }

    public KelasModel(KelasEntity entity){
        BeanUtils.copyProperties(entity, this);
        if (entity.getRuang() != null){
            ruangId = entity.getRuang().getId();
            ruangName = entity.getRuang().getName();
        }

        if (entity.getMataKuliah() != null){
            mataKuliahId = entity.getMataKuliah().getId();
            mataKuliahName = entity.getMataKuliah().getName();
        }

        if (entity.getDosen() != null){
            dosenId = entity.getDosen().getId();
            dosenName = entity.getDosen().getName();
        }
    }

}
