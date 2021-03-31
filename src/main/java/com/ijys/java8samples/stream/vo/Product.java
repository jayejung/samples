package com.ijys.java8samples.stream.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Product {
	private Long id;
	private String name;
	private BigDecimal price;
}
