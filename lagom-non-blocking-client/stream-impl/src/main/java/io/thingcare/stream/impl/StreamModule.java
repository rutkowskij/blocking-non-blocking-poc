/*
 * Copyright (C) 2016-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package io.thingcare.stream.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import io.thingcare.hello.api.ClientService;
import io.thingcare.stream.api.StreamService;

/**
 * The module that binds the StreamService so that it can be served.
 */
public class StreamModule extends AbstractModule implements ServiceGuiceSupport {
  @Override
  protected void configure() {
    // Bind the StreamService service
    bindServices(serviceBinding(StreamService.class, StreamServiceImpl.class));
    // Bind the ClientService client
    bindClient(ClientService.class);
  }
}
