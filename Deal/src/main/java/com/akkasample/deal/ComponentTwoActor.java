package com.akkasample.deal;

import akka.actor.AbstractActor;
import akka.actor.Props;

/**
 * @Author: Kyle
 * @Date: 2018/6/14 15:30
 */
public class ComponentTwoActor extends AbstractActor {
    //private static final Logger logger = LoggerFactory.getLogger(ComponentTwoActor.class);

    static public Props props() {
        return Props.create(ComponentTwoActor.class, () -> new ComponentTwoActor());
    }

    static public class ComponentTwoMsg {
        private final String msg;

        public ComponentTwoMsg(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(ComponentTwoMsg.class, componentTwoMsg -> {
                doReceive(componentTwoMsg.getMsg());
            })
            .build();
    }

    private void doReceive(String msg) {
        //logger.info("component two receive message: " + msg);
        System.out.println("component two receive message: " + msg);
    }
}