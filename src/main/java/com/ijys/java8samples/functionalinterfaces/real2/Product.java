package com.ijys.java8samples.functionalinterfaces.real2;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Product {
	private Long id;
	private String name;
	private BigDecimal price;
}
