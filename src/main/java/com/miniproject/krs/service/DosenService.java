package com.miniproject.krs.service;



import com.miniproject.krs.model.DosenModel;

import java.util.List;
import java.util.Optional;


public interface DosenService {
    public List<DosenModel> getAll();
    public Boolean validNip(DosenModel model);
    public Boolean validName(DosenModel model);
    public DosenModel getById(String id);
    public Optional<DosenModel> save(DosenModel data);
    public Optional<DosenModel> update(String id, DosenModel data);
    public Optional<DosenModel> delete(String id);
}
