package com.hellboy.components;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class LegacyFileRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("file:src/data/input?filename=inputfile.txt")
                .routeId("legacyFileMoveRouteId")
                .to("file:src/data/output?filename=outputfile.txt");
    }
}
