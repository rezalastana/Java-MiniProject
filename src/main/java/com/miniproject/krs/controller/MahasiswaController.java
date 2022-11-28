package com.miniproject.krs.controller;

import java.util.List;

import com.miniproject.krs.model.JurusanModel;
import com.miniproject.krs.model.MahasiswaModel;
import com.miniproject.krs.service.JurusanService;
import com.miniproject.krs.service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/mahasiswa")
public class MahasiswaController {
    private JurusanService jurusanService;
    private MahasiswaService mahasiswaService;

    @Autowired
    public MahasiswaController(JurusanService jurusanService, MahasiswaService mahasiswaService){
        this.jurusanService = jurusanService;
        this.mahasiswaService = mahasiswaService;
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
        List<JurusanModel> result = jurusanService.getAll();
        view.addObject("jurusanList", result);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute MahasiswaModel request){
        this.mahasiswaService.save(request);
        return new ModelAndView("redirect:/mahasiswa");
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        MahasiswaModel mahasiswa = this.mahasiswaService.getById(id);
        if(mahasiswa==null){
            return new ModelAndView("redirect:/mahasiswa");
        }
        List<JurusanModel> jurusan = jurusanService.getAll();
        ModelAndView view = new ModelAndView("mahasiswa/edit.html");

        view.addObject("data", mahasiswa);
        view.addObject("jurusanList", jurusan);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute MahasiswaModel request){
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
