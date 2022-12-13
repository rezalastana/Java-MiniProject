package com.miniproject.krs.repository;

import com.miniproject.krs.entity.GedungEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GedungRepo extends JpaRepository<GedungEntity,String> {
    List<GedungEntity> findByCode(String code);
    List<GedungEntity> findByName(String name);
}
