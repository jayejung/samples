package com.ijys.java8samples.functionalinterfaces;

import java.util.function.Consumer;
import java.util.function.Function;

public class ConsumerExample {
	// return void이며, 입력된 값을 순수하게 소비할때 사용

	public static void main(String[] args) {
		// anonymous method
		final Consumer<String> sayHello = new Consumer<String>() {
			@Override
			public void accept(String value) {
				System.out.println(value);
			}
		};

		// lambda expression
		final Consumer<String> greeting = value -> System.out.println(value);

		sayHello.accept("Hello");
		greeting.accept("Howdy");
	}
}
