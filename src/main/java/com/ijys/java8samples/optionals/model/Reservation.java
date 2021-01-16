package com.ijys.java8samples.optionals.model;

import java.util.Date;

public class Reservation {
	private Long id;
	private Date data;
	private Member member;

	public Reservation() {
	}

	public Reservation(Long id, Date data, Member member) {
		this.id = id;
		this.data = data;
		this.member = member;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
				", data=" + data +
				", member=" + member +
				'}';
	}
}
