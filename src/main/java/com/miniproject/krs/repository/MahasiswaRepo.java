package com.miniproject.krs.repository;

import com.miniproject.krs.entity.MahasiswaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MahasiswaRepo extends JpaRepository<MahasiswaEntity,String> {
}
