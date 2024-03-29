package com.ijys.java8samples.lambda;

import com.ijys.java8samples.lambda.vo.Admin;
import com.ijys.java8samples.lambda.vo.SvcUser;
import com.ijys.java8samples.lambda.vo.User;

import java.util.Comparator;

public class AdminUserCompareSample {
	public static void main(String[] args) {
		User svcUser = new SvcUser("u1", "jaye", 18, "level1");
		User admUser = new Admin("a1", "jj", 20, "admin1");

		Comparator<User> legacyComparator = new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				if (o1.getAge() > o2.getAge()) {
					return 1;
				} else {
					return 0;
				}
			}
		};

		// Comparator는 compare추상 메소드 하나만 가지고 있는(SAM-Single Abstract Method) functional interface이므로 noise 코드를 제거하면,
		// 아래와 같은 lambda expression으로 변경 가능
		Comparator<User> lambdaComparator = (o1, o2) -> {
			if (o1.getAge() > o2.getAge()) {
				return 1;
			} else {
				return 0;
			}
		};

		// Comparator 사용
		System.out.println(legacyComparator.compare(svcUser, admUser));
		System.out.println(lambdaComparator.compare(admUser, svcUser));
	}
}
