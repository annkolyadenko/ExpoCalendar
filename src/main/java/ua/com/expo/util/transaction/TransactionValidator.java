package ua.com.expo.util.transaction;

import ua.com.expo.service.IUserService;
import ua.com.expo.transaction_draft.Transactional;

public class TransactionValidator {

    public boolean isTransactional(IUserService service) {
        return service.getClass().getEnclosingMethod().isAnnotationPresent(Transactional.class);
    }
}
