package ua.com.expo.persistence.dao.executor;

import ua.com.expo.persistence.connection.ConnectionWrapper;

public interface IDaoExecutor<T> {

    void execute(ConnectionWrapper cw, String something);


}
