package com.ijys.java8samples.functionalinterfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class PredicateExample {
	// 주어진 값이 특정 조건에 맞는지 확인할때 주로 사용

	public static void main(String[] args) {
		// anonymous method
		Predicate<Integer> isPositive = new Predicate<Integer>() {
			@Override
			public boolean test(Integer integer) {
				return integer > 0;
			}
		};

		// lambda expression
		Predicate<Integer> isPositive2 = value -> value > 0;

		System.out.println(isPositive.test(1));
		System.out.println(isPositive2.test(0));

		// Function으로도 아래와 같이 동일한 기능 구현이 가능함
		Function<Integer, Boolean> isPositive3 = value -> value > 0;
		System.out.println(isPositive3.apply(0));


		List<Integer> numbers = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);
		// 위의 List에서 양수만 추출하는 평이한 방법
		List<Integer> positiveNumbers = new ArrayList<>();
		for (Integer num : numbers) {
			if (isPositive.test(num)) {
				positiveNumbers.add(num);
			}
		}
		System.out.println("positiveNumbers: " +  positiveNumbers);

		// 만약 3보다 작은 수를 추출한다면? 위의 코드를 복제할듯?
		Predicate<Integer> isLessThan3 = value -> value < 3;
		List<Integer> less3Numbers = new ArrayList<>();
		for (Integer num : numbers) {
			if (isLessThan3.test(num)) {
				less3Numbers.add(num);
			}
		}
		System.out.println("less3Numbers: " + less3Numbers);

		// 위와 같이 duplicate된 boiler plate code를 중복 생성하지 않고, predicate를 filter method에 전달하여 filtering 할 수 있음.
		System.out.println("less3Numbers with generic method: " + filter(numbers, isLessThan3));
		// or 아래와 같이 lambda를 전달 할 수도 있음. 위와 같이 하는게 더 명시적을 수 있음.
		System.out.println("less3Numbers with generic method: " + filter(numbers, value -> value < 3));
	}

	private static <T> List<T> filter(List<T> list, Predicate<T> condition) {
		List<T> result = new ArrayList<>();
		for (T input: list) {
			if (condition.test(input)) {
				result.add(input);
			}
		}
		return result;
	}
}
