package com.hellboy.components;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class SimpleTimer extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:simpletimer?period=2000")
                .routeId("simpleTimerID")
                .setBody(constant("Testing"))
                .log(LoggingLevel.INFO,"${body}");
    }
}
