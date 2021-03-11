package com.ijys.java8samples.functionalinterfaces.real2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ProductExample {
	public static void main(String[] args) {
		final List<Product> products = Arrays.asList(
				new Product(1L, "A", new BigDecimal("10.00")),
				new Product(2L, "B", new BigDecimal("55.50")),
				new Product(3L, "C", new BigDecimal("17.45")),
				new Product(4L, "D", new BigDecimal("20.00")),
				new Product(5L, "E", new BigDecimal("110.99"))
		);

		BigDecimal twenty = new BigDecimal("20");

		/*
		Old한 방식의 price가 $20 이상의 product의 sub-list
		 */
//		List<Product> result = new ArrayList<>();
//		for (final Product product : products) {
//			if (product.getPrice().compareTo(twenty) >= 0) {
//				result.add(product);
//			}
//		}


		/*
		predicate로 $20 이상을 .test하여 sub-list룰 추출할 수 있고, 다양한 조건의 labda자체를 넘길 수 있음
		filter method가 추가로 생성되었고, 전통적인 방식으로도 sub-list 추출을 위한 추가 메소드가 필요했었으니 복잡도가 늘어난것 은 아님
		 */
		Predicate<Product> orOverTwenty = product -> product.getPrice().compareTo(twenty) >= 0;
		System.out.println(products);
		System.out.println("products >= $20 : " +
				filter(products, orOverTwenty));
		System.out.println("products >= $15 : " +
				filter(products, product -> product.getPrice().compareTo(new BigDecimal("15")) >= 0));
	}

	private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
		final BigDecimal twenty = new BigDecimal("20");
		List<T> result = new ArrayList<>();

		for (final T t : list) {
			if (predicate.test(t)) {
				result.add(t);
			}
		}

		return result;
	}
}
