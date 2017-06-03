package com.noel.user.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;

import javax.annotation.concurrent.Immutable;

/**
 * Created by Noel on 6/3/17.
 */
@Immutable
@JsonDeserialize
public final class User {

    public final String userId;
    public final String name;

    @JsonCreator
    public User(String userId, String name) {
        this.userId = Preconditions.checkNotNull(userId, "userId");
        this.name = Preconditions.checkNotNull(name, "name");
    }
}
