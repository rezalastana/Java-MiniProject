package com.miniproject.krs.entity;

import com.miniproject.krs.model.KelasModel;
import com.miniproject.krs.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "kelas_tab")
@AllArgsConstructor
public class KelasEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "kode_kelas", length = 20, unique = true)
    private String code;

    @Column(name = "hari", length = 36)
    private String hari;

    @Temporal(TemporalType.TIME)
    @Column(name = "jam_mulai")
    private Date jam_mulai;

    @Temporal(TemporalType.TIME)
    @Column(name = "jam_selesai")
    private Date jam_selesai;

    @Column(name = "ruang_id", length = 36, insertable = false, updatable = false)
    private String ruangId;

    @Column(name = "matakuliah_id", length = 36, insertable = false, updatable = false)
    private String mataKuliahId;

    @Column(name = "dosen_id", length = 36, insertable = false, updatable = false)
    private String dosenId;

    @Column(name = "status", length = 255)
    private String status;

    @Column(name = "tahun_ajaran", length = 36)
    private Integer tahunAjaran;

    @Column(name = "semester", length = 36)
    private String semester;

    @Column(name = "quota", length = 36)
    private Integer quota;

    @Column(name = "bisa_online")
    private String bisaOnline;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 20)
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dosen_id", nullable = false)
    private DosenEntity dosen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "matakuliah_id", nullable = false)
    private MataKuliahEntity mataKuliah;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ruang_id", nullable = false)
    private RuangEntity ruang;

    //constructor
    public KelasEntity(){

    }
    public KelasEntity(String id){
        this.id = id;
    }

    public KelasEntity(KelasModel model){
        this.id = UUID.randomUUID().toString();
        BeanUtils.copyProperties(model, this);

        if (model.getRuangId() != null) {
            RuangEntity ruangEntity = new RuangEntity();
            ruangEntity.setId(model.getRuangId());
            this.ruang = ruangEntity;
        }

        if (model.getMataKuliahId() != null){
            MataKuliahEntity mataKuliahEntity = new MataKuliahEntity();
            mataKuliahEntity.setId(model.getMataKuliahId());
            this.mataKuliah = mataKuliahEntity;
        }

        if (model.getDosenId() != null) {
            DosenEntity dosenEntity = new DosenEntity();
            dosenEntity.setId(model.getDosenId());
            this.dosen = dosenEntity;
        }

        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";

    }

    public KelasEntity(String code, String hari, String jam_mulai, String jam_selesai, String ruangId, String mataKuliahId, String dosenId){
        this.id = UUID.randomUUID().toString();
        this.code = code;
        this.hari = hari;
        this.jam_mulai = DateUtil.getTime(jam_mulai);
        this.jam_selesai = DateUtil.getTime(jam_selesai);
        this.ruangId = ruangId;
        this.mataKuliahId = mataKuliahId;
        this.dosenId = dosenId;
    }

    @PrePersist
    public void onCreated(){
        this.id = UUID.randomUUID().toString();
    }


}
