package com.example.supermarket.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * テスト
 * 
 * @author Kaneda
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usr")
public class User {
	@Id
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	@Enumerated(EnumType.STRING)
	private RoleName roleName;

}
