/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.noel.reservation.api;

import akka.Done;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.broker.Topic;
import com.lightbend.lagom.javadsl.api.broker.kafka.KafkaProperties;

import static com.lightbend.lagom.javadsl.api.Service.*;

/**
 * The reservation service interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the ReservationService.
 */
public interface ReservationService extends Service {

    /**
     * Example: curl http://localhost:9000/api/hello/Alice
     */
    ServiceCall<NotUsed, String> hello(String id);

    /**
     * Example: curl -H "Content-Type: application/json" -X POST -d '{"message":
     * "Hi"}' http://localhost:9000/api/hello/Alice
     */
    ServiceCall<GreetingMessage, Done> useGreeting(String id);

    // TODO
    // fix return type
    ServiceCall<NotUsed, String> getReservation();

    /**
     * This gets published to Kafka.
     */
    Topic<ReservationEvent> helloEvents();

    @Override
    default Descriptor descriptor() {
        // @formatter:off
        return named("reservation").withCalls(
            pathCall("/api/hello/:id", this::hello),
            pathCall("/api/hello/:id", this::useGreeting),
            pathCall("/api/reservation/:id", this::getReservation)
        ).publishing(
            topic("hello-events", this::helloEvents)
                // Kafka partitions messages, messages within the same partition will
                // be delivered in order, to ensure that all messages for the same user
                // go to the same partition (and hence are delivered in order with respect
                // to that user), we configure a partition key strategy that extracts the
                // name as the partition key.
                .withProperty(KafkaProperties.partitionKeyStrategy(), ReservationEvent::getName)
        ).withAutoAcl(true);
        // @formatter:on
    }
}
