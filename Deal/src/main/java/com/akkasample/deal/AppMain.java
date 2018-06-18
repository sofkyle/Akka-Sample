package com.akkasample.deal;

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
            final ActorRef guest1 = system.actorOf(AccountActor.props(100), "guest1");
            final ActorRef guest2 = system.actorOf(AccountActor.props(100), "guest2");

            final ActorRef cooperator = system.actorOf(CooperatorActor.props(), "cooperator");
            cooperator.tell(new CooperatorActor.TransferMsg(guest1, guest2, 100), ActorRef.noSender());

            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (Exception e) {}
        finally {
            system.terminate();
        }
    }
}
