package com.noel.user.impl;

import akka.Done;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.lightbend.lagom.serialization.Jsonable;
import com.noel.user.api.User;

import javax.annotation.concurrent.Immutable;

/**
 * Created by Noel on 6/3/17.
 */
public interface UserCommand extends Jsonable {

    @Immutable
    @JsonDeserialize
    final class CreateUser implements UserCommand, PersistentEntity.ReplyType<Done> {
        public final User user;

        @JsonCreator
        public CreateUser(User user) {
            this.user = Preconditions.checkNotNull(user, "user");
        }
    }
}
