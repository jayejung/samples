package com.ijys.java8samples.optionals;

import com.ijys.java8samples.optionals.model.DeliveryAddress;
import com.ijys.java8samples.optionals.model.Member;
import com.ijys.java8samples.optionals.model.Reservation;

import java.util.Date;

public class LegacyNullCheck {
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

	public static String getCityOfMemberFromReservation(Reservation reservation) {
		// NPE 발생가능성 있음
		// return reservation.getMember().getDeliveryAddress().getCity();

		// hell of null checking
		if (reservation != null) {
			Member member = reservation.getMember();
			if (member != null) {
				DeliveryAddress deliveryAddress = member.getDeliveryAddress();
				if (deliveryAddress != null) {
					String city = deliveryAddress.getCity();
					if (city != null) {
						return city;
					}
				}
			}
		}

		return "Seoul";	// default
	}
}
