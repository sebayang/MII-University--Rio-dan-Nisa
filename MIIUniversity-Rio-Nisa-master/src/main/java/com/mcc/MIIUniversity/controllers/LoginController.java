/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.MIIUniversity.controllers; 
import com.mcc.MIIUniversity.entities.Fakultas;
import com.mcc.MIIUniversity.entities.Siswa;
import com.mcc.MIIUniversity.entities.User;
import com.mcc.MIIUniversity.services.FakultasService;
import com.mcc.MIIUniversity.services.SiswaService;
import com.mcc.MIIUniversity.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    FakultasService fakultasService;

    @RequestMapping("/check")
    public String checkLogin(@ModelAttribute(name = "user") User user, Model model) {
        String username = user.getUsername();
        String password = user.getPassword();
        //Check validasi
        boolean result = false;
        List<User> users = userService.getAll(); 
        for (User us : users) { 
            if (us.getUsername().equals(username)) {
                result = true;
            }
        }
        if (result == true) {
            User user1 = userService.getById(username);
            if (user1.getType() == 1) {
                model.addAttribute("fakultass", fakultasService.getAll());
                model.addAttribute("fakultas", new Fakultas());
                model.addAttribute("username", username);
                return "redirect:/fakultas";
            } else {
                model.addAttribute("psiswa", siswaService.getAll());
                model.addAttribute("siswa", new Siswa());
                model.addAttribute("username", username);
                return "redirect:/user";
            }
        } else {
            return "redirect:/showRegister";
        }
    }

    @PostMapping("/register")
    public String newRegister(@ModelAttribute(name = "user") User user, Model model, String username, String password) {
        user.setUsername(username);
        user.setPassword(password);
        User user1 = userService.getById(username);
        if (userService.getLogin(username, password) == true) {
            if (user1.getType() == 1) {
                model.addAttribute("psiswa", siswaService.getAll());
                model.addAttribute("siswa", new Siswa());
                return "redirect:/siswa";
            } else {
                model.addAttribute("psiswa", siswaService.getAll());
                model.addAttribute("siswa", new Siswa());
                model.addAttribute("username", username);
                return "redirect:/user";
            }
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping("/checkRegister")
    public String checkRegister(@ModelAttribute(name = "user") User user, Model model) {
        String username = user.getUsername();
        String password = user.getPassword();
        Integer type = 2;
        User user1 = new User();
        user1.setUsername(username);
        user1.setPassword(password);
        user1.setType(type);
        List<User> users = userService.getAll();
        boolean result = false;
        for (User us : users) {
            System.out.println(us.getUsername());
            if (us.getUsername().equals(username)) {
                result = true;
            }
        }
        if (result == false) {
            userService.saveRegister(user1);
            System.out.println(result);
            return "redirect:/";
        } else {
            return "redirect:/showRegister";
        }
    }

}
