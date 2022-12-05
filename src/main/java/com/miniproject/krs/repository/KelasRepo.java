package com.miniproject.krs.repository;

import com.miniproject.krs.entity.KelasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface KelasRepo extends JpaRepository<KelasEntity, String> {
    //validasi ruang, hari, jam mulai, jam selesai
    @Query("SELECT t FROM KelasEntity t WHERE t.hari= :hari AND t.ruangId= :ruangId AND t.dosenId= :dosenId AND (t.jam_mulai<= :jam_mulai OR t.jam_selesai<= :jam_selesai)")
    List<KelasEntity> validation1(
            @Param("hari") String hari,
            @Param("ruangId") String ruangId,
            @Param("dosenId") String dosenId,
            @Param("jam_mulai") Date jam_mulai,
            @Param("jam_selesai") Date jam_selesai
    );

    //Use BETWEEN
    @Query("SELECT t FROM KelasEntity t WHERE t.hari= :hari AND t.ruangId= :ruangId AND t.dosenId= :dosenId AND ((t.jam_mulai BETWEEN :jam_mulai AND :jam_selesai) OR (t.jam_selesai BETWEEN :jam_mulai AND :jam_selesai))")
    List<KelasEntity> validation2(
            @Param("hari") String hari,
            @Param("ruangId") String ruangId,
            @Param("dosenId") String dosenId,
            @Param("jam_mulai") Date jam_mulai,
            @Param("jam_selesai") Date jam_selesai
    );

    //validasi dimana hari, ruang, dosen sudah ada dan jam mulai>= dan jam_selesai<=
//    @Query("SELECT t FROM KelasEntity t WHERE t.hari= :hari AND t.ruangId= :ruangId AND t.dosenId= :dosenId AND (t.jam_mulai>= :jam_mulai OR t.jam_selesai<= :jam_selesai)")
//    List<KelasEntity> validation3(
//            @Param("hari") String hari,
//            @Param("ruangId") String ruangId,
//            @Param("dosenId") String dosenId,
//            @Param("jam_mulai") Date jam_mulai,
//            @Param("jam_selesai") Date jam_selesai
//    );




}
