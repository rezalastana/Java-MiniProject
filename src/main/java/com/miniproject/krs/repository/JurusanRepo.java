package com.miniproject.krs.repository;


import com.miniproject.krs.entity.JurusanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JurusanRepo extends JpaRepository<JurusanEntity,String> {
}
