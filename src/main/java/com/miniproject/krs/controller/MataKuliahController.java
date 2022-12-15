package com.miniproject.krs.controller;

import com.miniproject.krs.model.MataKuliahModel;
import com.miniproject.krs.service.MataKuliahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/matakuliah")
public class MataKuliahController {
    private MataKuliahService service;
    
    @Autowired
    public MataKuliahController(MataKuliahService service){
        this.service = service;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("matakuliah/index.html");
        List<MataKuliahModel> result = service.getAll();

        view.addObject("dataList", result);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("matakuliah/add.html");

        view.addObject("mataKuliah", new MataKuliahModel());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("mataKuliah") MataKuliahModel request, BindingResult result){
        ModelAndView view = new ModelAndView("matakuliah/add.html");
        if (Boolean.FALSE.equals(service.validCode(request))){
            FieldError fieldError = new FieldError("mataKuliah","code","Code "+ request.getCode() +" already exist");
            result.addError(fieldError);
        }

        if (Boolean.FALSE.equals(service.validName(request))){
            FieldError fieldError = new FieldError("mataKuliah","name","name "+ request.getName() +" already exist");
            result.addError(fieldError);
        }

        if (result.hasErrors()){
            view.addObject("mataKuliah", request);
            return view;
        }

        this.service.save(request);
        return new ModelAndView("redirect:/matakuliah");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        MataKuliahModel matakuliah = this.service.getById(id);
        if (matakuliah == null){
            return new ModelAndView("redirect:/matakuliah");
        }

        ModelAndView view = new ModelAndView("matakuliah/edit.html");
        view.addObject("mataKuliah", matakuliah);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("mataKuliah") MataKuliahModel request, BindingResult result){
        if (result.hasErrors()) {
            ModelAndView view = new ModelAndView("matakuliah/edit.html");
            view.addObject("mataKuliah", request);
            return view;
        }
        this.service.update(request.getId(), request);
        return new ModelAndView("redirect:/matakuliah");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        MataKuliahModel matakuliah = service.getById(id);
        if (matakuliah == null){
            return new ModelAndView("redirect:/matakuliah");
        }

        ModelAndView view = new ModelAndView("matakuliah/detail.html");
        view.addObject("data",matakuliah);
        return view;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute MataKuliahModel request){
        MataKuliahModel matakuliah = service.getById(request.getId());
        if (matakuliah == null){
            return new ModelAndView("redirect:/matakuliah");
        }

        this.service.delete(request.getId());
        return new ModelAndView("redirect:/matakuliah");
    }
}
