samples
=======

java8
-----

> ### optional
> #### legacy 방식의 null check와 비교
> <pre>com.ijys.java8samples.optionals.LegacyNullCheck</pre>
> * legacy 방식의 null check
> <pre>com.ijys.java8samples.optionals.OptionalNullInterimCheck</pre>
> * Optional의 nullable 메소드를 사용하였지만, 코딩상의 indent는 별 차이가 없는...
> <pre>com.ijys.java8samples.optionals.OptionalNullCheck</pre>
> * Optional의 static map method로 형변환하면서 null-safe 하게, orElse 메소드로 default값 까지 처리.
    > filter static method로 객체의 조건으로 조회
> <pre>com.ijys.java8samples.optionals.TransOptionalFromLegacy</pre>
> * Map의 get method사용시 return 값의 null check를 Optional로 변경
    > List의 get method의 try-catch block과 null check를 Optional로 변경

> ### lambda
> <pre>com.ijys.java8sample.lambda.AdminUserCompareSample</pre>
> * legacy 방식의 comparator 구현을 lambda로 변경

> ### functional interface
> <pre>com.ijys.java8sample.functionalinterfaces.example.FunctionalInterfaceEx</pre>
> * functionalInterface example
> <pre>com.ijys.java8sample.functionalinterfaces.FunctionExample</pre>
> * Function example
> <pre>com.ijys.java8sample.functionalinterfaces.PredicateExample</pre>
> * Predicate example
> <pre>com.ijys.java8sample.functionalinterfaces.ConsumerExample</pre>
> * ConsumerExample
> <pre>com.ijys.java8sample.functionalinterfaces.SupplierExample</pre>
> * SupplierExample
> <pre>com.ijys.java8sample.functionalinterfaces.real.RealExample</pre>
> * real life에서 사용
> <pre>com.ijys.java8sample.functionalinterfaces.real2.ProductExample</pre>
> * product의 price를 filtering
> <pre>com.ijys.java8sample.functionalinterfaces.real2.DiscountedProductExample</pre>
> * product의 price를 discount 시키고, filtering with Generic

> ### stream
> <pre>com.ijys.java8sample.stream.StreamPrelude</pre>
> * Stream 시작전, function...
> <pre>com.ijys.java8sample.stream.identityfunction.Example</pre>
> * Function.apply & Function.identify
> <pre>com.ijys.java8sample.stream.StreamExample1</pre>
> * repl
> <pre>com.ijys.java8sample.stream.StreamExample2</pre>
> * imperative vs. functional
> * Stream 사용과 직접 filter, map 구현시 효율성 비교
> <pre>com.ijys.java8sample.stream.StreamExample3</pre>
> * intermediate operation method & terminal operation method
> * external iterate & internal iterate