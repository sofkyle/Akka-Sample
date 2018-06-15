package com.im.client;

import akka.actor.AbstractActor;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: Finance Group
 * @Date: 2018/6/14 15:38
 */
public class Printer extends AbstractActor {
    static public Props props() {
        return Props.create(Printer.class, () -> new Printer());
    }

    static public class Greeting {
        public final String message;

        public Greeting(String message) {
            this.message = message;
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(Printer.class);

    public Printer() {
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Greeting.class, greeting -> {
            logger.info(greeting.message);
        }).build();
    }
}
