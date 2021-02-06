package com.ijys.java8samples.functionalinterfaces;

import java.util.function.Function;

public class FunctionExample {
	// Mapper 용으로 주로 사용. 아래의 예는 String을 Integer로 변환(mapping)하여 리턴
	public static void main(String[] args) {
		// anonymous class
		Function<String, Integer> toInt = new Function<String, Integer>() {
			@Override
			public Integer apply(String value) {
				return Integer.valueOf(value);
			}
		};

		// lambda
		final Function<String, Integer> toInt2 = value -> Integer.valueOf(value);

		// identity method (type뿐 아닌 값 자체도 변형되면 안됨)
		final Function<Integer, Integer> identity = Function.identity();
		// or
		//final Function<Integer, Integer> identity = t -> t;

		int a = toInt.apply("123");
		int b = toInt2.apply("345");
		int c = identity.apply(456);

		System.out.println(a + 1);
		System.out.println(b + 1);
		System.out.println(c + 1);
	}
}
