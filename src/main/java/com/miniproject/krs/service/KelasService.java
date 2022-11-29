package com.miniproject.krs.service;

import com.miniproject.krs.model.KelasModel;

import java.util.List;
import java.util.Optional;

public interface KelasService {
    public List<KelasModel> getAll();
    public KelasModel getById(String id);
    public Optional<KelasModel> save(KelasModel data);
    public Optional<KelasModel> update(String id, KelasModel data);
    public Optional<KelasModel> delete(String id);
}
