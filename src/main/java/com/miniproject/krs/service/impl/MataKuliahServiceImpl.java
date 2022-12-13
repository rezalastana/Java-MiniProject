package com.miniproject.krs.service.impl;


import com.miniproject.krs.entity.MataKuliahEntity;
import com.miniproject.krs.model.MataKuliahModel;
import com.miniproject.krs.repository.MataKuliahRepo;
import com.miniproject.krs.service.MataKuliahService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MataKuliahServiceImpl implements MataKuliahService {
    private MataKuliahRepo repo;

    @Autowired
    private MataKuliahServiceImpl(MataKuliahRepo repo){
        this.repo = repo;
    }

    @Override
    public List<MataKuliahModel> getAll() {
        return this.repo.findAll().stream().map(MataKuliahModel::new).collect(Collectors.toList());
    }

    @Override
    public MataKuliahModel getById(String id) {
        return this.repo.findById(id).map(MataKuliahModel::new).orElse(new MataKuliahModel());
    }

    @Override
    public Optional<MataKuliahModel> save(MataKuliahModel data) {
        if (data == null){
            return Optional.empty();
        }

        //check code
        List<MataKuliahEntity> checkCode = this.repo.findByCode(data.getCode());
        if (!checkCode.isEmpty()){
            return Optional.empty();
        }

        List<MataKuliahEntity> checkName = this.repo.findByName(data.getName());
        if (!checkCode.isEmpty()){
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
        BeanUtils.copyProperties(data, request);
        data.setId(id);

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

    @Override
    public Boolean validCode(MataKuliahModel model) {
        //check Code
        List<MataKuliahEntity> checkCode = this.repo.findByCode(model.getCode());
        return checkCode.isEmpty();
    }

    @Override
    public Boolean validName(MataKuliahModel model) {
        List<MataKuliahEntity> checkName = this.repo.findByName(model.getName());
        return checkName.isEmpty();
    }
}
