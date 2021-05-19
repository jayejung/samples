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
> <pre>com.ijys.java8samples.lambda.AdminUserCompareSample</pre>
> * legacy 방식의 comparator 구현을 lambda로 변경

> ### functional interface
> <pre>com.ijys.java8samples.functionalinterfaces.example.FunctionalInterfaceEx</pre>
> * functionalInterface example
> <pre>com.ijys.java8samples.functionalinterfaces.FunctionExample</pre>
> * Function example
> <pre>com.ijys.java8samples.functionalinterfaces.PredicateExample</pre>
> * Predicate example
> <pre>com.ijys.java8samples.functionalinterfaces.ConsumerExample</pre>
> * ConsumerExample
> <pre>com.ijys.java8samples.functionalinterfaces.SupplierExample</pre>
> * SupplierExample
> <pre>com.ijys.java8samples.functionalinterfaces.real.RealExample</pre>
> * real life에서 사용
> <pre>com.ijys.java8samples.functionalinterfaces.real2.ProductExample</pre>
> * product의 price를 filtering
> <pre>com.ijys.java8samples.functionalinterfaces.real2.DiscountedProductExample</pre>
> * product의 price를 discount 시키고, filtering with Generic

> ### stream
> <pre>com.ijys.java8samples.stream.StreamPrelude</pre>
> * Stream 시작전, function...
> <pre>com.ijys.java8samples.stream.identityfunction.Example</pre>
> * Function.apply & Function.identify
> <pre>com.ijys.java8samples.stream.StreamExamples1</pre>
> * repl
> <pre>com.ijys.java8samples.stream.StreamExamples2</pre>
> * imperative vs. functional
> * Stream 사용과 직접 filter, map 구현시 효율성 비교
> <pre>com.ijys.java8samples.stream.StreamExamples3</pre>
> * intermediate operation method & terminal operation method
> * external iterate & internal iterate
> <pre>com.ijys.java8samples.stream.streamExamples4</pre>
> * more realistic example
> <pre>com.ijys.java8samples.stream.parallel.StreamExamples5Parallel</pre>
> * parallel sum의 side effect 확인 (race condition)
> * parallel 연산의 경우, 사용하는 core 갯수에 비례하여 시간이 줄어들고 있음
> * -Djava.util.concurrent.ForkJoinPool.common.parallelism=20 옵션은 openJDK에서 안먹는 듯?
> <pre>com.ijys.java8samples.stream.parallel.StreamExamples5ParallelPerformance</pre>
> * Gauss method, iterative method, stream method, parallel method의 성능 비교
> * 예제가 실용적이지 않아서 stream, parallel method가 성능이 가장 많이 떨어짐
> <pre>com.ijys.java8samples.stream.parallel.StreamExamples5ParallelPerformance2</pre>
> * 조금 더 실제 상황에 맞게... 고수준의 연산이 있어서 연산사이에 시간이 걸린다면 parallel이 훨씬 빠르게 동작
> <pre>com.ijys.java8samples.stream.parallel.StreamExamples5ParallelPerformancePractical</pre>
> * StreamExamples5ParallelPerformance2에 대한 좀 더 현실적인 example

> <span style='color:red'>**Parallel Stream사용 시 주의점**</span><br/>
> 더 느릴 수도 있고... 빠를 수도 있고.. 감으로 하지 말고, 내 경우에 맞는지 테스트(벤치마크)를 해라.<br/>
> eg. [JMH 성능 벤치마크 툴](http://openjdk.java.net/projects/code-tools/jmh/)