package com.noel.user.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;
import com.lightbend.lagom.javadsl.persistence.ReadSide;
import com.lightbend.lagom.javadsl.persistence.cassandra.CassandraSession;
import com.noel.user.api.User;
import com.noel.user.api.UserService;
import com.noel.user.impl.UserCommand.CreateUser;

import javax.inject.Inject;

/**
 * Created by Noel on 6/3/17.
 */
public class UserServiceImpl implements UserService {

    private final PersistentEntityRegistry persistentEntities;
    private final CassandraSession db;

    @Inject
    public UserServiceImpl(PersistentEntityRegistry persistentEntities, ReadSide readSide, CassandraSession db) {
        this.persistentEntities = persistentEntities;
        this.db = db;

        persistentEntities.register(UserEntity.class);
        // TODO
//        readSide.register(UserEventProcessor.class);
    }

    @Override
    public ServiceCall<NotUsed, User> getUser(String userId) {
        return null;
    }

    @Override
    public ServiceCall<User, NotUsed> createUser() {
        return request ->
            userEntityRef(request.userId)
                .ask(new CreateUser(request))
                .thenApply(ack -> NotUsed.getInstance());
    }

    private PersistentEntityRef<UserCommand> userEntityRef(String userId) {
        PersistentEntityRef<UserCommand> ref = persistentEntities.refFor(UserEntity.class, userId);
        return ref;
    }
}
