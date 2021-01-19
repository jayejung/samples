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

		/*
			1. ofNullable static factory method를 통해서 reservation을 Optional로..
				reservation이 null일 수도 있으므로, ofNullable로 변경하였음.
			2. 이후, 3번의 연속적인 map 메소드를 통해서 Optional에 담긴 객체의 타입을 변경하였음.
				Optional<Reservation> -> Optional<Member> -> Optional<DeliveryAddress> -> Optional<String>
			3. orElse 메소드를 통해서, null 발견시 Seoul 기본값을 리턴하게함.
		 */
		return Optional.ofNullable(reservation)
				.map(Reservation::getMember)
				.map(Member::getDeliveryAddress)
				.map(DeliveryAddress::getCity)
				.orElse("Seoul");
	}

	/*
		legacy 방식의 reservation null check 및 mins check하여 member return
	 */
	private Member getMemberIfReservationWithin(Reservation reservation, int mins) {
		if (reservation != null && reservation.getDate().getTime() > System.currentTimeMillis() - mins * 1000) {
			return reservation.getMember();
		}
		return null;
	}

	/*
		추가로 filter()로 enhanced 한다면?

		return 형이 optional이어서 사용자에게 null이 담겨있는 optional 객체가 리턴될 수 있다는 의미를 전달 가능
		filter는 조건이 false이면 Optional을 비워버려서 이후의 map 메소드호출은 의미가 없어짐
	 */
	private Optional<Member> getMemberIfReservationWithinOptional(Reservation reservation, int mins) {
		return Optional.ofNullable(reservation)
				.filter(r -> r.getDate().getTime() > System.currentTimeMillis() - mins * 1000)
				.map(Reservation::getMember);
	}
}
