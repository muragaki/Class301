package com.example.supermarket.app.admin;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEditForm {
	private String itemcode;
	@Size(min=1, max=16)
	private String itemname;
	@Min(1)
	@Max(9999)
	private int itemprice;
	private boolean enableflag;
}
