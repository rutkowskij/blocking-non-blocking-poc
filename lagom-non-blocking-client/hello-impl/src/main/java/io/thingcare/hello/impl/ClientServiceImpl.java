/*
 * Copyright (C) 2016-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package io.thingcare.hello.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import io.thingcare.hello.api.ClientService;
import io.thingcare.hello.impl.routing.RoutingClient;

import javax.inject.Inject;

public class ClientServiceImpl implements ClientService {

    private final RoutingClient routingClient;

    @Inject
    public ClientServiceImpl(RoutingClient routingClient) {
        this.routingClient = routingClient;
    }

    @Override
    public ServiceCall<NotUsed, String> hello() {
        return request -> routingClient.routing().invoke();
    }
}
