package per.zyf.client;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

/**
 * @Author: Finance Group
 * @Date: 2018/6/14 15:30
 */
public class Greeter extends AbstractActor {
    static public Props props(String message, ActorRef printerActor) {
        return Props.create(Greeter.class, () -> new Greeter(message, printerActor));
    }

    static public class WhoToGreet {
        public final String who;

        public WhoToGreet(String who) {
            this.who = who;
        }
    }

    static public class Greet {
        public Greet() {
        }
    }

    private final String message;
    private final ActorRef printerActor;
    private String greeting = "";

    public Greeter(String message, ActorRef printerActor) {
        this.message = message;
        this.printerActor = printerActor;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(WhoToGreet.class, wtg -> {
                this.greeting = message + ", " + wtg.who;
            })
            .match(Greet.class, x -> {
                printerActor.tell(new Printer.Greeting(greeting), getSelf());
            })
            .build();
    }
}