package ua.com.expo.model.dao.executor;

import ua.com.expo.model.connection.ConnectionWrapper;

import java.io.Serializable;

public interface IDaoExecutor<T> {

    void execute(ConnectionWrapper cw, String something);


}
