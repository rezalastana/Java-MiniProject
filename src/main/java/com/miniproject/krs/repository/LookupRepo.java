package com.miniproject.krs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniproject.krs.entity.LookupEntity;

public interface LookupRepo extends JpaRepository<LookupEntity, String> {
    List<LookupEntity> findByGroups(String groups);
    Optional<LookupEntity> findByCode(String code);
}
