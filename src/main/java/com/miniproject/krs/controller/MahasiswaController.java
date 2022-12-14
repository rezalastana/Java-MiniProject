package com.miniproject.krs.controller;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

import com.miniproject.krs.entity.LookupEntity;
import com.miniproject.krs.model.JurusanModel;
import com.miniproject.krs.model.MahasiswaModel;
import com.miniproject.krs.service.JurusanService;
import com.miniproject.krs.service.LookupService;
import com.miniproject.krs.service.MahasiswaService;
import com.miniproject.krs.util.Constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/mahasiswa")
public class MahasiswaController {
    private MahasiswaService mahasiswaService;
    private JurusanService jurusanService;
    private LookupService lookupService;

    @Autowired
    public MahasiswaController(JurusanService jurusanService, MahasiswaService mahasiswaService, LookupService lookupService){
        this.mahasiswaService = mahasiswaService;
        this.jurusanService = jurusanService;
        this.lookupService = lookupService;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("mahasiswa/index.html");
        List<MahasiswaModel> result = mahasiswaService.getAll();

        view.addObject("dataList", result);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("mahasiswa/add.html");

        view.addObject("genderList", lookupService.getByGroup(Constant.GENDER));
        view.addObject("agamaList", lookupService.getByGroup(Constant.AGAMA));
        view.addObject("jurusanList", jurusanService.getAll());
        // untuk order urut byPosition
        view.addObject("byPosition", Comparator.comparing(LookupEntity::getPosition));

        view.addObject("mahasiswa", new MahasiswaModel());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("mahasiswa") MahasiswaModel request, BindingResult result){
        ModelAndView view = new ModelAndView("mahasiswa/add.html");

        if (Boolean.FALSE.equals(mahasiswaService.validNim(request))){
            FieldError fieldError = new FieldError("mahasiswa", "code", "Code "+ request.getNim() +" already exist");
            result.addError(fieldError);
        }

        if (Boolean.FALSE.equals(mahasiswaService.validName(request))) {
            ObjectError objectError = new FieldError("mahasiswa","code","Code "+ request.getName() +" already exist");
            result.addError(objectError);
        }

        if(result.hasErrors()){
            view.addObject("genderList", lookupService.getByGroup(Constant.GENDER));
            view.addObject("agamaList", lookupService.getByGroup(Constant.AGAMA));
            view.addObject("jurusanList", jurusanService.getAll());
            // untuk order urut byPosition
            view.addObject("byPosition", Comparator.comparing(LookupEntity::getPosition));

            view.addObject("mahasiswa", request);
            return view;
        }

        this.mahasiswaService.save(request);
        return new ModelAndView("redirect:/mahasiswa");
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        MahasiswaModel mahasiswa = this.mahasiswaService.getById(id);
        if(mahasiswa==null){
            return new ModelAndView("redirect:/mahasiswa");
        }
        ModelAndView view = new ModelAndView("mahasiswa/edit.html");

        view.addObject("genderList", lookupService.getByGroup(Constant.GENDER));
        view.addObject("agamaList", lookupService.getByGroup(Constant.AGAMA));
        view.addObject("jurusanList", jurusanService.getAll());
        // untuk order urut byPosition
        view.addObject("byPosition", Comparator.comparing(LookupEntity::getPosition));

        view.addObject("mahasiswa", mahasiswa);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("mahasiswa") MahasiswaModel request, BindingResult result){
        if (result.hasErrors()) {
            ModelAndView view = new ModelAndView("mahasiswa/edit.html");
            view.addObject("mahasiswa", request);
            return view;
        }
        this.mahasiswaService.update(request.getId(), request);
        return new ModelAndView("redirect:/mahasiswa");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        MahasiswaModel mahasiswa = mahasiswaService.getById(id);
        if (mahasiswa==null) {
            return new ModelAndView("redirect:/mahasiswa");
        }
        ModelAndView view = new ModelAndView("mahasiswa/detail.html");
        view.addObject("data", mahasiswa);
        return view;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute MahasiswaModel request){
        MahasiswaModel mahasiswa = mahasiswaService.getById(request.getId());
        if (mahasiswa == null){
            return new ModelAndView("redirect:/mahasiswa");
        }
        this.mahasiswaService.delete(request.getId());
        return new ModelAndView("redirect:/mahasiswa");
    }
}
