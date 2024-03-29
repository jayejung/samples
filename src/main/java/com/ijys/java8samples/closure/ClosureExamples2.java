package com.ijys.java8samples.closure;

/**
 * anonymous class와 lambda expression을 각각 comment 처리하고, compile해보고 파일을 확인.
 * labda expression이 byte code로 변경될때 anonymous class로 전환되는게 아님이 확인됨.
 * <p>
 * 단, 컴파일 타임에는 object를 생성하지 않으나... runtime에는 object가 필요하고, dynamic으로 object를 생성함(desugar).
 */
public class ClosureExamples2 {
    private int number = 999;

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        int number = 100;

//		Runnable runnable = new Runnable() {
//			@Override
//			public void run() {
//				System.out.println(number);
//			}
//		};
//
//		runnable.run();

		/*
		아래와 같은 closure를 사용하는 lambda는 anonymous class와 거의 동일한 성능이고..
		아래와 같이 내부에서 모든 처리를 하는 lambda는 최고 60배 가까이 빠르다고 함.
		Function <Integer> doubledFunc = i -> i * 2;
		 */
        Runnable runnable1 = () -> System.out.println(number);
        runnable1.run();
    }
}
