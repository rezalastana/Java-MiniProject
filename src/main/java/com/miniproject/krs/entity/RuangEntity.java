package com.miniproject.krs.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;


import com.miniproject.krs.model.RuangModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ruang_tab")
public class RuangEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "code_ruang", length = 20, unique = true)
    private String code;
    
    @Column(name = "nama_ruang", length = 255)
    private String name;

    @Column(name = "gedung_id", length = 36, insertable = false, updatable = false)
    private String gedungId;

    @Column(name = "lantai_ke", length = 36)
    private String lantaiKe;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 20)
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gedung_id", nullable = false)
    private GedungEntity gedung;

    @OneToMany(mappedBy = "ruang", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<KelasEntity> kelas = new HashSet<>();
    //constructor
    public RuangEntity(){

    }

    public RuangEntity(String id) {
        this.id = id;
    }

    public RuangEntity(String code, String name){
        this.code = code;
        this.name = name;
    }

    public RuangEntity(String code, String name, String lantaiKe){
        this.code = code;
        this.name = name;
        this.code = lantaiKe;
    }
    public RuangEntity(RuangModel model){
        this.id = UUID.randomUUID().toString();
        this.code = model.getCode();
        this.name = model.getName();

        if (model.getGedung() != null){
            GedungEntity gedungEntity = new GedungEntity();
            gedungEntity.setId(model.getGedung().getId());
            this.gedung = gedungEntity;
        }
        this.lantaiKe = model.getLantaiKe();
        this.createdAt=LocalDateTime.now();
        this.createdBy="SYSTEM";
        this.updatedAt=LocalDateTime.now();
        this.updatedBy="SYSTEM";
    }

    @PrePersist
    public void onCreated(){
        this.id = UUID.randomUUID().toString();
    }
}
