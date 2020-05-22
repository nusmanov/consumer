package com.cdt.consumer;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsumerApplication {

  @Bean
  public Binding binding() {
    return BindingBuilder
        .bind(userPresenceQueue())
        .to(userPresenceExchange())
        .with("#");
  }

  private DirectExchange userPresenceExchange() {
    return new DirectExchange("user-presence");
  }

  @Bean
  Queue userPresenceQueue() {
    return new Queue("user-presence");
  }

  @Bean
  public MessageConverter jackson2JsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  public static void main(String[] args) {
    SpringApplication.run(ConsumerApplication.class, args);
  }

}