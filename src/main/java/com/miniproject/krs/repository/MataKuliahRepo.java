package com.miniproject.krs.repository;

import com.miniproject.krs.entity.MataKuliahEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MataKuliahRepo extends JpaRepository<MataKuliahEntity,String> {
    
}
