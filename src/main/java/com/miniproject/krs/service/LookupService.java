package com.miniproject.krs.service;

import java.util.List;
import java.util.Optional;

import com.miniproject.krs.entity.LookupEntity;

public interface LookupService {
    List<LookupEntity> getByGroup(String group);
    Optional<LookupEntity> getByCode(String code);
    Optional<LookupEntity> getById(String id);
    Optional<LookupEntity> save(LookupEntity entity);
    List<LookupEntity> saveAll(List<LookupEntity> entities);
}
