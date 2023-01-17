package com.example.supermarket.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

// コメントby増田

@Data
@Entity
@Table(name = "m_item")
public class MItem {
	@Id
	private String itemcode;
	private String itemname;
	private int itemprice;
	private boolean enableflag;
}
