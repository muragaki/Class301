package com.example.supermarket.domain.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supermarket.domain.model.Sales;
import com.example.supermarket.domain.repository.cart.SalesRepository;

@Service
public class CartService {
	
	@Autowired
	SalesRepository salesRepository;

	public void save(Sales sales) {
		if (salesRepository.readByItemcodeAndUserid(sales.getItemcode(), sales.getUserid()) == null) {
			salesRepository.save(sales);
		} else {
			// 既に登録されている場合は登録しない
		}
	}
	
	public List<Sales> finditem(String userid) {
		return salesRepository.findByUseridOrderBySalesidAsc(userid);
	}
	
	public void update(Sales sales) {
		if (sales.getSales() == 0) {
			salesRepository.deleteById(sales.getSalesid());
		} else {
			Sales tempsales = salesRepository.findById(sales.getSalesid()).get();
			tempsales.setSales(sales.getSales());
			salesRepository.save(tempsales);
		}
	}
}
