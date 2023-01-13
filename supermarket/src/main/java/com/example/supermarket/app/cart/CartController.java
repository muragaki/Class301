package com.example.supermarket.app.cart;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.supermarket.domain.model.Sales;
import com.example.supermarket.domain.service.cart.CartService;
import com.example.supermarket.domain.service.user.SuperUserDetails;

@Controller
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@RequestMapping("cart")
	String cart(@ModelAttribute("cartForm") CartForm cartForm, Model model) {
		String userId = ((SuperUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUserId();
		List<Sales> itemlist = cartService.finditem(userId);
		cartForm.setSalesList(new ArrayList<Sales>(itemlist));
		return "cart/cart";
	}
	
	@RequestMapping("cartin")
	String cartin(String itemcode, Model model) {
		int one = 1;
		String userId = ((SuperUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUserId();
		LocalDateTime localtime = LocalDateTime.now();
		
		Sales sales = new Sales(itemcode, one, userId, localtime);
		cartService.save(sales);
		return "redirect:/goods";
	}

	@RequestMapping("cartcalculate")
	String cartcalcurate(@ModelAttribute("cartForm") @Validated CartForm cartForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "cart/cart";
		}
		// 合計金額計算
		Integer total = 0;
		ArrayList<Sales> salesList = cartForm.getSalesList();
		for (Sales sales : salesList) {
			total += sales.getSales() * sales.getItem().getItemprice();
		}
		model.addAttribute("total", total);
		return "cart/cartconf";
	}
	
	@RequestMapping("cartconf")
	String cartconf(@ModelAttribute("cartForm") CartForm cartForm, Model model) {
		ArrayList<Sales> salesList = cartForm.getSalesList();
		for (Sales sales : salesList) {
			cartService.update(sales);
		}
		return "redirect:/goods";
	}
}
