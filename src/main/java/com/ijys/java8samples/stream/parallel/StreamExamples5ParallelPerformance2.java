package com.ijys.java8samples.stream.parallel;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * StreamExamples5ParallelPerformance 을 좀 더 현실성을 반영하였음.
 * stream 연산의 iteration중 computing power가 필요한 연산이 있다고 가정
 */
public class StreamExamples5ParallelPerformance2 {

	/*
	10ms processing time이 있는 연산이 있다고 가정
	 */
	private static void slowDown() {
		try {
			TimeUnit.MILLISECONDS.sleep(10L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	가우스 방식, 가장 빠름 알고리즘의 힘
	0 ms
	 */
	public static long gaussSum(long n) {
		return (1 + n) * (n / 2);
	}

	/*
	걍 싱글core로 빠르게 계산
	10978 ms
	 */
	public static long iterativeSum(long n) {
		long result = 0;
		for (long i = 0; i <= n; i++) {
			result += i;
			slowDown();
		}
		return result;
	}

	/*
	slowDown method를 직접 사용 못하므로, peek method를 통해서 사용.
	11010 ms
	 */
	public static long sequentialSum(long n) {
		return Stream.iterate(1L, i -> i + 1).limit(n).peek(i -> slowDown()).reduce(Long::sum).get();
	}

	/*
	iterate의 i -> i + 1은 index -1의 값이 있어야 iterate 가능함.
	예들 들어... 1 ~ 10 이라고 하고 core가 5개면... 각 코어가 1~2, 3~4, 5~6, 7~8, 9~10 계산을 나눠서 하려고 하는데...
	3~4는 1~2의 결과가 나와야 진행되므로.. 기다리게 되고 이전 값(1~2)을 계속 확인해야해서... 더 느리게 동작해야하지만....
	slowDown()고 같이 시간이 소요되는 연산이 있으면 시퀀셜보다 빠르게 동작함.
	694 ms
	 */
	public static long parallelSum(long n) {
		return Stream.iterate(1L, i -> i + 1).limit(n).parallel().peek(i -> slowDown()).reduce(Long::sum).get();
	}

	/*
	10960 ms
	 */
	public static long rangedSum(long n) {
		return LongStream.rangeClosed(1, n).peek(i -> slowDown()).reduce(Long::sum).getAsLong();
	}

	/*
	다음 값을 기다려야하는 i -> i + 1 lambda식은 아니지만...
	range를 core 개수로 정확하게 나눠서 계산하고 sum하지 않고, 무수히 많은(랜덤한) 개수로 나눠서 별도 계산하고 sum하는 과정때문에
	computing power를 더 사용하게 되면서 rangedSum 방식보다는 더 느림. (24ms)
	range에 parallel이 더 좋은 성능을 보이는 경우는.. parallel()이후에 intermediate method에 복잡하고 시간이 오래 걸리는 연산이 있을 경우.
	즉, 연산이 복잡하고 오래 걸릴 경우, core를 여러개 사용할 수 있는 parallel이 더 효율 적임. 위와 같이 1 ~ 1_000_000 연산은 무의미함.
	694 ms
	 */
	public static long parallelRangedSum(long n) {
		return LongStream.rangeClosed(1, n).parallel().peek(i -> slowDown()).reduce(Long::sum).getAsLong();
	}

	public static void main(String[] args) {
		final long n = 1_000;

		final long start0 = System.currentTimeMillis();
		System.out.println("         gaussSum(n): " + gaussSum(n));
		System.out.println("                      " + (System.currentTimeMillis() - start0) + " ms\n");

		final long start1 = System.currentTimeMillis();
		System.out.println("     iterativeSum(n): " + iterativeSum(n));
		System.out.println("                      " + (System.currentTimeMillis() - start1) + " ms\n");

		final long start2 = System.currentTimeMillis();
		System.out.println("    sequentialSum(n): " + sequentialSum(n));
		System.out.println("                      " + (System.currentTimeMillis() - start2) + " ms\n");

		final long start3 = System.currentTimeMillis();
		System.out.println("      parallelSum(n): " + parallelSum(n));
		System.out.println("                      " + (System.currentTimeMillis() - start3) + " ms\n");

		final long start4 = System.currentTimeMillis();
		System.out.println("        rangedSum(n): " + rangedSum(n));
		System.out.println("                      " + (System.currentTimeMillis() - start4) + " ms\n");

		final long start5 = System.currentTimeMillis();
		System.out.println("parallelRangedSum(n): " + parallelRangedSum(n));
		System.out.println("                      " + (System.currentTimeMillis() - start5) + " ms\n");
	}
}
