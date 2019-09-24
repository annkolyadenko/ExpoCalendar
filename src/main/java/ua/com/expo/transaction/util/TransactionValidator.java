package ua.com.expo.transaction.util;

import ua.com.expo.transaction.TransactionExecutable;
import ua.com.expo.transaction.Transactional;


public class TransactionValidator {

    public boolean isTransactional(TransactionExecutable ex) {
        return ex.getClass().getEnclosingMethod().isAnnotationPresent(Transactional.class);
    }
}
