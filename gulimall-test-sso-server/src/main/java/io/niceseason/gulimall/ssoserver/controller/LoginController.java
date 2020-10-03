package io.niceseason.gulimall.ssoserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/login.html")
    public String login(String redirect_url, Model model) {
        model.addAttribute("redirect_url", redirect_url);
        return "login";
    }


    @RequestMapping("/login")
    public String login(String username,String password,String redirect_url) {
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            return "redirect:"+redirect_url;
        }else {
            return "/login";
        }
    }
}
