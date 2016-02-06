package com.forest.controller;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.forest.controller.support.MessageHelper;
import com.forest.service.UserService.User;

@Controller
public class SigninController {
	
	@RequestMapping(value = "signin")
	public String signin() {
        return "signin/signin";
    }
	
	
	@RequestMapping(value = "signin/success")
	public String signinSuccess(Principal principal, RedirectAttributes ra) {
		String page = "/";
		if ((principal != null) && ( (principal instanceof UsernamePasswordAuthenticationToken))) {
			UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.class.cast(principal);
			User user = User.class.cast(token.getPrincipal());
			if (user.isAdmin()){
				page = "/admin/index";
			}
			MessageHelper.addSuccessAttribute(ra, "Login_Success");
		}
        return "redirect:" + page;
    }
	
	@RequestMapping(value = "logout/success")
	public String logout(RedirectAttributes ra) {
		MessageHelper.addSuccessAttribute(ra, "Logout_Success");
        return "redirect:/";
    }
}
