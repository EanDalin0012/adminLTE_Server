package com.spring.adminlte.Transactions;

import org.springframework.transaction.SavepointManager;

public interface TransactionStatus extends SavepointManager {
    boolean isNewTransaction();

    boolean hasSavepoint();

    void setRollbackOnly();

    boolean isRollbackOnly();

    void flush();

    boolean isCompleted();
}
