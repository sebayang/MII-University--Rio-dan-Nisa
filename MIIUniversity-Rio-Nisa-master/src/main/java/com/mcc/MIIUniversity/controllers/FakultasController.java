/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.MIIUniversity.controllers;

import com.mcc.MIIUniversity.entities.Fakultas;
import com.mcc.MIIUniversity.repositories.FakultasRepository;
import com.mcc.MIIUniversity.services.FakultasService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView; 

/**
 *
 * @author Laila
 */
@RestController
public class FakultasController {

    @Autowired
    FakultasService fakultasService;  
    
    @GetMapping("")
    public ModelAndView homeFakultas() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("fakultass", fakultasService.getAll());
        mav.addObject("fakultas", new Fakultas());
        return mav;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Fakultas fakultas) {
        
        ModelAndView mav = new ModelAndView("redirect:/");
        mav.addObject("fakultass", fakultasService.getAll());
        mav.addObject("fakultas", new Fakultas());
        fakultasService.save(fakultas); 
        return mav; 
    }

    @GetMapping("{id}")
    @SuppressWarnings("empty-statement")
    public ModelAndView delete(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView("redirect:/");
        mav.addObject("fakultass", fakultasService.getAll());
        mav.addObject("fakultas", new Fakultas());
        fakultasService.delete(id);
        return mav;

    }
//
//    @GetMapping("{id}")
//    public ModelAndView getById(Model model, @PathVariable("nama") String nama) { 
//        ModelAndView mav = new ModelAndView("redirect:/");
//        mav.addObject("fakultass", fakultasService.getAll());
//        mav.addObject("fakultas", fakultasService.getById(nama));
//        return mav; 
//    } 
}
