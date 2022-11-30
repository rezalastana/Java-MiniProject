package com.miniproject.krs.controller;

import com.miniproject.krs.model.DosenModel;
import com.miniproject.krs.model.KelasModel;
import com.miniproject.krs.model.MataKuliahModel;
import com.miniproject.krs.model.RuangModel;
import com.miniproject.krs.service.DosenService;
import com.miniproject.krs.service.KelasService;
import com.miniproject.krs.service.MataKuliahService;
import com.miniproject.krs.service.RuangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/kelas")
public class KelasController {
    private KelasService kelasService;
    private RuangService ruangService;
    private DosenService dosenService;
    private MataKuliahService mataKuliahService;

    @Autowired
    public KelasController(KelasService kelasService, RuangService ruangService, DosenService dosenService, MataKuliahService mataKuliahService){
        this.kelasService = kelasService;
        this.ruangService = ruangService;
        this.dosenService = dosenService;
        this.mataKuliahService = mataKuliahService;
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
        List<RuangModel> ruang = ruangService.getAll();
        List<MataKuliahModel> matakuliah = mataKuliahService.getAll();
        List<DosenModel> dosen = dosenService.getAll();
        view.addObject("ruangList", ruang);
        view.addObject("matakuliahList", matakuliah);
        view.addObject("dosenList", dosen);
        return view;
    }

    @GetMapping("/edit{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        KelasModel kelas = this.kelasService.getById(id);
        if (kelas == null){
            return new ModelAndView("redirect:/kelas");
        }
        List<RuangModel> ruang = ruangService.getAll();
        List<MataKuliahModel> matakuliah = mataKuliahService.getAll();
        List<DosenModel> dosen = dosenService.getAll();

        ModelAndView view = new ModelAndView("kelas/edit.html");
        view.addObject("data", kelas);
        view.addObject("ruangList", ruang);
        view.addObject("matakuliahList", matakuliah);
        view.addObject("dosenList", dosen);

        return view;
    }

    @PostMapping("/update")
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
            return new ModelAndView("redirect:/jurusan");
        }
        this.kelasService.delete(request.getId());
        return new ModelAndView("redirect:/kelas");
    }

}
