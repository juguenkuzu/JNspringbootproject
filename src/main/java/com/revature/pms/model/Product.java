package com.revature.pms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "revatureproducts")
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	private int productId;
	private String productName;
	private int quantityOnHand;
	private int price;
}
