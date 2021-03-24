package com.ijys.java8samples.stream.identityfunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Example {
	public static void main(String[] args) {

		final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		System.out.println("mapOld(numbers, i -> i * 2):\n" +
				mapOld(numbers, i -> i * 2)
		);
		/*
		만약에 map으로 전달된 numbers를 변경없이 그대로 받고 싶다면??
		 */
		System.out.println("mapOld(numbers, null): \n" +
						mapOld(numbers, null)
				/*
				map method에서 mapper에 대한 nullcheck해야함.
				그리고, null인경우 parameter t를 R로 casting해야함.
				슬슬.. 이상한 코드가..
				 */
		);

		System.out.println("map(numbers, null): \n" +
						map(numbers, null)
				/*
				map method내에서 Function을 재정의 했지만...
				역시.. 이상한 코드가.. 게다가 casting도 여전히 살아 있음.
				 */
		);

		System.out.println("mapNew(numbers, i -> i): \n" +
						mapNew(numbers, i -> i)
				/*
				걍 null 이 안닌  i -> i 를 넘기면 됨.
				 */
		);

		System.out.println("mapNew(numbers, Function.identity()): \n" +
						mapNew(numbers, Function.identity())
				/*
				Function의 static method로 i -> i 가 정의되어 있으므로, Function.identity()도 전달 가능
					(identity()가 Function 객체 리턴)
				 */
		);
	}

	private static <T, R> List<R> mapNew(final List<T> list, final Function<T, R> mapper) {
		final List<R> result = new ArrayList<>();
		for (final T t : list) {
			result.add(mapper.apply(t));
		}
		return result;
	}

	private static <T, R> List<R> map(final List<T> list, final Function<T, R> mapper) {
		final Function<T, R> localMapper;
		if (mapper != null) {
			localMapper = mapper;
		} else {
			localMapper = t -> (R) t;
		}
		final List<R> result = new ArrayList<>();
		for (final T t : list) {
			result.add(localMapper.apply(t));
		}
		return result;
	}

	private static <T, R> List<R> mapOld(List<T> list, Function<T, R> mapper) {
		final List<R> result = new ArrayList<>();
		for (final T t : list) {
			if (mapper != null) {
				result.add(mapper.apply(t));
			} else {
				result.add((R) t);
			}
		}
		return result;
	}
}
