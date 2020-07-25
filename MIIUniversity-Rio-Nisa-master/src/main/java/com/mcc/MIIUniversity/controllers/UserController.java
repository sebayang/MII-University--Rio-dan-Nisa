/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.MIIUniversity.controllers;

import com.mcc.MIIUniversity.entities.User;
import com.mcc.MIIUniversity.services.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("")
    public ModelAndView homeLogin() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new User());
        System.out.println("Home jalan");
        return mav;
    }

    @RequestMapping("/login") 
    public ModelAndView checkLogin(@ModelAttribute(name = "user") User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        System.out.println("+++++++++++++++++++++++++SEBELUM IF+++++++++++++++++++++++++++++++++++++");
        if (userService.getLogin(username, password) == true) {
            ModelAndView mav = new ModelAndView("index");
            return mav;
        } else { 
            ModelAndView mav = new ModelAndView("login");
            System.out.println("Lewat bawah");
            return mav;
        } 
    }
//      ModelAndView mav = new ModelAndView("redirect:/fakultas");
//        mav.addObject("fakultass", fakultasService.getAll());
//        mav.addObject("fakultas", new Fakultas());
//        fakultasService.save(fakultas); 
//        return mav; 
  
    @RequestMapping("/register")
    public ModelAndView register(@Valid User user) {
        ModelAndView mav = new ModelAndView("redirect:/register");
        mav.addObject("users", userService.getAll());
        mav.addObject("user", new User());
        userService.saveRegister(user); 
        System.out.println("Register jalan");
        return mav;
    }
    
}
