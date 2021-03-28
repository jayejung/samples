package com.ijys.java8samples.stream;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExamples1 {
	public static void main(String[] args) {
		IntStream.range(1, 10).forEach(i -> System.out.print(i + " "));
		System.out.println();
		IntStream.rangeClosed(1, 10).forEach(i -> System.out.print(i + " "));

		IntStream.iterate(1, i -> i + 1)
				.forEach(i -> System.out.print(i));
		Stream.iterate(BigInteger.ONE, i -> i.add(BigInteger.ONE))
				.forEach(i -> System.out.println(i));
	}
}
