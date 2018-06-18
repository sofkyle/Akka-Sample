package com.akkasample.deal;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

/**
 * @author Kyle
 * @create 2018/6/18 20:07
 */
public class CooperatorActor extends AbstractActor {
    static public Props props() {
        return Props.create(CooperatorActor.class, () -> new CooperatorActor());
    }

    /**
     * 转账消息
     */
    static public class TransferMsg {
        private final ActorRef fromAccount;
        private final ActorRef toAccount;
        private final Integer money;

        public TransferMsg(ActorRef fromAccount, ActorRef toAccount, Integer money) {
            this.fromAccount = fromAccount;
            this.toAccount = toAccount;
            this.money = money;
        }

        public ActorRef getFromAccount() {
            return fromAccount;
        }

        public ActorRef getToAccount() {
            return toAccount;
        }

        public Integer getMoney() {
            return money;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(TransferMsg.class, transferMsg -> {
                    // 发送扣款消息
                    transferMsg.getFromAccount().tell(new AccountActor.DeductMsg(transferMsg.getMoney()), getSelf());
                    // 发送入账消息
                    transferMsg.getToAccount().tell(new AccountActor.AddMsg(transferMsg.getMoney()), getSelf());
                })
                .build();
    }
}
