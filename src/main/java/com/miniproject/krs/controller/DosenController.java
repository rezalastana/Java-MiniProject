package com.miniproject.krs.controller;

import com.miniproject.krs.model.DosenModel;
import com.miniproject.krs.service.DosenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/dosen")
public class DosenController {
    private DosenService service;

    @Autowired
    public DosenController(DosenService service){
        this.service = service;
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
        return new ModelAndView("dosen/add.html");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute DosenModel request){
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
        view.addObject("data", dosen);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute DosenModel request){
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
