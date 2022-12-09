package com.hellboy.components;

import com.hellboy.model.NameAddress;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LegacyFileRoute extends RouteBuilder {

    Logger logger = LoggerFactory.getLogger(getClass());
    BeanIODataFormat inboundDataFormat = new BeanIODataFormat("InboundMessageBeanIOMapping.xml","inputMessageStream");

    @Override
    public void configure() throws Exception {

        from("file:src/data/input?filename=input.csv")
                .routeId("legacyFileMoveRouteId")
                .unmarshal(inboundDataFormat)
                .split(body().tokenize("\n"))
                .process(exchange -> {
                    NameAddress fileData = exchange.getIn().getBody(NameAddress.class);
                    logger.info("Input file data --> {}",fileData.toString());
                });
//                .to("file:src/data/output?filename=outputfile.txt&fileExist=append&appendChars=\\n");
    }
}
