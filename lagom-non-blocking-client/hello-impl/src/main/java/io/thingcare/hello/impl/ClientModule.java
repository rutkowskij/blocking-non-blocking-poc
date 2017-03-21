/*
 * Copyright (C) 2016-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package io.thingcare.hello.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import io.thingcare.hello.api.ClientService;
import io.thingcare.hello.impl.routing.RoutingClient;

/**
 * The module that binds the ClientService so that it can be served.
 */
public class ClientModule extends AbstractModule implements ServiceGuiceSupport {
  @Override
  protected void configure() {
    bindServices(serviceBinding(ClientService.class, ClientServiceImpl.class));

    bindClient(RoutingClient.class);
  }
}
