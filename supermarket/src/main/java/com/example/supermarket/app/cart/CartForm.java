package com.example.supermarket.app.cart;

import java.util.ArrayList;

import com.example.supermarket.domain.model.Sales;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class CartForm {
	@Valid
	private ArrayList<Sales> salesList = new ArrayList<>();
}
