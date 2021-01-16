package com.ijys.java8samples.optionals.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Member {
	private Long id;
	private String name;
	private DeliveryAddress deliveryAddress;
}
