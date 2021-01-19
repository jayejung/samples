package com.ijys.java8samples.optionals.model;

import java.util.Date;

public class Reservation {
	private Long id;
	private Date date;
	private Member member;

	public Reservation() {
	}

	public Reservation(Long id, Date date, Member member) {
		this.id = id;
		this.date = date;
		this.member = member;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", data=" + date +
				", member=" + member +
				'}';
	}
}
