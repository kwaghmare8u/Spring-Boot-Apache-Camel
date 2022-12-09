package com.hellboy;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CamelSpringBootTest
@SpringBootTest
@UseAdviceWith
public class LegacyFileRouteTest {

    @Autowired
    CamelContext camelContext;

    @EndpointInject("mock:result")
    private MockEndpoint mockEndpoint;

    @Test
    public void testFileMove() throws Exception {

        String expectedBody = "This is the input file";
        mockEndpoint.expectedBodiesReceived(expectedBody);
        mockEndpoint.expectedMinimumMessageCount(1);

        AdviceWith.adviceWith(camelContext, "legacyFileMoveRouteId" , routeBuilder -> {
           routeBuilder.weaveByToUri("file:*").replace().to(mockEndpoint);
        });

        camelContext.start();
        mockEndpoint.assertIsSatisfied();
    }


}
