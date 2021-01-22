package com.ijys.java8samples.lambda.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class Admin extends User {
	private String adminLevel;

	public Admin(String id, String name, int age, String adminLevel) {
		super(id, name, age);
		this.adminLevel = adminLevel;
	}
}
