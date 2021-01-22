package com.ijys.java8samples.lambda.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class SvcUser extends User {
	private String svcUserLevel;

	public SvcUser(String id, String name, int age, String svcUserLevel ) {
		super(id, name, age);
		this.svcUserLevel = svcUserLevel;
	}
}
