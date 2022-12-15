package com.miniproject.krs.controller;

import com.miniproject.krs.entity.LookupEntity;
import com.miniproject.krs.model.*;
import com.miniproject.krs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/kelas")
public class KelasController {
    private KelasService kelasService;
    private RuangService ruangService;
    private DosenService dosenService;
    private MataKuliahService mataKuliahService;
    private LookupService lookupService;

    @Autowired
    public KelasController(KelasService kelasService, RuangService ruangService, DosenService dosenService, MataKuliahService mataKuliahService, LookupService lookupService){
        this.kelasService = kelasService;
        this.ruangService = ruangService;
        this.dosenService = dosenService;
        this.mataKuliahService = mataKuliahService;
        this.lookupService = lookupService;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("kelas/index.html");
        List<KelasModel> result = kelasService.getAll();

        view.addObject("dataList", result);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("kelas/add.html");

        view.addObject("hariList", lookupService.getByGroup("HARI"));
        view.addObject("onlineList", lookupService.getByGroup("ONLINE"));
        // untuk order urut byPosition
        view.addObject("byPosition", Comparator.comparing(LookupEntity::getPosition));

        view.addObject("ruangList", ruangService.getAll());
        view.addObject("mataKuliahList", mataKuliahService.getAll());
        view.addObject("dosenList", dosenService.getAll());
        view.addObject("kelas", new KelasModel());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("kelas") KelasModel request, BindingResult result){
        ModelAndView view = new ModelAndView("kelas/add.html");
        if (Boolean.FALSE.equals(kelasService.validCode(request))){
            FieldError fieldError = new FieldError("kelas","code","Code "+ request.getCode() +" already exist");
            result.addError(fieldError);
        }

        if (result.hasErrors()){
            view.addObject("hariList", lookupService.getByGroup("HARI"));
            view.addObject("onlineList", lookupService.getByGroup("ONLINE"));
            // untuk order urut byPosition
            view.addObject("byPosition", Comparator.comparing(LookupEntity::getPosition));
            view.addObject("ruangList", ruangService.getAll());
            view.addObject("mataKuliahList", mataKuliahService.getAll());
            view.addObject("dosenList", dosenService.getAll());
            view.addObject("kelas", request);
            return view;
        }


        this.kelasService.save(request);
        return new ModelAndView("redirect:/kelas");
    }

    @GetMapping("/edit{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        KelasModel kelas = this.kelasService.getById(id);
        if (kelas == null){
            return new ModelAndView("redirect:/kelas");
        }

        ModelAndView view = new ModelAndView("kelas/edit.html");

        view.addObject("hariList", lookupService.getByGroup("HARI"));
        view.addObject("onlineList", lookupService.getByGroup("ONLINE"));
        // untuk order urut byPosition
        view.addObject("byPosition", Comparator.comparing(LookupEntity::getPosition));

        view.addObject("ruangList", ruangService.getAll());
        view.addObject("mataKuliahList", mataKuliahService.getAll());
        view.addObject("dosenList", dosenService.getAll());
        view.addObject("kelas", kelas);

        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("kelas") KelasModel request, BindingResult result){
        if (result.hasErrors()) {
            ModelAndView view = new ModelAndView("kelas/edit.html");
            view.addObject("kelas", request);
            return view;
        }
        this.kelasService.update(request.getId(), request);
        return new ModelAndView("redirect:/kelas");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        KelasModel kelas = kelasService.getById(id);
        if (kelas == null){
            return new ModelAndView("redirect:/kelas");
        }

        ModelAndView view = new ModelAndView("kelas/detail.html");
        view.addObject("data", kelas);
        return view;
    }

    @PostMapping("delete")
    public ModelAndView delete(@ModelAttribute KelasModel request){
        KelasModel kelas = kelasService.getById(request.getId());
        if (kelas == null){
            return new ModelAndView("redirect:/kelas");
        }
        this.kelasService.delete(request.getId());
        return new ModelAndView("redirect:/kelas");
    }

}
