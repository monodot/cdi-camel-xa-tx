package com.example.tx;

import com.arjuna.ats.jta.UserTransaction;

import javax.inject.Named;

/**
 * Created by tdonohue on 26/04/2016.
 */
@Named("userTransaction")
public class CdiUserTransaction extends UserTransaction {

    public CdiUserTransaction() {
        super();
    }
}
