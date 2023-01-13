package com.example.supermarket.domain.repository.cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.supermarket.domain.model.Sales;

public interface SalesRepository extends JpaRepository<Sales, Integer> {
	List<Sales> findByUseridOrderBySalesidAsc(String userid);
	Sales readByItemcode(String itemcode);
	Sales readByItemcodeAndUserid(String itemcode, String userid);
}