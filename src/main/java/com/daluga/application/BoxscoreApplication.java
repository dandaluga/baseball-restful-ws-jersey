package com.daluga.application;

import com.daluga.application.listener.BoxscoreApplicationEventListener;

import javax.ws.rs.core.Application;
import java.util.*;

// Note that this is not needed. I simply added it as a way to show how to add some additional
// logging for each request and response. Note that the web.xml will need to have an init param
// that will bootstrap this class as part of the Jersey implementation.
//
// Entry in web.xml:
//
// <init-param>
//    <param-name>javax.ws.rs.Application</param-name>
//    <param-value>com.daluga.application.BoxscoreApplication</param-value>
// </init-param>

public class BoxscoreApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        return new HashSet<Class<?>>() {{
            // Add your resources.
            //add(BoxscoreResource.class);

            // Add LoggingFilter.
            // This will add additional logging for each request and it's corresponding response.
            //add(LoggingFilter.class);

            // Add Event Listeners
            add(BoxscoreApplicationEventListener.class);
            System.out.println("Hello World");
        }};
    }

}
