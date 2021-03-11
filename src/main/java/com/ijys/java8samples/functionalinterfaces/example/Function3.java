package com.ijys.java8samples.functionalinterfaces.example;

@FunctionalInterface
public interface Function3<T1, T2, T3, R> {
	/*
	abstract method가 1개 일때, lambda를 사용했었을때, 두번째 abstract method 추가가 되면 사용했던 모든 lambda expression에서
	compile error 발생됨.
	그래서, @FunctionalInterface 로 SAM 상태를 유지하게 해주고, 추가 method는 default로 기본 method body를 만들어 제공한다.
	물론 child interface에서 override 가능함.
	 */
	R apply(T1 t1, T2 t2, T3 t3);

	// void print(int i);
	default void print(int i) {
		System.out.println(i);
	}
}