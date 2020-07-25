/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.MIIUniversity.controllers;

import com.mcc.MIIUniversity.entities.Siswa;
import com.mcc.MIIUniversity.entities.User;
import com.mcc.MIIUniversity.services.SiswaService;
import com.mcc.MIIUniversity.services.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Gin
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SiswaService siswaService;

    @GetMapping("")
    public ModelAndView homeLogin() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new User());
        System.out.println("Home jalan");
        return mav;
    }

    @GetMapping("/showRegister")
    public ModelAndView homeRegister() {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user", new User());
        return mav;
    }

    @RequestMapping("/login")
    public ModelAndView checkLogin(@ModelAttribute(name = "user") User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (userService.getLogin(username, password) == true) {
            ModelAndView mav = new ModelAndView("index");
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("login");
            return mav;
        }

    }

    @GetMapping("/user") 
    public ModelAndView userSiswa() {
        ModelAndView mav = new ModelAndView("siswaUser");
        mav.addObject("siswa", new Siswa());
        mav.addObject("psiswa", siswaService.getAll());
        return mav;
    }

}
