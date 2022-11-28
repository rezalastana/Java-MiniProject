package com.miniproject.krs.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "kelas_tab")
public class KelasEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "kode")
    private String code;

    @Column(name = "hari", length = 36)
    private String hari;

    @Column(name = "jam_mulai")
    private Date jam_mulai;

    @Column(name = "jam_selesai")
    private Date jam_selesai;

    @Column(name = "ruang_id")
    private String ruangId;

    @Column(name = "matakuliah_id")
    private String matakuliahId;

    @Column(name = "dosen_id")
    private String dosenId;

    @Column(name = "status")
    private String status;

    @Column(name = "tahun_ajaran")
    private String tahunAjaran;

    @Column(name = "semester")
    private String semester;

    @Column(name = "quota")
    private String quota;

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
}
