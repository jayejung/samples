package com.ijys.java8samples.functionalinterfaces.real;

@FunctionalInterface
public interface InvalidFunctionalInterface {
	<T> String makeString(T value);
}
