package ua.com.expo.service.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.exception_draft.RuntimeServiceException;
import ua.com.expo.exception_draft.RuntimeSqlException;
import ua.com.expo.transaction.TransactionUtil;
import ua.com.expo.transaction.TransactionValidator;

public class TransactionExecutor<T> {

    private static final TransactionUtil transactionUtil = TransactionUtil.getInstance();
    private static final TransactionValidator transactionValidator = new TransactionValidator();
    private static final Logger LOGGER = LogManager.getLogger(TransactionExecutor.class.getName());

    public void perform(TransactionExecutable ex) {
        if (transactionValidator.isTransactional(ex)) {
            performTransaction(ex);
        } else {
            throw new RuntimeException("Something went wrong with transaction");
        }
    }

    private void performTransaction(TransactionExecutable ex) {
        try {
            transactionUtil.startTransaction();
            ex.execute();
            transactionUtil.commit();
        } catch (RuntimeSqlException e) {
            LOGGER.error(e);
            try {
                transactionUtil.rollback();
            } catch (RuntimeSqlException exe) {
                LOGGER.error(exe);
                throw new RuntimeServiceException(exe);
            } finally {
                try {
                    transactionUtil.endTransaction();
                } catch (RuntimeSqlException exp) {
                    LOGGER.error(exp);
                }
            }
        }
    }
}
