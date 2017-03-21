/*
 * Copyright (C) 2016-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package io.thingcare.hello.impl;

import akka.Done;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;

import java.util.Optional;

import javax.inject.Inject;
import io.thingcare.hello.api.GreetingMessage;
import io.thingcare.hello.api.HelloService;
import io.thingcare.hello.impl.HelloCommand.*;
import io.thingcare.hello.impl.routing.RoutingClient;

/**
 * Implementation of the HelloService.
 */
public class HelloServiceImpl implements HelloService {

  private final PersistentEntityRegistry persistentEntityRegistry;
  private final RoutingClient routingClient;

  @Inject
  public HelloServiceImpl(PersistentEntityRegistry persistentEntityRegistry, RoutingClient routingClient) {
    this.persistentEntityRegistry = persistentEntityRegistry;
    this.routingClient = routingClient;
    persistentEntityRegistry.register(HelloEntity.class);
  }

  @Override
  public ServiceCall<NotUsed, String> hello(String id) {
    return request -> {
     return routingClient.routing().invoke();
    };
  }

  @Override
  public ServiceCall<GreetingMessage, Done> useGreeting(String id) {
    return request -> {
      // Look up the hello world entity for the given ID.
      PersistentEntityRef<HelloCommand> ref = persistentEntityRegistry.refFor(HelloEntity.class, id);
      // Tell the entity to use the greeting message specified.
      return ref.ask(new UseGreetingMessage(request.message));
    };

  }

}
