package com.miniproject.krs.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.krs.entity.LookupEntity;
import com.miniproject.krs.repository.LookupRepo;
import com.miniproject.krs.service.LookupService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LookupServiceImpl implements LookupService {
    private final LookupRepo repo;

    @Autowired
    public LookupServiceImpl(LookupRepo repo){
        this.repo = repo;
    }

    @Override
    public List<LookupEntity> getByGroup(String group) {
        if (group == null || group.isEmpty()) {
            return Collections.emptyList();
        }

        return this.repo.findByGroups(group);
    }

    @Override
    public Optional<LookupEntity> getByCode(String code) {
        if (code == null || code.isEmpty()) {
            return Optional.empty();
        }
        return this.repo.findByCode(code);
    }

    @Override
    public Optional<LookupEntity> getById(String id) {
        if (id == null || id.isEmpty()) {
            return Optional.empty();
        }

        return this.repo.findById(id);
    }

    @Override
    public Optional<LookupEntity> save(LookupEntity entity) {
        if (entity == null){
            return Optional.empty();
        }

        try{
            this.repo.save(entity);
            return Optional.of(entity);
        } catch(Exception e){
            log.error("Failed save lookup, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<LookupEntity> saveAll(List<LookupEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }

        try {
            this.repo.saveAll(entities);
            return entities;
        } catch (Exception e) {
            log.error("Failed save lookup, error: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    
}
