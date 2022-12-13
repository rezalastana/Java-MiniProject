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
import java.util.Date;

@Getter
@Setter
public class KelasModel {
    private String id;
    @NotBlank
    @NotEmpty
    private String code;
    @NotBlank
    @NotEmpty
    private String hari;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date jam_mulai;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date jam_selesai;
    @NotBlank
    @NotEmpty
    private String ruangId;
    @NotBlank
    @NotEmpty
    private String mataKuliahId;
    @NotBlank
    @NotEmpty
    private String dosenId;
    @NotBlank
    @NotEmpty
    private String status;
    @Min(value = 1, message = "The value must be not zero")
    private Integer tahunAjaran;
    @NotBlank
    @NotEmpty
    private String semester;
    @Min(value = 1, message = "The value must be not zero")
    private Integer quota;
    @NotBlank
    @NotEmpty
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
