package com.noel.user.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.serialization.Jsonable;
import com.noel.user.api.User;

import javax.annotation.concurrent.Immutable;
import java.util.Optional;

/**
 * Created by Noel on 6/3/17.
 */
@Immutable
@JsonDeserialize
public final class UserState implements Jsonable {

    public final Optional<User> user;

    @JsonCreator
    public UserState(Optional<User> user) {
        this.user = Preconditions.checkNotNull(user, "user");
    }
}
