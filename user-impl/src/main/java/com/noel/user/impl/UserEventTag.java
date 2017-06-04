package com.noel.user.impl;

import com.lightbend.lagom.javadsl.persistence.AggregateEventTag;

/**
 * Created by Noel on 6/3/17.
 */
public class UserEventTag {
    public static final AggregateEventTag<UserEvent> INSTANCE = AggregateEventTag.of(UserEvent.class);
}
