package com.miniproject.krs.service;

import com.miniproject.krs.model.GedungModel;

import java.util.List;
import java.util.Optional;

public interface GedungService {
    public List<GedungModel> getAll();
    public GedungModel getById(String id);
    public Boolean validCode(GedungModel model);
    public Boolean validName(GedungModel model);
    public Optional<GedungModel> save(GedungModel data);
    public Optional<GedungModel> update(String id, GedungModel data);
    public Optional<GedungModel> delete(String id);
}
