package com.ijys.java8samples.functionalinterfaces.real2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class DiscountedProductExample {
	public static void main(String[] args) {
		final List<Product> products = Arrays.asList(
				new Product(1L, "A", new BigDecimal("10.00")),
				new Product(2L, "B", new BigDecimal("55.50")),
				new Product(3L, "C", new BigDecimal("17.45")),
				new Product(4L, "D", new BigDecimal("20.00")),
				new Product(5L, "E", new BigDecimal("110.99"))
		);

		final List<Product> expensiveProducts = filter(products, product -> product.getPrice().compareTo(new BigDecimal("50")) > 0);

		/*
		ProductExample과 마찮가지로 predicate를 사용하여 sub-list 추출하고 있음.
		 */

//		List <DiscountedProduct> discountedProducts = new ArrayList<>();
//		for (final Product product : expensiveProducts) {
// 			discountedProducts.add(new DiscountedProduct(product.getId(), product.getName(), product.getPrice()));
//		}


		final List<DiscountedProduct> discountedProducts =
				map(expensiveProducts, product -> new DiscountedProduct(product.getId(), product.getName(), product.getPrice().multiply(new BigDecimal("0.9"))));
		System.out.println("expensive products: " + expensiveProducts);
		System.out.println("discounted products: " + discountedProducts);

		System.out.println("discounted products under $50: " +
				filter(discountedProducts, product -> product.getPrice().compareTo(new BigDecimal("50")) < 0)
		);

		// 위의 predicate를 자주 사용하기 위해서 명시적으로 predicate를 선언
		final Predicate<Product> lessThanOrEqualTo50 = product -> product.getPrice().compareTo(new BigDecimal("50")) < 0;
		System.out.println("new discounted products: " + filter(discountedProducts, lessThanOrEqualTo50));
		System.out.println("new expensive products: " + filter(expensiveProducts, lessThanOrEqualTo50));

		/*
		DiscountedProduct용으로 생성한 predicate를 Product에 사용할 수 없다는 컴파일 오류 발생
		반대로 Product용으로 생성한 predicate도 DiscountedProduct에 사용 할 수 없음

		해결은...
		LSP (Liskov Substitution Principle) - super type은 sub type으로 대체가 가능해야함
		filter의 predicate에 상속관계를 추가 (T의 super type이면 허용 가능)
		기존 Predicate<T> ===> Predicate<? super T>
		predicate는 super용으로 생성 Precidate<Product> lessThanOrEqualTo50로
		 */

		// total price
		List<BigDecimal> prices = map(products, product -> product.getPrice());
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (final BigDecimal price : prices) {
			//totalPrice.add(price); BigDecimal은 immutable이므로 아래와 같이..
			totalPrice = totalPrice.add(price);
		}
		System.out.println("Total Price: " + totalPrice);

		/*
		Function(mapper)의 apply를 이용한 totalPrice 계산
		 */
		BigDecimal totalPrice2 = total(products, product -> product.getPrice());

		System.out.println("Total Price with FuncIF: " + totalPrice2);
	}

	private static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
		final List<T> result = new ArrayList<>();
		for (final T t : list) {
			if (predicate.test(t)) {
				result.add(t);
			}
		}
		return result;
	}

	private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
		List<R> result = new ArrayList<>();
		for (final T product : list) {

			result.add(function.apply(product));
		}

		return result;
	}

	private static <T> BigDecimal total(List<T> list, Function<T, BigDecimal> mapper) {
		BigDecimal total = BigDecimal.ZERO;
		for (final T t : list) {
			total = total.add(mapper.apply(t));
		}
		return total;
	}
}