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
//    @Query("select t from KelasEntity t where t.ruangId= : ruangId and t.hari= :hari and t.jam_mulai >= :jam_mulai and t.jam_selesai <= :jam_selesai")
//    List<KelasEntity> checkCase01(@Param("ruangId") String ruangId,
//                                  @Param("namaHari")String hari,
//                                  @Param("jamMulai") Date jam_mulai,
//                                  @Param("jamSelesai") Date jam_selesai);
}
