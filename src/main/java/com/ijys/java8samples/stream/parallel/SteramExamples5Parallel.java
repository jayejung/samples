package com.ijys.java8samples.stream.parallel;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SteramExamples5Parallel {
	public static void main(String[] args) {
		final int[] sum = {0};
		final int[] sum2 = {0};
		// single thread
		IntStream.range(0, 100)
				.forEach(i -> sum[0] += i);
		System.out.println("stream sum: " + sum[0]);

		// race condition: 여러개의 parallel thread가 하나의 값(sum2)를 변경하고 있음.
		IntStream.range(0, 100)
				.parallel()
				.forEach(i -> sum2[0] += i);
		System.out.println("parallel sum(with side-effect): " + sum2[0]);

		System.out.println("stream sum (no side effect)" +
				IntStream.range(0, 100)
						.sum());

		System.out.println("parallel stream sum (no side effect)" +
				IntStream.range(0, 100)
						.parallel()
						.sum());

		System.out.println("\n=======================");
		System.out.println("Stream");
		final long start = System.currentTimeMillis();
		Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
				.stream()
				.map(i -> {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return i;
				})
				.forEach(i -> System.out.println(i));

		System.out.println("Time elapsed: " + (System.currentTimeMillis() - start));

		System.out.println("\n=======================");
		System.out.println("Parallel Stream (16 elements)");
		final long start2 = System.currentTimeMillis();
		Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
				.parallelStream()
				.map(i -> {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return i;
				})
				.forEach(i -> System.out.println(i));

		System.out.println("Time elapsed: " + (System.currentTimeMillis() - start2));

		System.out.println("\n=======================");
		System.out.println("Parallel Stream (17 elements)");
		final long start3 = System.currentTimeMillis();
		Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17)
				.parallelStream()
				.map(i -> {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return i;
				})
				.forEach(i -> System.out.println(i));

		System.out.println("Time elapsed: " + (System.currentTimeMillis() - start3));


		/*
		core 개수를 parallelism으로 제어하려고 했으나.. jdk 버전의 이슈인지 runtime에서 조절이 안되었음.
		실행시 아래와 같은 옵션을 줘야할듯
		-Djava.util.concurrent.ForkJoinPool.common.parallelism=20
		 */
		System.out.println("\n=======================");
		System.out.println("Parallel Stream (16 elements) with parallelism: 14");
		System.out.println("getParallelism: " + ForkJoinPool.commonPool().getParallelism());
		// 14는 15 cores
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "1");
		System.out.println("getParallelism: " + ForkJoinPool.commonPool().getParallelism());
		final long start4 = System.currentTimeMillis();
		Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
				.parallelStream()
				.map(i -> {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return i;
				})
				.forEach(i -> System.out.println(i));

		System.out.println("Time elapsed: " + (System.currentTimeMillis() - start4));

		/*
		parallel stream에서는 순서가 보장되지 않음.
		일반적인 ORM 에서는 thread safe하지 않은 경우가 많기 때문에 lazy fetch에서는 parallel을 사용하면 안되고,
		eager fetch일 경우에만 사용해야함. (JPA 사용시 주의해야함)
		 */
	}
}
