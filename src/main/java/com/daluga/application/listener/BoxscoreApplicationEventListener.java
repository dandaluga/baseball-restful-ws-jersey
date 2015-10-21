package com.daluga.application.listener;

import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoxscoreApplicationEventListener implements ApplicationEventListener {

    private volatile int requestCount = 0;

    private static final Logger logger = LoggerFactory.getLogger(BoxscoreApplicationEventListener.class);

    @Override
    public void onEvent(ApplicationEvent applicationEvent) {
        switch (applicationEvent.getType()) {
            case INITIALIZATION_FINISHED:
                logger.debug("Application " + applicationEvent.getResourceConfig().getApplicationName() + " was initialized.");
                break;
            case DESTROY_FINISHED:
                logger.debug("Application " + applicationEvent.getResourceConfig().getApplicationName() + " destroyed.");
                break;
        }
    }

    @Override
    public RequestEventListener onRequest(RequestEvent requestEvent) {

        requestCount++;

        logger.debug("Request number [" + requestCount + "] started.");

        // return the listener instance that will handle this request.
        return new BoxscoreRequestEventListener(requestCount);
    }
}
