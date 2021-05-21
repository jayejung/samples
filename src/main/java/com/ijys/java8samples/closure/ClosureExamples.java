package com.ijys.java8samples.closure;

public class ClosureExamples {
	private int number = 999;

	public static void main(String[] args) {
		/*
		1. Effectively Final
		 */
		int number = 100;    // effectively final
		// number = 1; --> anonymous method or lambda에서 compile error

		System.out.println("Anonymous Class");
		final Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println(number);
			}
		};

		runnable.run();

		System.out.println("Lambda expression");
		final Runnable runnable2 = () -> System.out.println(number);
		runnable2.run();

		testClosure("New Anonymous Class", new Runnable() {
			@Override
			public void run() {
				System.out.println(number);
			}
		});

		testClosure("New Lambda expression", () -> System.out.println(number));

		/*
		2. lambda expression의 scope
		 */
		new ClosureExamples().nonStaticMemberRefClosure();

	}

	private static void testClosure(final String name, final Runnable runnable) {
		System.out.println("===============================");
		System.out.println(name + ": ");
		runnable.run();
		System.out.println("===============================");
	}

	private void nonStaticMemberRefClosure() {
		int number = 100;
		testClosure("non static ref. Anonymous Class", new Runnable() {
			@Override
			public void run() {
				// number = 999
				System.out.println(ClosureExamples.this.number);
			}
		});

		// number = 999
		/*
		Class member로 close over 하고 있음. closure!
		즉, lambda expression에 대한 scope은 없다
		 */
		testClosure("non static ref. Lambda Expression", () -> System.out.println(this.number));
	}
}
