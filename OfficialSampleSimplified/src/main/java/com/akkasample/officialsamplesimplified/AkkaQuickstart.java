package com.akkasample.officialsamplesimplified;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.akkasample.officialsamplesimplified.Greeter.*;

import java.io.IOException;

/**
 * @author Kyle
 * @create 2018/6/16 0:19
 */
public class AkkaQuickstart {
    public static final Integer threadCount = 100;

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create();
        try {
            final ActorRef printerActor = system.actorOf(Printer.props());

            for(int i = 1; i <= threadCount; i++) {
                final int sign = i;
                new Thread(() -> {
                    /**
                     * 按照我最初的理解，
                     * Actor的消息处理顺序是与实例初始化的顺序一致的，从而保证了并发的顺序性，
                     * 从实践来看我的理解是错的。
                     */
                    final ActorRef greeter = system.actorOf(Greeter.props(printerActor));
                    greeter.tell(new Greeter.Greet("Hello" + sign), greeter);
                }).start();
            }

            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (IOException ioe) {
        } finally {
            system.terminate();
        }
    }
}
