package com.ijys.java8samples.stream;

public class StreamPrelude {
	public static void main(String[] args) {
		final int abs1 = Math.abs(-1);
		final int abs2 = Math.abs(1);

		System.out.println("abs1: " + abs1);
		System.out.println("abs2: " + abs2);

		System.out.println("abs1 == abs2 is " + (abs1 == abs2));

		final int minInt = Math.abs(Integer.MIN_VALUE);

		System.out.println("minInt: " + minInt);
		/*
		minInt: -2147483648 로 출력
		java에는 unsigned int가 없고 int --> 32bit임.
		 */

		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);

		/*
		@Native public static final int   MIN_VALUE = 0x80000000;
		@Native public static final int   MAX_VALUE = 0x7fffffff;

		Max value와 Min value는 1차이임.
		 */

		/*
		즉, abs는 functional programming에서 말하는 function이 아님.
		값의 오류에 대한게 아니라, 입력값에 대해서 예상/유추 할수 있는 결과가 나오지 않으므로?
		 */
	}
}
