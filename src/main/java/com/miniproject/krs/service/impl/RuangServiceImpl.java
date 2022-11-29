package com.miniproject.krs.service.impl;

import com.miniproject.krs.entity.GedungEntity;
import com.miniproject.krs.entity.RuangEntity;
import com.miniproject.krs.model.RuangModel;
import com.miniproject.krs.repository.RuangRepo;
import com.miniproject.krs.service.RuangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RuangServiceImpl implements RuangService {
    private RuangRepo repo;

    @Autowired
    public RuangServiceImpl(RuangRepo repo){
        this.repo = repo;
    }

    @Override
    public List<RuangModel> getAll() {
        List<RuangEntity> result = this.repo.findAll();
        if (result.isEmpty()){
            Collections.emptyList();
        }
        return result.stream().map(RuangModel::new).collect(Collectors.toList());
    }

    @Override
    public RuangModel getById(String id) {
        if (id == null || id.isBlank() || id.isEmpty()){
            return  new RuangModel();
        }
        Optional<RuangEntity> result = repo.findById(id);
        return result.map(RuangModel::new).orElseGet(RuangModel::new);
    }

    @Override
    public Optional<RuangModel> save(RuangModel data) {
        if (data == null){
            return  Optional.empty();
        }
        RuangEntity result = new RuangEntity(data);
        try {
            this.repo.save(result);
            return Optional.of(new RuangModel(result));
        } catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<RuangModel> update(String id, RuangModel data) {
        Optional<RuangEntity> result = this.repo.findById(id);
        if (result.isEmpty()){
            return Optional.empty();
        }
        RuangEntity request = result.get();
        request.setCode(data.getCode());
        request.setName(data.getName());
        GedungEntity gedung = new GedungEntity(data.getGedung().getId());
        request.setGedung(gedung);
        request.setLantaiKe(data.getLantaiKe());
        request.setUpdatedAt(LocalDateTime.now());

        try {
            this.repo.save(request);
            return Optional.of(new RuangModel(request));
        } catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<RuangModel> delete(String id) {
        Optional<RuangEntity> result = this.repo.findById(id);
        if (result.isEmpty()){
            return Optional.empty();
        }
        try {
            RuangEntity data = result.get();
            GedungEntity gedung = data.getGedung();
            gedung.removeRuang(data);
            data.setGedung(null);
            this.repo.delete(data);
            return Optional.of(new RuangModel(data));
        } catch (Exception e){
            return Optional.empty();
        }
    }
}
