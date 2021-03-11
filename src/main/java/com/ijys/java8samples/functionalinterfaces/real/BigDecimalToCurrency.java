package com.ijys.java8samples.functionalinterfaces.real;

import java.math.BigDecimal;

@FunctionalInterface
public interface BigDecimalToCurrency {
	String toCurrency(BigDecimal value);
}
