package com.ijys.java8samples.optionals;

import com.ijys.java8samples.optionals.model.DeliveryAddress;
import com.ijys.java8samples.optionals.model.Member;
import com.ijys.java8samples.optionals.model.Reservation;

import java.util.Date;
import java.util.Optional;

public class OptionalNullCheck {
	public static void main(String[] args) {
		// idle
//		DeliveryAddress deliveryAddress = new DeliveryAddress("판교로", "성남시", "07111");
//		Member member = new Member(1L, "ryan", deliveryAddress);
//		Reservation reservation = new Reservation(1L, new Date(), member);

		// NPE
		DeliveryAddress deliveryAddress = new DeliveryAddress("판교로", "성남시", "07111");
		Member member = new Member(1L, "ryan", null);
		Reservation reservation = new Reservation(1L, new Date(), member);

		System.out.println(getCityOfMemberFromReservation(reservation));
	}

	private static String getCityOfMemberFromReservation(Reservation reservation) {
		return Optional.ofNullable(reservation)
				.map(Reservation::getMember)
				.map(Member::getDeliveryAddress)
				.map(DeliveryAddress::getCity)
				.orElse("Seoul");
	}
}
