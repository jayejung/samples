package com.ijys.java8samples.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamExamples2 {
	public static void main(String[] args) {
		/*
		Stream은 collection builder?
		 */
		Stream.of(1, 2, 3, 4, 5)
				.forEach(i -> System.out.print(i + " "));
		System.out.println();

		/*
		전통적인 방식 imperative programming
		 */
		final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		//final List<Integer> result = new ArrayList<>();
		Integer result = null;
		for (final Integer number : numbers) {
			if (number > 3 && number < 9) {
				// 1. 2배로..
				final Integer newNumber = number * 2;
				// 2. 10보다 크면서 가장 처음 나온 값은?
				if (newNumber > 10) {
					//result.add(newNumber);
					result = newNumber;
					break;
				}
			}
		}
		System.out.println("Imperative Result: " + result);

		/*
		Stream으로..
		 */
		System.out.println("Functional Result: " +
				numbers.stream()
						.filter(number -> number > 3)
						.filter(number -> number < 9)
						.map(number -> number * 2)
						.filter(number -> number > 10)
						.findFirst()
		);

		/*
		연산 횟수를 검증해보자
		 */
		System.out.println("Functional Result: " +
				numbers.stream()
						.filter(number -> {
							System.out.println("number > 3");
							return number > 3;
						})
						.filter(number -> {
							System.out.println("number < 9");
							return number < 9;
						})
						.map(number -> {
							System.out.println("number * 2");
							return number * 2;
						})
						.filter(number -> {
							System.out.println("number > 10");
							return number > 10;
						})
						.findFirst()
		);

		/*
		Stream은 lazy colleciton builder
		모든 필터와 map을 계속 순차적으로 수행하지 않고... lazy하게 수행함.
		그래서 성능이 나쁘지 않음
		 */

		/*
		(귀찮고 바보같지만) 직접 만들어 보자.
		일단 list, map static method를 먼저 구현해 놓고...
		위의 Stream 방식과 같이 연산회수를 확인해보면... stream이 더 효율적임.
		게다가 Stream 방식은 Optional을 리턴하니 IndexOutOfBoundsException이나 NPE이 발행하지 않음.
		 */
		final List<Integer> greaterThan3 = filter(numbers, number -> {
			System.out.println("i > 3");
			return number > 3;
		});
		final List<Integer> lessThan9 = filter(greaterThan3, number -> {
			System.out.println("i < 9");
			return number < 9;
		});
		final List<Integer> doubled = map(lessThan9, number -> {
			System.out.println("i * 2");
			return number * 2;
		});
		final List<Integer> greaterThan10 = filter(doubled, number -> {
			System.out.println("i > 10");
			return number > 10;
		});
		System.out.println(greaterThan10.get(0));

	}

	private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
		final List<T> result = new ArrayList<>();
		for (final T t : list) {
			if (predicate.test(t))
				result.add(t);
		}

		return result;
	}

	private static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
		final List<R> result = new ArrayList<>();
		for (final T t : list) {
			result.add(mapper.apply(t));
		}

		return result;
	}
}
