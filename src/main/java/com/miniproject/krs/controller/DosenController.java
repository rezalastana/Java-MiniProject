package com.miniproject.krs.controller;

import com.miniproject.krs.entity.LookupEntity;
import com.miniproject.krs.model.DosenModel;
import com.miniproject.krs.service.DosenService;
import com.miniproject.krs.service.LookupService;
import com.miniproject.krs.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/dosen")
public class DosenController {
    private DosenService service;
    private LookupService lookupService;

    @Autowired
    public DosenController(DosenService service, LookupService lookupService){
        this.service = service;
        this.lookupService = lookupService;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("dosen/index.html");
        List<DosenModel> result = service.getAll();

        view.addObject("dataList", result);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("dosen/add.html");
        view.addObject("genderList", lookupService.getByGroup(Constant.GENDER));
        //order position
        view.addObject("byPosition", Comparator.comparing(LookupEntity::getPosition));

        view.addObject("dosen", new DosenModel());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("dosen") DosenModel request, BindingResult result){
        ModelAndView view = new ModelAndView("dosen/add.html");
        if (Boolean.FALSE.equals(service.validNip(request))){
            FieldError fieldError = new FieldError("dosen","nip","NIP "+ request.getNip() +" already exist");
            result.addError(fieldError);
        }

        if (Boolean.FALSE.equals(service.validName(request))){
            FieldError fieldError= new FieldError("dosen","name","Name "+ request.getName() +" already exist");
            result.addError(fieldError);
        }

        if (result.hasErrors()){
            view.addObject("genderList", lookupService.getByGroup(Constant.GENDER));
            //order position
            view.addObject("byPosition", Comparator.comparing(LookupEntity::getPosition));

            view.addObject("dosen", request);
            return view;
        }

        this.service.save(request);
        return new ModelAndView("redirect:/dosen");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        DosenModel dosen = this.service.getById(id);
        if (dosen == null){
            return new ModelAndView("redirect:/dosen");
        }
        ModelAndView view = new ModelAndView("dosen/edit.html");

        view.addObject("genderList", lookupService.getByGroup(Constant.GENDER));
        //order position
        view.addObject("byPosition", Comparator.comparing(LookupEntity::getPosition));

        view.addObject("dosen", dosen);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("dosen") DosenModel request, BindingResult result) {
        if (result.hasErrors()){
            ModelAndView view = new ModelAndView("dosen/edit.html");
            view.addObject("dosen", request);
            return view;
        }

        this.service.update(request.getId(), request);
        return new ModelAndView("redirect:/dosen");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        DosenModel dosen = service.getById(id);
        if (dosen == null){
            return new ModelAndView("redirect:/dosen");
        }
        ModelAndView view = new ModelAndView("dosen/detail.html");
        view.addObject("data", dosen);
        return view;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute DosenModel request){
        DosenModel dosen = service.getById(request.getId());
        if (dosen == null){
            return new ModelAndView("redirect:/dosen");
        }

        this.service.delete(request.getId());
        return new ModelAndView("redirect:/dosen");
    }


}
