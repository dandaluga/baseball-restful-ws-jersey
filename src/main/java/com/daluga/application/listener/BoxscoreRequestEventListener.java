package com.daluga.application.listener;

import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoxscoreRequestEventListener implements RequestEventListener {

    private final int requestNumber;
    private final long startTime;

    private static final Logger logger = LoggerFactory.getLogger(BoxscoreApplicationEventListener.class);

    public BoxscoreRequestEventListener(int requestNumber) {

        this.requestNumber = requestNumber;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void onEvent(RequestEvent requestEvent) {

        switch (requestEvent.getType()) {
            case RESOURCE_METHOD_START:
                logger.debug("Resource method " + requestEvent.getUriInfo().getMatchedResourceMethod() .getHttpMethod() + " started for request " + requestNumber);
                break;
            case FINISHED:
                logger.debug("Request " + requestNumber + " finished. Processing time " + (System.currentTimeMillis() - startTime) + " ms.");
                break;
        }
    }
}
