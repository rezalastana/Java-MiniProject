package com.miniproject.krs.service.impl;

import com.miniproject.krs.entity.DosenEntity;
import com.miniproject.krs.entity.KelasEntity;
import com.miniproject.krs.entity.MataKuliahEntity;
import com.miniproject.krs.entity.RuangEntity;
import com.miniproject.krs.model.KelasModel;
import com.miniproject.krs.repository.KelasRepo;
import com.miniproject.krs.service.KelasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KelasServiceImpl implements KelasService {
    private KelasRepo repo;

    @Autowired
    public KelasServiceImpl(KelasRepo repo){
        this.repo = repo;
    }

    @Override
    public List<KelasModel> getAll() {
        return this.repo.findAll().stream().map(KelasModel::new).collect(Collectors.toList());
    }

    @Override
    public KelasModel getById(String id) {

        if (id == null || id.isBlank() || id.isEmpty()){
            return new KelasModel();
        }
        Optional<KelasEntity> result = repo.findById(id);
        return result.map(KelasModel::new).orElseGet(KelasModel::new);
    }

    @Override
    public Optional<KelasModel> save(KelasModel data) {
        if (data == null){
            return Optional.empty();
        }
        KelasEntity result = new KelasEntity(data);
        try {
            this.repo.save(result);
            return Optional.of(new KelasModel(result));
        } catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<KelasModel> update(String id, KelasModel data) {
        Optional<KelasEntity> result = this.repo.findById(id);
        if (result.isEmpty()){
            return Optional.empty();
        }

        KelasEntity request = result.get();
        request.setCode(data.getCode());
        request.setHari(data.getHari());
        request.setJam_mulai(data.getJam_mulai());
        request.setJam_selesai(data.getJam_selesai());
        RuangEntity ruang = new RuangEntity(data.getRuang().getId());
        MataKuliahEntity mataKuliah = new MataKuliahEntity(data.getMataKuliah().getId());
        DosenEntity dosen = new DosenEntity(data.getDosen().getId());
        request.setStatus(data.getStatus());
        request.setTahunAjaran(data.getTahunAjaran());
        request.setSemester(data.getSemester());
        request.setQuota(data.getQuota());
        request.setBisaOnline(data.getBisaOnline());

        try {
            this.repo.save(request);
            return Optional.of(new KelasModel(request));
        } catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<KelasModel> delete(String id) {
        Optional<KelasEntity> result = this.repo.findById(id);
        if (result.isEmpty()){
            return Optional.empty();
        }
        try {
            KelasEntity data = result.get();
            this.repo.delete(data);
            return Optional.of(new KelasModel(data));
        } catch (Exception e){
            return Optional.empty();
        }
    }
}
