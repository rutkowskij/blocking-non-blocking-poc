package io.thingcare.hello.impl.routing;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.deser.PathParamSerializers;
import com.lightbend.lagom.javadsl.api.transport.Method;

import java.util.UUID;

import static com.lightbend.lagom.javadsl.api.Service.named;

public interface RoutingClient extends Service {

    ServiceCall<NotUsed, String> routing();

    @Override
    default Descriptor descriptor() {
        return named("routing")
                .withCalls(
                        Service.restCall(Method.GET, "/routing", this::routing)
                ).withPathParamSerializer(
                        UUID.class, PathParamSerializers.required("UUID", UUID::fromString, UUID::toString)
                )
                .withAutoAcl(true);
    }
}