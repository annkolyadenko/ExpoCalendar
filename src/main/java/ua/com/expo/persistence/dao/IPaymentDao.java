package ua.com.expo.persistence.dao;

import ua.com.expo.entity.Payment;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Optional;

public interface IPaymentDao {

    Long savePaymentWithGeneratedKey(Payment payment);

    Optional<Payment> findPaymentById(Long id);

    boolean save(Payment payment);

}
