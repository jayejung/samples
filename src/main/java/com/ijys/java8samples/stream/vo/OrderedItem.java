package com.ijys.java8samples.stream.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class OrderedItem {
	private Long id;
	private Product product;
	private int quantity;

	public BigDecimal getTotalPrice() {
		return product.getPrice().multiply(new BigDecimal(quantity));
	}
}
