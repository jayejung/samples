package com.ijys.java8samples.stream.parallel;

import com.ijys.java8samples.stream.vo.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * 좀 더 현실적인 상황을 반영
 */
public class StreamExamples5ParallelPerformancePractical {
	private static final String[] priceStrings = {"1.0", "100.99", "35.75", "21.30", "88.00"};
	private static final BigDecimal[] targetPrices = {new BigDecimal("30"), new BigDecimal("20"), new BigDecimal("31")};
	private static final Random random = new Random(123);
	private static final Random targetPriceRandom = new Random(111);

	private static final List<Product> products;

	static {
		final int length = 8_000_000;
		final List<Product> list = new ArrayList<>(length);

		for (int i = 1; i <= length; i++) {
			list.add(new Product((long) i, "Product" + i, new BigDecimal(priceStrings[random.nextInt(5)])));
		}
		products = Collections.unmodifiableList(list);
	}

	public static void main(String[] args) {
		final BigDecimal targetPrice = new BigDecimal("40");

		imperativeTest(targetPrice);
		streamTest(targetPrice);
		parallelStreamTest(targetPrice);

		/* JVM 초기 설정시 JVM자체의 load가 있으니 처음 테스트는 무시하는게 좋음 */
		System.out.println("\nIgnore Tests Above\n==========================\n");

		System.out.println("Start!");

		/* target price로 random을 사용하여 캐싱된 계산을 하지 않게 한다. */
		for (int i = 0; i < 5; i++) {
			BigDecimal price = targetPrices[targetPriceRandom.nextInt(3)];
			imperativeTest(price);
			streamTest(price);
			parallelStreamTest(price);
		}
	}

	private static BigDecimal imperativeSum(final List<Product> products, final Predicate<Product> predicate) {
		BigDecimal sum = BigDecimal.ZERO;
		for (final Product product : products) {
			if (predicate.test(product)) {
				sum = sum.add(product.getPrice());
			}
		}
		return sum;
	}

	private static BigDecimal streamSum(final Stream<Product> stream, final Predicate<Product> predicate) {
		return stream.filter(predicate).map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private static void imperativeTest(BigDecimal targetPrice) {
		System.out.println("===================================================");
		System.out.println("\nImperative Sum\n--------------------------------------------------");
		final long start = System.currentTimeMillis();
		System.out.println("Sum: "
				+ imperativeSum(products, product -> product.getPrice().compareTo(targetPrice) >= 0));
		System.out.println("It took " + (System.currentTimeMillis() - start) + " ms.");
		System.out.println("===================================================");
	}

	private static void streamTest(BigDecimal targetPrice) {
		System.out.println("===================================================");
		System.out.println("\nStream Sum\n--------------------------------------------------");
		final long start = System.currentTimeMillis();
		System.out.println("Sum: "
				+ streamSum(products.stream(), product -> product.getPrice().compareTo(targetPrice) >= 0));
		System.out.println("It took " + (System.currentTimeMillis() - start) + " ms.");
		System.out.println("===================================================");
	}

	private static void parallelStreamTest(BigDecimal targetPrice) {
		System.out.println("===================================================");
		System.out.println("\nParallel Stream Sum\n--------------------------------------------------");
		final long start = System.currentTimeMillis();
		System.out.println("Sum: "
				+ streamSum(products.parallelStream(), product -> product.getPrice().compareTo(targetPrice) >= 0));
		System.out.println("It took " + (System.currentTimeMillis() - start) + " ms.");
		System.out.println("===================================================");
	}

}
