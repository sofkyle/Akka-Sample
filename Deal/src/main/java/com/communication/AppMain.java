package com.communication;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * @author Kyle
 * @create 2018/6/18 15:16
 */
public class AppMain {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create();
        try {
            final ActorRef componentOneActor = system.actorOf(ComponentOneActor.props(), "componentOneActor");
            final ActorRef componentTwoActor = system.actorOf(ComponentTwoActor.props(), "componentTwoActor");

            componentOneActor.tell(new ComponentTwoActor.ComponentTwoMsg("first msg"), ActorRef.noSender());
            componentTwoActor.tell(new ComponentOneActor.ComponentOneMsg("second msg"), ActorRef.noSender());

            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (Exception e) {}
        finally {
            system.terminate();
        }
    }
}
