package com.miniproject.krs.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "kelas_detail_tab")
public class KelasDetailEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "kelas_id", insertable = false, updatable = false)
    private String kelasId;

    @Column(name = "mahasiswa_id", insertable = false, updatable = false)
    private String mahasiswaId;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kelas_id", nullable = false)
    private KelasEntity kelas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mahasiswa_id", nullable = false)
    private MahasiswaEntity mahasiswa;

    //constructor
    public KelasDetailEntity(){
        
    }
}
