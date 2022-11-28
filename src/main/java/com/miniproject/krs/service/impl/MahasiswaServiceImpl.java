package com.miniproject.krs.service.impl;

import com.miniproject.krs.entity.JurusanEntity;
import com.miniproject.krs.entity.MahasiswaEntity;
import com.miniproject.krs.model.MahasiswaModel;
import com.miniproject.krs.repository.MahasiswaRepo;
import com.miniproject.krs.service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MahasiswaServiceImpl implements MahasiswaService {
    private MahasiswaRepo repository;

    @Autowired
    public MahasiswaServiceImpl(MahasiswaRepo repository) {
        this.repository = repository;
    }

    @Override
    public List<MahasiswaModel> getAll() {

        List<MahasiswaEntity> result = this.repository.findAll();
        if (result.isEmpty()) {
            Collections.emptyList();
        }
        // conver dari List<SiswaEntity> => List<SiswaModel>
        return result.stream().map(MahasiswaModel::new).collect(Collectors.toList());
    }

    @Override
    public MahasiswaModel getById(String id) {
        // check id
        if (id == null || id.isBlank() || id.isEmpty()) {
            return new MahasiswaModel();
        }
        Optional<MahasiswaEntity> result = repository.findById(id);
        // convert dari SiswaEntity => SiswaModel
        return result.map(MahasiswaModel::new).orElseGet(MahasiswaModel::new);
    }

    @Override
    public Optional<MahasiswaModel> save(MahasiswaModel data) {
        if (data == null) {
            return Optional.empty();
        }
        MahasiswaEntity result = new MahasiswaEntity(data);
        try {
            // proses simpan data => table siswa
            this.repository.save(result);
            return Optional.of(new MahasiswaModel(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<MahasiswaModel> update(String id, MahasiswaModel data) {
        Optional<MahasiswaEntity> result = this.repository.findById(id);
        if (result.isEmpty()) {
            return Optional.empty();
        }

        // check data dari result
        MahasiswaEntity request = result.get();
        // replace data lama dengan dataBaru
        request.setName(data.getName());
        request.setJk(data.getJk());
        request.setAlamat(data.getAlamat());
        request.setTmptLahir(data.getTmptLahir());
        request.setTglLahir(data.getTglLahir());
        request.setAgama(data.getAgama());
        JurusanEntity jurusan = new JurusanEntity(data.getJurusan().getId());
        request.setJurusan(jurusan);
        // update waktu
        request.setUpdatedAt(LocalDateTime.now());
        request.setUpdatedBy("SYSTEM");

        try {
            this.repository.save(request);
            // jika berhasil
            return Optional.of(new MahasiswaModel(request));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<MahasiswaModel> delete(String id) {
        Optional<MahasiswaEntity> result = this.repository.findById(id);
        if (result.isEmpty()) {
            return Optional.empty();
        }
        try {
            MahasiswaEntity data = result.get();
            this.repository.delete(data);
            return Optional.of(new MahasiswaModel(data));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
