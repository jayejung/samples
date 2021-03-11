package com.ijys.java8samples.functionalinterfaces.real;

import java.math.BigDecimal;

public class RealExample {
	public static void main(String[] args) {
		BigDecimalToCurrency bigDecimalToCurrency = bd -> "$" + bd.toString();

		System.out.println(bigDecimalToCurrency.toCurrency(new BigDecimal("1000.00")));

		/*
		Generic method는 lambda expression 사용 못함 -> interface 자체가 generic 이어야함. annonymous class는 가능.
		 */
		// final InvalidFunctionalInterface invalidFunctionalInterface = value -> value.toString();
		// System.out.println(invalidFunctionalInterface.makeString(123));

		final InvalidFunctionalInterface anonymousClass = new InvalidFunctionalInterface() {
			@Override
			public <T> String makeString(T value) {
				return value.toString();
			}
		};

		System.out.println("anonymous class: " + anonymousClass.makeString(123));
	}
}