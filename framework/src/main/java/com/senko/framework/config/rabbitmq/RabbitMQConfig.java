package com.senko.framework.config.rabbitmq;

import com.senko.common.constants.RabbitMQConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置类
 * @author senko
 * @date 2022/7/14 12:06
 */
@Configuration
public class RabbitMQConfig {

    // =============邮箱=============
    /**
     * 邮箱队列
     */
    @Bean("emailQueue")
    public Queue emailQueue() {
        return new Queue(RabbitMQConstants.EMAIL_QUEUE, true);
    }

    /**
     * 邮箱 广播交换机
     */
    @Bean("emailExchange")
    public FanoutExchange emailExchange() {
        return new FanoutExchange(RabbitMQConstants.EMAIL_EXCHANGE, true, false);
    }

    /**
     * 将邮箱队列与交换机绑定
     */
    @Bean("bindingEmailDirect")
    public Binding bindingEmailDirect(@Qualifier("emailQueue") Queue emailQueue, @Qualifier("emailExchange") FanoutExchange emailExchange) {
        return BindingBuilder.bind(emailQueue)
                .to(emailExchange);
    }


}
