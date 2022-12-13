package com.miniproject.krs.service;

import com.miniproject.krs.model.KelasModel;
import com.miniproject.krs.model.RuangModel;

import java.util.List;
import java.util.Optional;

public interface KelasService {
    public List<KelasModel> getAll();
    public KelasModel getById(String id);
    public Boolean validCode(KelasModel model);
    public Boolean validHari(KelasModel model);
    public Optional<KelasModel> save(KelasModel data);
    public Optional<KelasModel> update(String id, KelasModel data);
    public Optional<KelasModel> delete(String id);
}
