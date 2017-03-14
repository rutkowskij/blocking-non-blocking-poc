/*
 * Copyright (C) 2016-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package io.thingcare.hello.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.ServiceLocator;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;
import io.thingcare.hello.api.HelloService;
import io.thingcare.hello.impl.HelloCommand.Hello;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the HelloService.
 */
public class HelloServiceImpl implements HelloService {
    private final PersistentEntityRegistry persistentEntityRegistry;
    private final ServiceLocator serviceLocator;

    @Inject
    public HelloServiceImpl(PersistentEntityRegistry persistentEntityRegistry, ServiceLocator serviceLocator) {
        this.persistentEntityRegistry = persistentEntityRegistry;
        this.serviceLocator = serviceLocator;
        persistentEntityRegistry.register(HelloEntity.class);
    }

    @Override
    public ServiceCall<NotUsed, String> client() {
        return request -> {
//            serviceLocator.doWithService("routing").the
            String id = UUID.randomUUID().toString();
            // Look up the hello world entity for the given ID.
            PersistentEntityRef<HelloCommand> ref = persistentEntityRegistry.refFor(HelloEntity.class, id);
            // Ask the entity the Hello command.
            return ref.ask(new Hello(id, Optional.empty()));
        };
    }
}
