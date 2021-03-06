/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.noel.reservation.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.base.Preconditions;
import lombok.Value;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ReservationEvent.GreetingMessageChanged.class, name = "greeting-message-changed")
})
public interface ReservationEvent {

    String getName();

    @Value
    final class GreetingMessageChanged implements ReservationEvent {
        public final String name;
        public final String message;

        @JsonCreator
        public GreetingMessageChanged(String name, String message) {
            this.name = Preconditions.checkNotNull(name, "name");
            this.message = Preconditions.checkNotNull(message, "message");
        }
    }
}
