package com.example.supermarket.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.supermarket.domain.model.RoleName;
import com.example.supermarket.domain.model.User;
import com.example.supermarket.domain.service.user.SuperUserDetailsService;

/**
 * ユーザーコントローラー
 * 
 * @author Yamaura
 */
@Controller
public class UserController {
	
	@Autowired
	SuperUserDetailsService superUserDetailsService;
	
	@GetMapping("signup")
	public String signup(SignupForm gignupForm ) {
		return "user/userregist";
	}
	
	@PostMapping("signup")
	public String userregist(@ModelAttribute("signupForm") @Validated SignupForm signupForm, BindingResult br, Model model) {
		if (br.hasErrors()) {
			return "user/userregist";
		}
		User user = new User(signupForm.getUsername(),
				signupForm.getPassword(),
				signupForm.getFirstname(),
				signupForm.getLastname(),
				RoleName.USER);
		
		superUserDetailsService.userregist(user);
		return "redirect:/loginForm";
	}

}
