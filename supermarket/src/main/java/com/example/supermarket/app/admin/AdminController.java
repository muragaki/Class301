package com.example.supermarket.app.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.supermarket.domain.model.MItem;
import com.example.supermarket.domain.model.RoleName;
import com.example.supermarket.domain.model.User;
import com.example.supermarket.domain.service.goods.GoodsService;
import com.example.supermarket.domain.service.user.SuperUserDetailsService;
@Controller
public class AdminController {
	
	@Autowired
	SuperUserDetailsService superUserDetailsService;
	
	@Autowired
	GoodsService goodsService;
	
	@RequestMapping("admin")
	String admin() {
		return "admin/adminmenu";
	}

	@RequestMapping("admin/newuser")
	String newuser(UserForm userForm, Model model) {
		userForm.setUsername("username");
		userForm.setPassword("password");
		userForm.setFirstname("姓");
		userForm.setLastname("名");
		userForm.setRolename(RoleName.ADMIN);
		userForm.setRoleNameList(new ArrayList<RoleName>(Arrays.asList(RoleName.ADMIN,RoleName.USER)));
		return "admin/newuser";
	}
	
	@PostMapping("admin/signup")
	String signup(@ModelAttribute("UserForm") @Validated UserForm userForm, BindingResult br, Model model) {
		if (br.hasErrors()) {
			return "admin/newuser";
		}
		User user = new User(userForm.getUsername(),
				userForm.getPassword(),
				userForm.getFirstname(),
				userForm.getLastname(),
				userForm.getRolename());
		
		superUserDetailsService.userregist(user);
		model.addAttribute("userForm", userForm);
		return "admin/signup";
	}
	
	@RequestMapping("admin/userlist")
	String userlist(Model model) {
		List<User> userlist = superUserDetailsService.getUserAll();
		model.addAttribute("userlist", userlist);
		return "admin/userlist";
	}
	
	@RequestMapping("admin/edit")
	String useredit(@RequestParam("userId") String userId, UserEditForm userForm, Model model) {
		User user = superUserDetailsService.findById(userId);
		userForm.setUsername(user.getUserId());
		userForm.setFirstname(user.getFirstName());
		userForm.setLastname(user.getLastName());
		userForm.setRolename(user.getRoleName());
		userForm.setRoleNameList(new ArrayList<RoleName>(Arrays.asList(RoleName.ADMIN,RoleName.USER)));
		model.addAttribute("userForm", userForm);

		return "admin/useredit";
	}
	
	@PostMapping("admin/editconf")
	String editconf(@ModelAttribute("userForm") @Validated UserEditForm userForm, BindingResult br, Model model) {
		if (br.hasErrors()) {
			return "admin/useredit";
		}
		User user = superUserDetailsService.findById(userForm.getUsername());
		if (!user.getFirstName().equals(userForm.getFirstname())) {
			user.setFirstName(userForm.getFirstname());
		}
		if (!user.getLastName().equals(userForm.getLastname())) {
			user.setLastName(userForm.getLastname());
		}
		if (!user.getRoleName().getValue().equals(userForm.getRolename().getValue())) {
			user.setRoleName(userForm.getRolename());
		}
		superUserDetailsService.userregist(user);
		model.addAttribute("userForm", userForm);
		return "admin/editconf";
	}
	
	@RequestMapping("admin/goodslist")
	String goodslist(Model model) {
		List<MItem> goodslist = goodsService.findGoods();
		model.addAttribute("goodsList", goodslist);
		return "admin/goodslist";
	}
	
	@RequestMapping("admin/itemedit")
	String itemedit(@RequestParam("itemcode") String itemcode, ItemEditForm itemEditForm, Model model) {
		MItem item = goodsService.findItem(itemcode);
		itemEditForm.setItemcode(item.getItemcode());
		itemEditForm.setItemname(item.getItemname());
		itemEditForm.setItemprice(item.getItemprice());
		itemEditForm.setEnableflag(item.isEnableflag());
//		model.addAttribute("item", itemEditForm);
		return "admin/itemedit";
	}
	
	@PostMapping("admin/itemeditconf")
	String itemeditconf(@ModelAttribute("itemEditForm") @Validated ItemEditForm itemEditForm, BindingResult br, Model model) {
		MItem item = goodsService.findItem(itemEditForm.getItemcode());
		if (!item.getItemname().equals(itemEditForm.getItemname())) {
			item.setItemname(itemEditForm.getItemname());
		}
		if (item.getItemprice() != itemEditForm.getItemprice()) {
			item.setItemprice(itemEditForm.getItemprice());
		}
		if (item.isEnableflag() != itemEditForm.isEnableflag()) {
			item.setEnableflag(itemEditForm.isEnableflag());
		}
		goodsService.itemregist(item);
		return "admin/itemeditconf";
	}
}
