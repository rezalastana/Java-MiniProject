package com.miniproject.krs.service;

import com.miniproject.krs.model.FakultasModel;

import java.util.List;
import java.util.Optional;

public interface FakultasService {
    public List<FakultasModel> getAll();
    public FakultasModel getById(String id);
    public Optional<FakultasModel> save(FakultasModel data);
    public Optional<FakultasModel> update(String id, FakultasModel data);
    public Optional<FakultasModel> delete(String id);
}
