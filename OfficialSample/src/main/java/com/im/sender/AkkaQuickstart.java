package com.im.sender;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.im.sender.Greeter.*;

import java.io.IOException;

/**
 * @author Kyle
 * @create 2018/6/16 0:19
 */
public class AkkaQuickstart {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("helloakka");
        try {
            final ActorRef printerActor =
                    system.actorOf(Printer.props(), "printerActor");
            final ActorRef howdyGreeter =
                    system.actorOf(Greeter.props("Howdy", printerActor), "howdyGreeter");
            final ActorRef helloGreeter =
                    system.actorOf(Greeter.props("Hello", printerActor), "helloGreeter");
            final ActorRef goodDayGreeter =
                    system.actorOf(Greeter.props("Good day", printerActor), "goodDayGreeter");

            howdyGreeter.tell(new WhoToGreet("Akka"), howdyGreeter);
            howdyGreeter.tell(new Greet(), ActorRef.noSender());

            howdyGreeter.tell(new WhoToGreet("Lightbend"), howdyGreeter);
            howdyGreeter.tell(new Greet(), ActorRef.noSender());

            helloGreeter.tell(new WhoToGreet("Java"), helloGreeter);
            helloGreeter.tell(new Greet(), ActorRef.noSender());

            goodDayGreeter.tell(new WhoToGreet("Play"), goodDayGreeter);
            goodDayGreeter.tell(new Greet(), ActorRef.noSender());

            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (IOException ioe) {
        } finally {
            system.terminate();
        }
    }
}