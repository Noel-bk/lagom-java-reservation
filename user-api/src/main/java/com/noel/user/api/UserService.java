package com.noel.user.api;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.Service.*;

/**
 * Created by Noel on 6/3/17.
 */
public interface UserService extends Service {

    ServiceCall<NotUsed, User> getUser(String userId);

    ServiceCall<User, NotUsed> createUser();

    @Override
    default Descriptor descriptor() {
        return named("user").withCalls(
            pathCall("/api/user/:userId", this::getUser),
            namedCall("/api/users", this::createUser)
        ).withAutoAcl(true);
    }

}
