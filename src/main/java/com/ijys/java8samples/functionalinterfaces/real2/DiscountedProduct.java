package com.ijys.java8samples.functionalinterfaces.real2;

import lombok.ToString;

import java.math.BigDecimal;

@ToString(callSuper = true)
public class DiscountedProduct extends Product {
	public DiscountedProduct(Long id, String name, BigDecimal price) {
		super(id, name, price);
	}
}
