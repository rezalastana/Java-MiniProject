package com.miniproject.krs.service.impl;

import com.miniproject.krs.model.KelasModel;
import com.miniproject.krs.repository.KelasRepo;
import com.miniproject.krs.service.KelasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KelasServiceImpl implements KelasService {
    private KelasRepo repo;

    @Autowired
    public KelasServiceImpl(KelasRepo repo){
        this.repo = repo;
    }

    @Override
    public List<KelasModel> getAll() {
        return null;
    }

    @Override
    public KelasModel getById(String id) {
        return null;
    }

    @Override
    public Optional<KelasModel> save(KelasModel data) {
        return Optional.empty();
    }

    @Override
    public Optional<KelasModel> update(String id, KelasModel data) {
        return Optional.empty();
    }

    @Override
    public Optional<KelasModel> delete(String id) {
        return Optional.empty();
    }
}
