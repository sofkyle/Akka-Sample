package com.akkasample.deal;

import akka.actor.AbstractActor;
import akka.actor.Props;

/**
 * @Author: Kyle
 * @Date: 2018/6/14 15:30
 */
public class ComponentOneActor extends AbstractActor {
    //private static final Logger logger = LoggerFactory.getLogger(ComponentOneActor.class);

    static public Props props() {
        return Props.create(ComponentOneActor.class, () -> new ComponentOneActor());
    }

    static public class ComponentOneMsg {
        private final String msg;

        public ComponentOneMsg(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(ComponentOneMsg.class, componentOneMsg -> {
                doReceive(componentOneMsg.getMsg());
            })
            .build();
    }

    private void doReceive(String msg) {
        //logger.info("component one receive message: " + msg);
        System.out.println("component one receive message: " + msg);
    }
}