package com.example.tx;

import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by tdonohue on 26/04/2016.
 */
@Named("PROPAGATION_REQUIRED")
public class CdiRequiredPolicy extends SpringTransactionPolicy {

    @Inject
    public CdiRequiredPolicy(CdiJtaTransactionManager cdiJtaTransactionManager) {
        super(new TransactionTemplate(cdiJtaTransactionManager,
                new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED)));
    }

}
