package com.example.tx;

import com.arjuna.ats.jta.TransactionManager;
import com.arjuna.ats.jta.UserTransaction;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by tdonohue on 26/04/2016.
 */
@Named("jtaTransactionManager")
public class CdiJtaTransactionManager extends JtaTransactionManager {

    @Inject
    private TransactionManager transactionManager;

    @Inject
    private UserTransaction userTransaction;


    public CdiJtaTransactionManager() {
        super();
    }

    @PostConstruct
    public void setManager() {
        setTransactionManager(transactionManager.transactionManager());
        setUserTransaction(userTransaction.userTransaction());
    }

}
