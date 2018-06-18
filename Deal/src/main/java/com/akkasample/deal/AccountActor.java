package com.akkasample.deal;

import akka.actor.AbstractActor;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: Kyle
 * @Date: 2018/6/14 15:30
 */
public class AccountActor extends AbstractActor {
    private static final Logger logger = LoggerFactory.getLogger(AccountActor.class);

    static public Props props(Integer amount) {
        return Props.create(AccountActor.class, () -> new AccountActor(amount));
    }

    /**
     * 扣款消息
     */
    static public class DeductMsg {
        private final Integer money;

        public DeductMsg(Integer money) {
            this.money = money;
        }

        public Integer getMoney() {
            return money;
        }
    }

    /**
     * 入账消息
     */
    static public class AddMsg {
        private final Integer money;

        public AddMsg(Integer money) {
            this.money = money;
        }

        public Integer getMoney() {
            return money;
        }
    }

    /** 账户金额 **/
    private Integer amount;

    public AccountActor(Integer amount) {
        this.amount = amount;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(DeductMsg.class, deductMsg -> {
                amount -= deductMsg.getMoney();
                logger.info("current money: " + amount);
            })
            .match(AddMsg.class, addMsg -> {
                amount += addMsg.getMoney();
                logger.info("current money: " + amount);
            })
            .build();
    }
}