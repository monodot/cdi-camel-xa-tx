package com.example.tx;

import com.arjuna.ats.jta.TransactionManager;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * Created by tdonohue on 26/04/2016.
 */
@Named("transactionManager")
public class CdiTransactionManager extends TransactionManager {

    public CdiTransactionManager() {
        super();
    }
}
