package com.noel.user.impl;

import akka.Done;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.noel.user.api.User;
import com.noel.user.impl.UserCommand.CreateUser;
import com.noel.user.impl.UserEvent.UserCreated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Noel on 6/3/17.
 */
public class UserEntity extends PersistentEntity<UserCommand, UserEvent, UserState> {

    @Override
    public Behavior initialBehavior(Optional<UserState> snapshotState) {

        BehaviorBuilder b = newBehaviorBuilder(snapshotState.orElse(
            new UserState(Optional.empty())));

        b.setCommandHandler(CreateUser.class, (cmd, ctx) -> {
            if (state().user.isPresent()) {
                ctx.invalidCommand("User " + entityId() + " is already created");
                return ctx.done();
            } else {
                User user = cmd.user;
                List<UserEvent> events = new ArrayList<>();
                events.add(new UserCreated(user.userId, user.name));
                return ctx.thenPersistAll(events, () -> ctx.reply(Done.getInstance()));
            }
        });

        b.setEventHandler(UserCreated.class,
            evt -> new UserState(Optional.of(new User(evt.userId, evt.name))));


        // TODO

        return b.build();

    }
}
