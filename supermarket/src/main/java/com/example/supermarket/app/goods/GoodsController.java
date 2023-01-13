package com.example.supermarket.app.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.supermarket.domain.service.goods.GoodsService;

@Controller
public class GoodsController {
	@Autowired
	GoodsService goodsService;
	
	@RequestMapping("goods")
	String goods(Model model) {
		
		model.addAttribute("goods", goodsService.findGoods());
		return "goods/goods";
	}
}
