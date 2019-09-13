package ua.com.expo.utils.transaction;

import ua.com.expo.services.IUserService;
import ua.com.expo.transaction_draft.Transactional;

public class TransactionValidator {

    public boolean isTransactional(IUserService service) {
        return service.getClass().getEnclosingMethod().isAnnotationPresent(Transactional.class);
    }
}
