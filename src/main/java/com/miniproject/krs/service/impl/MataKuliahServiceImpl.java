package com.miniproject.krs.service.impl;


import com.miniproject.krs.entity.MataKuliahEntity;
import com.miniproject.krs.model.MataKuliahModel;
import com.miniproject.krs.repository.MataKuliahRepo;
import com.miniproject.krs.service.MataKuliahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MataKuliahServiceImpl implements MataKuliahService {
    private MataKuliahRepo repo;

    @Autowired
    private MataKuliahServiceImpl(MataKuliahRepo repo){
        this.repo = repo;
    }

    @Override
    public List<MataKuliahModel> getAll() {
        List<MataKuliahEntity> result = this.repo.findAll();
        if (result.isEmpty()){
            Collections.emptyList();
        }
        return result.stream().map(MataKuliahModel::new).collect(Collectors.toList());
    }

    @Override
    public MataKuliahModel getById(String id) {
        if (id == null || id.isBlank() || id.isEmpty()){
            return new MataKuliahModel();
        }
        Optional<MataKuliahEntity> result = repo.findById(id);
        return result.map(MataKuliahModel::new).orElseGet(MataKuliahModel::new);
    }

    @Override
    public Optional<MataKuliahModel> save(MataKuliahModel data) {
        if (data == null){
            return Optional.empty();
        }
        MataKuliahEntity result = new MataKuliahEntity(data);
        try {
            this.repo.save(result);
            return Optional.of(new MataKuliahModel(result));
        } catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<MataKuliahModel> update(String id, MataKuliahModel data) {
        Optional<MataKuliahEntity> result = this.repo.findById(id);
        if (result.isEmpty()){
            return Optional.empty();
        }
        MataKuliahEntity request = result.get();
        request.setCode(data.getCode());
        request.setName(data.getName());
        request.setSks(data.getSks());

        try {
            this.repo.save(request);
            return Optional.of(new MataKuliahModel(request));
        } catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<MataKuliahModel> delete(String id) {
        Optional<MataKuliahEntity> result = this.repo.findById(id);
        if (result.isEmpty()){
            return Optional.empty();
        }

        try {
            MataKuliahEntity data = result.get();
            this.repo.delete(data);
            return Optional.of(new MataKuliahModel(data));
        } catch (Exception e){
            return Optional.empty();
        }
    }
}
