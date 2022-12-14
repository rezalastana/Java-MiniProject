package com.miniproject.krs.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;

import com.miniproject.krs.model.MataKuliahModel;
import org.springframework.beans.BeanUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "matkul_tab")
public class MataKuliahEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "kode_matkul", length = 20, unique = true)
    private String code;

    @Column(name = "nama_matkul", length = 225)
    private String name;

    @Column(name = "sks")
    private String sks;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 20)
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    @OneToMany(mappedBy = "mataKuliah", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<KelasEntity> kelas = new HashSet<>();


    public MataKuliahEntity() {
        
    }

    public MataKuliahEntity(String id){
        this.id = id;
    }

    public MataKuliahEntity(MataKuliahModel model) {
        BeanUtils.copyProperties(model, this);
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
    }

    public void addMataKuliah(KelasEntity kelas){
        this.kelas.add(kelas);
        kelas.setMataKuliah(this);
    }

    public void removeMataKuliah(KelasEntity kelas){
        this.kelas.remove(kelas);
        kelas.setMataKuliah(null);
    }

    @PrePersist
    public void onCreated() {
        this.id = UUID.randomUUID().toString();
    }
}
