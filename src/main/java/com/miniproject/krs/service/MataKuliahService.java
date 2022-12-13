package com.miniproject.krs.service;

import com.miniproject.krs.model.MataKuliahModel;

import java.util.List;
import java.util.Optional;

public interface MataKuliahService  {
    public List<MataKuliahModel> getAll();
    public Boolean validCode(MataKuliahModel model);
    public Boolean validName(MataKuliahModel model);
    public MataKuliahModel getById(String id);
    public Optional<MataKuliahModel> save(MataKuliahModel data);
    public Optional<MataKuliahModel> update(String id, MataKuliahModel data);
    public Optional<MataKuliahModel> delete(String id);

}
