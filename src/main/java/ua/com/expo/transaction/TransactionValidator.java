package ua.com.expo.transaction;

import ua.com.expo.service.utils.TransactionExecutable;

public class TransactionValidator {

    public boolean isTransactional(TransactionExecutable ex) {
        return ex.getClass().getEnclosingMethod().isAnnotationPresent(Transactional.class);
    }
}
