package com.miniproject.krs.repository;

import com.miniproject.krs.entity.RuangEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuangRepo extends JpaRepository<RuangEntity,String> {
}
