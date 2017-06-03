package com.noel.user.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.noel.user.api.User;
import com.noel.user.api.UserService;

/**
 * Created by Noel on 6/3/17.
 */
public class UserServiceImpl implements UserService {

    @Override
    public ServiceCall<NotUsed, User> getUser(String userId) {
        return null;
    }

    @Override
    public ServiceCall<User, NotUsed> createUser() {
        return null;
    }
}
