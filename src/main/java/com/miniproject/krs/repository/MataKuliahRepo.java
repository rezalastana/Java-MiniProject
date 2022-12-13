package com.miniproject.krs.repository;

import com.miniproject.krs.entity.MataKuliahEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MataKuliahRepo extends JpaRepository<MataKuliahEntity,String> {
    List<MataKuliahEntity> findByCode(String code);
    List<MataKuliahEntity> findByName(String name);

}
