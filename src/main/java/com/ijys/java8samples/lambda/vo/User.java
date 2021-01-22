package com.ijys.java8samples.lambda.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter @Setter
public class User {
	private String id;
	private String name;
	private int age;
}
