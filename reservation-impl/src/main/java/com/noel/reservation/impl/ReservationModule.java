/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.noel.reservation.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.noel.reservation.api.ReservationService;

/**
 * The module that binds the ReservationService so that it can be served.
 */
public class ReservationModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindService(ReservationService.class, ReservationServiceImpl.class);
    }
}
