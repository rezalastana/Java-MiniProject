package com.miniproject.krs.controller;

import com.miniproject.krs.model.KelasModel;
import com.miniproject.krs.service.KelasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/kelas")
public class KelasController {
    private KelasService kelasService;

    @Autowired
    public KelasController(KelasService kelasService){
        this.kelasService = kelasService;
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
        List<KelasModel> result = kelasService.getAll();
        view.addObject("kelasList", result);
        return view;
    }
}
