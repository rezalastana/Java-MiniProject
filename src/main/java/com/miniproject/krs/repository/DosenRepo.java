package com.miniproject.krs.repository;

import com.miniproject.krs.entity.DosenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DosenRepo extends JpaRepository<DosenEntity,String> {
    
}
