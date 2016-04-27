package com.example;

import org.apache.activemq.ActiveMQXAConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.deltaspike.core.api.config.ConfigProperty;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * Created by tdonohue on 25/04/2016.
 */
public class JmsComponentFactoryBean {

    @Produces
    @Named("jms1")
    @ApplicationScoped
    JmsComponent jmsSourceComponent(@ConfigProperty(name = "jms1.url") String url,
                                    @ConfigProperty(name = "jms1.username") String username,
                                    @ConfigProperty(name = "jms1.password") String password,
                                    JtaTransactionManager cdiJtaTransactionManager) throws Exception {
        JmsComponent component = new JmsComponent();

        ActiveMQXAConnectionFactory xaFactory = new ActiveMQXAConnectionFactory(url);
        xaFactory.setUserName(username);
        xaFactory.setPassword(password);
        component.setTransactionManager(cdiJtaTransactionManager);
        component.setTransacted(true);
        component.setConnectionFactory(xaFactory);
        return component;
    }

    @Produces
    @Named("jms2")
    @ApplicationScoped
    JmsComponent jmsTargetComponent(@ConfigProperty(name = "jms2.url") String url,
                                    @ConfigProperty(name = "jms2.username") String username,
                                    @ConfigProperty(name = "jms2.password") String password,
                                    JtaTransactionManager cdiJtaTransactionManager) throws Exception {
        JmsComponent component = new JmsComponent();
        ActiveMQXAConnectionFactory factory = new ActiveMQXAConnectionFactory(url);
        factory.setUserName(username);
        factory.setPassword(password);
        component.setTransactionManager(cdiJtaTransactionManager);
        component.setTransacted(true);
        component.setConnectionFactory(factory);
        return component;
    }



}
