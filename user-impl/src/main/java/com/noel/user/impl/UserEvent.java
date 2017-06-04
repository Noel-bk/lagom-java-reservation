package com.noel.user.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.javadsl.persistence.AggregateEvent;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTagger;
import com.lightbend.lagom.serialization.Jsonable;

import javax.annotation.concurrent.Immutable;
import java.time.Instant;
import java.util.Optional;

/**
 * Created by Noel on 6/3/17.
 */
public interface UserEvent extends Jsonable, AggregateEvent<UserEvent> {

    @Override
    default AggregateEventTagger<UserEvent> aggregateTag() {
        return UserEventTag.INSTANCE;
    }

    @Immutable
    @JsonDeserialize
    class UserCreated implements UserEvent {
        public final String userId;
        public final String name;
        public final Instant timestamp;

        public UserCreated(String userId, String name) {
            this(userId, name, Optional.empty());
        }

        @JsonCreator
        private UserCreated(String userId, String name, Optional<Instant> timestamp) {
            this.userId = Preconditions.checkNotNull(userId, "userId");
            this.name = Preconditions.checkNotNull(name, "name");
            this.timestamp = timestamp.orElseGet(() -> Instant.now());
        }
    }
}
