package com.ijys.java8samples.functionalinterfaces;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class SupplierExample {
	// Supplier는 SAM T get() 하나만 가지고 있음.

	public static void main(String[] args) {
		final Supplier<String> helloSupplier = () -> "hello";

		System.out.println(helloSupplier.get() + " world");

		printIfValidIndex(0, "howdy");
		printIfValidIndex(-1, "howdy1");

		System.out.println("Start of Very Expensive Calculation");
		long start = System.currentTimeMillis();
		printIfValidIndex(0, getVeryExpensiveCostProcess(1));
		printIfValidIndex(-1, getVeryExpensiveCostProcess(1));
		printIfValidIndex(-2, getVeryExpensiveCostProcess(1));

		System.out.println("it took " + ((System.currentTimeMillis() - start) / 1000) + " second(s)");

		// process가 오래 걸리는 메소드 호출을 직접 하지 않고, supplier를 통해서 오래된 메소드를 실행하게 되면 시간이 단축됨.
		// printIfValidIndex(int, getVeryExpensiveCostProcess())의 경우는
		// - printIfValidIndex를 호출할때마다 getVeryExpensiveProcess호출
		// printIfValidIndex(int, Supplier<String>)은 supplier get할때만.
		// 즉, lazy loading 효과가 있음.
		long start2 = System.currentTimeMillis();
		// anonymous method
		printIfValidIndex(0, new Supplier<String>() {
			@Override
			public String get() {
				return getVeryExpensiveCostProcess(2);
			}
		});
		// lambda expression
		printIfValidIndex(-1, () -> getVeryExpensiveCostProcess(2));
		printIfValidIndex(-2, () -> getVeryExpensiveCostProcess(2));

		System.out.println("it took " + ((System.currentTimeMillis() - start2) / 1000) + " second(s)");
	}

	private static void printIfValidIndex(int number, String value) {
		if (number >= 0) {
			System.out.println("value is " + value + ".");
		} else {
			System.out.println("invalid");
		}
	}

	private static void printIfValidIndex(int number, Supplier<String> valueSupplier) {
		if (number >= 0) {
			System.out.println("value is " + valueSupplier.get() + ".");
		} else {
			System.out.println("invalid");
		}
	}

	private static String getVeryExpensiveCostProcess(int cateNo) {
		// 고비용의 연산이 된다고 가정해보자
		System.out.println("cateNo: " + cateNo);
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Result that expensive";
	}
}
