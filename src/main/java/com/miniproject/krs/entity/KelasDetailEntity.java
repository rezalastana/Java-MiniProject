package com.miniproject.krs.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "kelas_detail_tab")
public class KelasDetailEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "kelas_id")
    private String kelasId;

    @Column(name = "mahasiswa_id")
    private String mahasiswaId;

    @Column(name = "status")
    private String status;
}
