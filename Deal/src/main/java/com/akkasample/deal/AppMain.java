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
            final ActorRef accountA = system.actorOf(AccountActor.props(100), "accountA");
            final ActorRef accountB = system.actorOf(AccountActor.props(100), "accountB");

            final ActorRef cooperator = system.actorOf(CooperatorActor.props(), "cooperator");
            cooperator.tell(new CooperatorActor.TransferMsg(accountA, accountB, 100), ActorRef.noSender());

            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (Exception e) {}
        finally {
            system.terminate();
        }
    }
}
