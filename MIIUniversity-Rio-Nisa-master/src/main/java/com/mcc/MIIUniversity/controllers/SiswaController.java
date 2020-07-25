/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.MIIUniversity.controllers;

import com.mcc.MIIUniversity.entities.Siswa;
import com.mcc.MIIUniversity.services.SiswaService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired; 
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
@RequestMapping("siswa")
@RestController
public class SiswaController {
    @Autowired
    SiswaService siswaService;  
    
    /**
     * controller base url
     * @return view index
     */
    @GetMapping("")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("tables_dynamic");
        mav.addObject("psiswa", siswaService.getAll());
        mav.addObject("siswa", new Siswa());

        return mav;
    }
    @GetMapping("user")
    public ModelAndView user(){
        ModelAndView mav = new ModelAndView("tables_dynamicSiswa");
        mav.addObject("psiswa", siswaService.getAll());
        mav.addObject("siswa", new Siswa());

        return mav;
    }
    /**
     * controller menyimpan data dari index
     * @return view base url
     */ 
    @PostMapping("save")
    public ModelAndView save(@Valid Siswa siswa){
        
        ModelAndView mav = new ModelAndView("redirect:/siswa");
        mav.addObject("psiswa", siswaService.getAll());
        mav.addObject("siswa", new Siswa());
        siswaService.save(siswa);
        return mav;
    } 
    @GetMapping("{id}")
    public ModelAndView deleteRegion(@PathVariable(name = "id") Integer id){
        ModelAndView mav = new ModelAndView("redirect:/siswa");
        mav.addObject("psiswa", siswaService.getAll());
        mav.addObject("siswa", new Siswa());
        siswaService.delete(id);
        return mav;
    } 
}
