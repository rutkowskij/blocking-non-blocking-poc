/*
 * Copyright (C) 2016-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package io.thingcare.hello.api;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;


public interface ClientService extends Service {


    ServiceCall<NotUsed, String> hello();

    @Override
    default Descriptor descriptor() {
        return named("hello").withCalls(
                pathCall("/client", this::hello)
        ).withAutoAcl(true);
    }
}
