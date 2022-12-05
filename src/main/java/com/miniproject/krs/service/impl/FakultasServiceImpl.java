package com.miniproject.krs.service.impl;

import com.miniproject.krs.entity.FakultasEntity;
import com.miniproject.krs.model.FakultasModel;
import com.miniproject.krs.repository.FakultasRepo;
import com.miniproject.krs.service.FakultasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FakultasServiceImpl implements FakultasService {
    private FakultasRepo repository;
    @Autowired
    public FakultasServiceImpl(FakultasRepo repository){
        this.repository = repository;
    }

    @Override
    public List<FakultasModel> getAll() {
        return this.repository.findAll().stream().map(FakultasModel::new).collect(Collectors.toList());
    }

    @Override
    public FakultasModel getById(String id) {
        if (id == null || id.isEmpty() || id.isBlank()){
            return new FakultasModel();
        }
        return this.repository.findById(id).map(FakultasModel::new).orElse(new FakultasModel());
    }

    @Override
    public Optional<FakultasModel> save(FakultasModel data) {
        if(data == null) {
            return Optional.empty();
        }

        //check code
        List<FakultasEntity> checkCode = this.repository.findByCode(data.getCode());
        if (!checkCode.isEmpty()){
            return Optional.empty();
        }

        //check name
        List<FakultasEntity> checkName = this.repository.findByName(data.getName());
        if (!checkName.isEmpty()){
            return Optional.empty();
        }

        FakultasEntity result= new FakultasEntity(data);
        try{
            // proses simpan data => table siswa
            this.repository.save(result);
            return Optional.of(new FakultasModel(result));
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<FakultasModel> update(String id, FakultasModel data) {
        if (id == null || id.isBlank() || id.isEmpty()){
            return Optional.empty();
        }

        Optional<FakultasEntity> result = this.repository.findById(id);
        if (result.isEmpty()){
            return Optional.empty();
        }

        FakultasEntity request = result.get();
        BeanUtils.copyProperties(data, request);
        data.setId(id);
        try {
            this.repository.save(request);
            return  Optional.of(new FakultasModel(request));
        } catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<FakultasModel> delete(String id) {
//        Optional<FakultasEntity> fakultas = this.repository.findById(id);
//        if (fakultas == null){
//            return Optional.empty();
//        }

        //Bisa juga seperti ini
        FakultasEntity fakultas = this.repository.findById(id).orElse(null);
        if (fakultas == null){
            return Optional.empty();
        }

        if (!fakultas.getJurusans().isEmpty()){
            fakultas.getJurusans().clear();
        }

        try {
            this.repository.save(fakultas);
        } catch (Exception e){
            log.info("Delete is failed, error {}", e.getMessage());
        }
        return Optional.of(new FakultasModel(fakultas));
    }
}
