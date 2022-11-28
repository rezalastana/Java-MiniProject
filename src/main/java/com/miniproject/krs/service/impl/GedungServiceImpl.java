package com.miniproject.krs.service.impl;

import com.miniproject.krs.entity.GedungEntity;
import com.miniproject.krs.model.GedungModel;
import com.miniproject.krs.repository.GedungRepo;
import com.miniproject.krs.service.GedungService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GedungServiceImpl implements GedungService {
    private GedungRepo repo;

    @Autowired
    public GedungServiceImpl(GedungRepo repo){
        this.repo = repo;
    }


    @Override
    public List<GedungModel> getAll() {
        return this.repo.findAll().stream().map(GedungModel::new).collect(Collectors.toList());
    }

    @Override
    public GedungModel getById(String id) {
        return this.repo.findById(id).map(GedungModel::new).orElse(new GedungModel());
    }

    @Override
    public Optional<GedungModel> save(GedungModel data) {
        if (data == null){
            return Optional.empty();
        }
        GedungEntity result = new GedungEntity(data);
        try {
            this.repo.save(result);
            return Optional.of(new GedungModel(result));
        } catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<GedungModel> update(String id, GedungModel data) {
        Optional<GedungEntity> result = this.repo.findById(id);
        if (result.isEmpty()){
            return Optional.empty();
        }
        GedungEntity request = result.get();
        BeanUtils.copyProperties(data, request);
        data.setId(id);
        try {
            this.repo.save(request);
            return Optional.of(new GedungModel(request));
        } catch (Exception e){
            return Optional.empty();
        }

    }

    @Override
    public Optional<GedungModel> delete(String id) {
        Optional<GedungEntity> result = this.repo.findById(id);
        if (result.isEmpty()){
            return Optional.empty();
        }
        try {
            GedungEntity data = result.get();
            this.repo.delete(data);
            return Optional.of(new GedungModel(data));
        } catch (Exception e){
            return Optional.empty();
        }
    }
}
