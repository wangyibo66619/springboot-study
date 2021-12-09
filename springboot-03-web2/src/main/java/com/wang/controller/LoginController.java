package com.wang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
                         
@Controller
public class LoginController {

    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpServletRequest req) {
        // 具体的业务
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            req.getSession().setAttribute("userID",username);
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg","用户名或者密码错误");
            return "index";
        }
    }


    @GetMapping("/user/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/emps";
    }
}
