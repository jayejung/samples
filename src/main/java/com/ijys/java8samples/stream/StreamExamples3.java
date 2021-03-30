package com.ijys.java8samples.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamExamples3 {
	public static void main(String[] args) {
		final Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
		/* 위와 같이 Stream을 생성해서 사용하는 경우보다 컬랙션을 스트림으로 변환해서 사용하는 경우가 더 많을 듯 */
		final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		numbers.stream()
				.filter(number -> number < 3);

		/*
		Terminal Operation Method, Intermediate Operation Method
		 */
		System.out.println("collect(toList()): " +
				Stream.of(1, 3, 3, 5, 5)
						.filter(number -> number > 2)
						.map(i -> i * 2)
						.map(i -> "#" + i)
						.collect(toList())
		);

		System.out.println("collect(toSet()): " +
				Stream.of(1, 3, 3, 5, 5)
						.filter(number -> number > 2)
						.map(i -> i * 2)
						.map(i -> "#" + i)
						.collect(toSet())
		);

		System.out.println("collect(joining([\", \"\", \"\", \"] )): " +
				Stream.of(1, 3, 3, 5, 5)
						.filter(number -> number > 2)
						.map(i -> i * 2)
						.map(i -> "#" + i)
						.collect(joining(", ", "[", "]"))
		);

		System.out.println("distinct().collect(joining([\", \"\", \"\", \"] )): " +
				Stream.of(1, 3, 3, 5, 5)
						.filter(number -> number > 2)
						.map(i -> i * 2)
						.map(i -> "#" + i)
						.distinct()
						.collect(joining(", ", "[", "]"))
		);

		System.out.println("distinct().collect(toList()): " +
				Stream.of(1, 3, 3, 5, 5)
						.filter(number -> number > 2)
						.map(i -> i * 2)
						.map(i -> "#" + i)
						.distinct()
						.collect(toList())
		);

		/*
		Stream.of의 파라메터는 autoboxing으로 Integer로 변환되었고
		filter에서는 primitive로 unboxing되어 filter 동작함.
		 */
		System.out.println(
				Stream.of(1, 2, 3, 4, 5)
						.filter(i -> i == 3)
						.findFirst()
		);

		/*
		memory reference가 달라서 filter 안될것 같지만... 찾음
		 */
		final Integer integer3 = 3;
		System.out.println(
				Stream.of(1, 2, 3, 4, 5)
						.filter(i -> i == integer3)
						.findFirst()
		);

		System.out.println(". filter(i -> i > interger3).count(): " +
				Stream.of(1, 2, 3, 4, 5)
						.filter(i -> i > integer3)
						.count()
		);

		/*
		external iterate: numbers를 위해서 외부에서 for 문으로 iterate
		 */
		for (Integer i : numbers) {
			System.out.println("i = " + i);
		}

		/*
		internal iterate
		 */
		Stream.of(1, 2, 3, 4, 5)
				.forEach(i -> System.out.println(i));
	}
}
