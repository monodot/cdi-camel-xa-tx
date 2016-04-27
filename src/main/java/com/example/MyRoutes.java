package com.example;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.arjuna.ats.jta.TransactionManager;
import com.arjuna.ats.jta.UserTransaction;
import org.apache.activemq.ActiveMQXAConnectionFactory;
import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.cdi.Uri;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.deltaspike.core.api.config.ConfigProperty;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * Configures all our Camel routes, components, endpoints and beans
 */
@ContextName("myCamel")
public class MyRoutes extends RouteBuilder {

    @Inject
    @Uri("jms1:queue:TEST.IN")
    private Endpoint inputEndpoint;

    @Inject
    @Uri("jms2:queue:TEST.OUT")
    private Endpoint resultEndpoint;

    @Inject
    @Uri("timer://foo?repeatCount=25&delay=1000&period=100")
    private Endpoint timerEndpoint;

    @Override
    public void configure() throws Exception {

        // Fire messages into the source queue
        from(timerEndpoint)
                .id("timer-route")
                .transform(simple("Hello, world!"))
                .to(inputEndpoint);

        // Consume messages from the queue in a transacted route
        from(inputEndpoint)
                .id("cdi-camel-xa-tx-route")
                .transacted("PROPAGATION_REQUIRED")
                .log("Received message - ${body}")
                .to(resultEndpoint)
                .to("processorBean")
                .log("Published message - ${body}");

    }

}
