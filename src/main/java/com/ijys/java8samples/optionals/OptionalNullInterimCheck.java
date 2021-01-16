package com.ijys.java8samples.optionals;

import com.ijys.java8samples.optionals.model.DeliveryAddress;
import com.ijys.java8samples.optionals.model.Member;
import com.ijys.java8samples.optionals.model.Reservation;

import java.util.Date;
import java.util.Optional;

public class OptionalNullInterimCheck {
	public static void main(String[] args) {
		// idle
//		DeliveryAddress deliveryAddress = new DeliveryAddress("판교로", "성남시", "07111");
//		Member member = new Member(1L, "ryan", deliveryAddress);
//		Reservation reservation = new Reservation(1L, new Date(), member);

		// NPE
		DeliveryAddress deliveryAddress = new DeliveryAddress("판교로", "성남시", "07111");
		Member member = new Member(1L, "ryan", deliveryAddress);
		Reservation reservation = new Reservation(1L, new Date(), member);

		System.out.println(getCityOfMemberFromReservation(reservation));
	}

	private static String getCityOfMemberFromReservation(Reservation reservation) {
		Optional<Reservation> reservationOpt = Optional.ofNullable(reservation);

		if (reservationOpt.isPresent()) {
			Optional<Member> memberOpt = Optional.ofNullable(reservationOpt.get().getMember());
			if (memberOpt.isPresent()) {
				Optional<DeliveryAddress> deliveryAddressOpt = Optional.ofNullable(memberOpt.get().getDeliveryAddress());
				if(deliveryAddressOpt.isPresent()) {
					DeliveryAddress deliveryAddress = deliveryAddressOpt.get();
					Optional<String> cityOpt = Optional.ofNullable(deliveryAddress.getCity());
					if (cityOpt.isPresent()) {
						return cityOpt.get();
					}
				}
			}
		}

		return "Seoul";
	}
}
