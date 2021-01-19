package com.ijys.java8samples.optionals;

import java.util.*;

public class TransOptionalFromLegacy {
	public static void main(String[] args) {

		/*************************************************************
		 * null return
		 *************************************************************/
		Map<Integer, String> cities = new HashMap<>();
		cities.put(1, "Seoul");
		cities.put(2, "Busan");
		cities.put(3, "Daejeon");

		// Map인터페이스의 get 메소드는, 인덱스에 해당되는 값이 없으면 null을 반환.
		// 따라서 해당 API를 사용하는 코드를 null-safe하게 만들기 위해서는 null check를 해줘야함.
		String city = cities.get(4); // return null
		int length = city == null ? 0 : city.length(); // null check
		System.out.println(length);

		// 아래와 같이 optional에 넣으면 null-safe한 코드가 됨.
		Optional<String> cityOpt = Optional.ofNullable(cities.get(4));
		int lengthOpt = cityOpt.map(String::length).orElse(0); // null-safe
		System.out.println(lengthOpt);

		/*************************************************************
		 * throw Exception
		 *************************************************************/
		List<String> cityList = Arrays.asList("Seoul", "Busan", "Daejeon");

		// list의 get()메소드는 try-catch로 예외처리해야하고, null check도 해야함. 그래서 코드가 좀 지저분해짐.
		String city2 = null;
		try {
			city2 = cityList.get(3);
		} catch (ArrayIndexOutOfBoundsException ex) {
			// something to do...
		}
		int length2 = city2 == null ? 0 : city2.length(); // null check
		System.out.println(length2);

		// 위와 같이 try-catch block과 null check가 같이 있다면, static method로 예외처리를 빼주는게 좋음 (getAsOptional)
		Optional<String> cityOpt2 = getAsOptional(cityList, 3); // optional
		int length3 = cityOpt2.map(String::length).orElse(0);
		System.out.println(length3);
	}

	private static <T> Optional<T> getAsOptional(List<T> list, int index) {
		try {
			return Optional.of(list.get(index));
		} catch (IndexOutOfBoundsException ex) {
			return Optional.empty();
		}
	}
}
