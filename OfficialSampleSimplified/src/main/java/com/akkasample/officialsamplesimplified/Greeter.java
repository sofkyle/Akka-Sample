package com.akkasample.officialsamplesimplified;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: Kyle
 * @Date: 2018/6/14 15:30
 */
public class Greeter extends AbstractActor {

    private static final Logger logger = LoggerFactory.getLogger(Greeter.class);

    static public Props props(ActorRef printerActor) {
        return Props.create(Greeter.class, () -> new Greeter(printerActor));
    }

    public Greeter(ActorRef printerActor) {
        this.printerActor = printerActor;
    }

    private final ActorRef printerActor;

    static public class Greet {
        private String greeting;

        public Greet(String greeting) {
            this.greeting = greeting;
        }

        public String getGreeting() {
            return greeting;
        }

        public void setGreeting(String greeting) {
            this.greeting = greeting;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(Greet.class, greet -> {
                    printerActor.tell(new Printer.Greeting(greet.getGreeting()), getSelf());
                })
            .build();
    }
}