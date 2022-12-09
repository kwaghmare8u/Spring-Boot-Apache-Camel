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

@SpringBootTest
@CamelSpringBootTest
@UseAdviceWith
class ApacheCamelSpringbootApplicationTests {

    @Autowired
    CamelContext camelContext;

    @EndpointInject("mock:result")
    protected MockEndpoint mockEndpoint;

    @Test
    public void testSimpleTimer() throws Exception {
        String expectedBody = "Testing";
        mockEndpoint.expectedBodiesReceived(expectedBody);
        mockEndpoint.expectedMinimumMessageCount(1);
        AdviceWith.adviceWith(camelContext, "simpleTimerID", routeBuilder -> {
            routeBuilder.weaveAddLast().to(mockEndpoint);
        });
        camelContext.start();
        mockEndpoint.assertIsSatisfied();
    }

}
