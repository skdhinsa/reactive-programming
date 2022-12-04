package com.serenakd.springbootwebfluxdemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class monoFluxTest {

    @Test
    public void monoTest(){
        //Mono<String> monoString = Mono.just("mono test"); //publisher, we need to subscribe to the event
        Mono<String> monoString = Mono.just("mono test").log();
        monoString.subscribe(System.out::println); // calling subscriber() means publisher can start emitting the events
    }

    @Test
    public void monoOnErrorTest(){
        Mono<?> monoString = Mono.just("mono test")
                .then(Mono.error(new RuntimeException("EXCEPTION THROWN!!")))
                .log();
        monoString.subscribe(System.out::println, throwable -> System.out.println(throwable)); // subscribe() overloaded, handle exception
    }

    @Test
    public void fluxTest(){
        Flux<Integer> fluxData = Flux.just(1,2,3,4)
                .concatWithValues(5,6)
                .log();

        fluxData.subscribe(System.out::println);
    }

    @Test
    public void fluxOnErrorTest(){
        Flux<Integer> fluxData = Flux.just(1,2,3,4,5)
                .concatWith(Flux.error(new RuntimeException("EXCEPTION THROWN!!")))
                .concatWithValues(7)
                .log();
        fluxData.subscribe(System.out::println, throwable -> System.out.println(throwable));
    }
}
