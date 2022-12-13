package com.miniproject.krs.entity;

import com.miniproject.krs.model.GedungModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "gedung_tab")
public class GedungEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "code", length = 20, unique = true)
    private String code;

    @Column(name = "nama_gedung", length = 225)
    private String name;

    @Column(name = "jmlLantai", length = 36)
    private String jmlLantai;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 20)
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    @OneToMany(mappedBy = "gedung", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RuangEntity> ruangan = new HashSet<>();

    //constructor
    public GedungEntity(){

    }

    public GedungEntity(String id){
        this.id = id;
    }

    public GedungEntity(String code, String name, String jmlLantai){
        this.code = code;
        this.name = name;
        this.jmlLantai = jmlLantai;
        this.createdAt=LocalDateTime.now();
        this.createdBy="SYSTEM";
        this.id = UUID.randomUUID().toString();
    }

    public GedungEntity(GedungModel model){
        BeanUtils.copyProperties(model, this);
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
    }

    public void addRuang(RuangEntity ruang){
        this.ruangan.add(ruang);
        ruang.setGedung(this);
    }
    public void removeRuang(RuangEntity ruang){
        this.ruangan.remove(ruang);
        ruang.setGedung(this);
    }
}
