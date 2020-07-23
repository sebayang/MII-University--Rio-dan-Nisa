/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.MIIUniversity.controllers;

import com.mcc.MIIUniversity.entities.Jurusan; 
import com.mcc.MIIUniversity.services.JurusanService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Laila
 */
@RequestMapping("jurusan")
@RestController
public class JurusanController {

    @Autowired
    JurusanService jurusanService;

    @GetMapping("")
    public ModelAndView index(Model model) {
        ModelAndView mav = new ModelAndView("jurusan");
        mav.addObject("jurusans", jurusanService.getAll());
        mav.addObject("jurusan", new Jurusan());

        return mav;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Jurusan jurusan) {
        
        ModelAndView mav = new ModelAndView("redirect:/jurusan");
        mav.addObject("jurusans", jurusanService.getAll());
        mav.addObject("jurusan", new Jurusan());
        jurusanService.save(jurusan);
        return mav;

    }

    @GetMapping("{id}")
    public ModelAndView delete(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView("redirect:/jurusan");
        mav.addObject("jurusans", jurusanService.getAll());
        mav.addObject("jurusan", new Jurusan());
        jurusanService.delete(id);
        return mav;

    }

//    @RequestMapping("/jurusan/{id}")
//    public String getById(Model model, @PathVariable("nama") String nama) {
//        model.addAttribute("jurusan", jurusanService.getById(nama));
//        model.addAttribute("jurusans", jurusanService.getAll());
//        return "index";
//    }
}
