package com.ijys.java8samples.optionals.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@AllArgsConstructor
@ToString
public class DeliveryAddress {
	private String street;
	private String city;
	private String zipcode;
}
