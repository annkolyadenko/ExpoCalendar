package ua.com.expo.persistence.dao.interfaces;

import ua.com.expo.entity.Payment;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public interface IPaymentDao extends InterfaceDao<Long, Payment> {

    Long createPaymentWithGeneratedKey(Payment payment) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException;

}
