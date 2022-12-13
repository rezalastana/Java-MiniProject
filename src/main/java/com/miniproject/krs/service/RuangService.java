package com.miniproject.krs.service;

import com.miniproject.krs.model.GedungModel;
import com.miniproject.krs.model.RuangModel;

import java.util.List;
import java.util.Optional;


public interface RuangService {
    public List<RuangModel> getAll();
    public RuangModel getById(String id);
    public Boolean validCode(RuangModel model);
    public Boolean validName(RuangModel mode);
    public Optional<RuangModel> save(RuangModel data);
    public Optional<RuangModel> update(String id, RuangModel data);
    public Optional<RuangModel> delete(String id);
}
