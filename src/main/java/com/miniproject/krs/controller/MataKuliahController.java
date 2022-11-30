package com.miniproject.krs.controller;

import com.miniproject.krs.model.MataKuliahModel;
import com.miniproject.krs.service.MataKuliahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return new ModelAndView("matakuliah/add.html");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute MataKuliahModel request){
        this.service.save(request);
        return new ModelAndView("redirect:/matakuliah");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        MataKuliahModel matakuliah = service.getById(id);
        if (matakuliah == null){
            return new ModelAndView("redirect:/matakuliah");
        }

        ModelAndView view = new ModelAndView("matakuliah/edit.html");
        view.addObject("data", matakuliah);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute MataKuliahModel request){
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
