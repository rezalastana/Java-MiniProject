package com.miniproject.krs.repository;

import com.miniproject.krs.entity.DosenEntity;
import com.miniproject.krs.entity.FakultasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DosenRepo extends JpaRepository<DosenEntity,String> {
    List<DosenEntity> findByNip(String code);
    List<DosenEntity> findByName(String name);
}
