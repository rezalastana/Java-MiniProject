package com.miniproject.krs.service.impl;

import com.miniproject.krs.entity.*;
import com.miniproject.krs.model.KelasModel;
import com.miniproject.krs.model.RuangModel;
import com.miniproject.krs.repository.KelasRepo;
import com.miniproject.krs.service.KelasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class KelasServiceImpl implements KelasService {
    private KelasRepo repo;

    @Autowired
    public KelasServiceImpl(KelasRepo repo){
        this.repo = repo;
    }

    @Override
    public List<KelasModel> getAll() {
        List<KelasEntity> result = this.repo.findAll();
        if (result.isEmpty()){
            Collections.emptyList();
        }
        return result.stream().map(KelasModel::new).collect(Collectors.toList());
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
    public Boolean validCode(KelasModel model) {
        //check code
        List<KelasEntity> checkCode = this.repo.findByCode(model.getCode());
        return checkCode.isEmpty();
    }

    @Override
    public Optional<KelasModel> save(KelasModel data) {
        if (data == null){
            return Optional.empty();

        }

        //checkCode
        List<KelasEntity> checkCode = this.repo.findByCode(data.getCode());
        if (!checkCode.isEmpty()) {
            return Optional.empty();
        }

        List<KelasEntity> check01 = this.repo.validation1(
                data.getHari(),
                data.getRuangId(),
                data.getDosenId(),
                data.getJam_mulai(),
                data.getJam_selesai()
        );

        List<KelasEntity> check02 = this.repo.validation3(
                data.getRuangId(),
                data.getHari(),
                data.getJam_mulai(),
                data.getJam_selesai()
        );

        List<KelasEntity> check03 = this.repo.validation7(
                data.getDosenId(),
                data.getHari(),
                data.getJam_mulai(),
                data.getJam_selesai()
        );

        List<KelasEntity> check04 = this.repo.validation8(
                data.getDosenId(),
                data.getHari(),
                data.getJam_mulai(),
                data.getJam_selesai()
        );

        List<KelasEntity> check05 = this.repo.validation4(
                data.getDosenId()
        );

        if (check01.size()>0 || check02.size()>0){
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
        RuangEntity ruang = new RuangEntity(data.getRuangId());
        MataKuliahEntity mataKuliah = new MataKuliahEntity(data.getMataKuliahId());
        DosenEntity dosen = new DosenEntity(data.getDosenId());
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
            RuangEntity ruang = data.getRuang();
            ruang.removeRuang(data);
            data.setRuang(null);
            DosenEntity dosen = data.getDosen();
            dosen.removeDosen(data);
            data.setDosen(null);
            MataKuliahEntity mataKuliah = data.getMataKuliah();
            mataKuliah.removeMataKuliah(data);
            data.setMataKuliah(null);
            this.repo.delete(data);
            return Optional.of(new KelasModel(data));
        } catch (Exception e){
            return Optional.empty();
        }
    }
}
