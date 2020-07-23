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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Gin
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    SiswaService siswaService;

    @RequestMapping("/check")
    public String checkLogin(@ModelAttribute(name = "user") User user, Model model) {
        String username = user.getUsername();
        String password = user.getPassword(); 
        User user1 = userService.getById(username); 
        if (userService.getLogin(username, password) == true) {
            if (user1.getType() == 1) {
                model.addAttribute("psiswa", siswaService.getAll());
                model.addAttribute("siswa", new Siswa());
                return "redirect:/siswa";
            } else {
                return "redirect:/";
            }

        } else {
            return "redirect:/";
        }
    }

}
