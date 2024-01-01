package com.ECommerce.major.Controller;

import com.ECommerce.major.GlobalData.CartData;
import com.ECommerce.major.Model.Role;
import com.ECommerce.major.Model.User;
import com.ECommerce.major.Repository.RoleRepo;
import com.ECommerce.major.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;

    @GetMapping("/login")
    public String login(){
        CartData.products.clear();
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @PostMapping("/register")
    public String loginIn(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
        String password=user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role>roles=new ArrayList<>();
        roles.add(roleRepo.findById(2).get());
        user.setRoles(roles);
        userRepo.save(user);
        request.login(user.getEmail(),password);
        return  "redirect:/";
    }
}
