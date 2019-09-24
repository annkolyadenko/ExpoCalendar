package ua.com.expo.util.security.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.util.security.IPasswordHashing;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class PasswordHashingImpl implements IPasswordHashing {

    private static final Logger LOGGER = LogManager.getLogger(PasswordHashingImpl.class.getName());

    private PasswordHashingImpl() {
    }

    private static class Holder {
        static final PasswordHashingImpl INSTANCE = new PasswordHashingImpl();
    }

    public static PasswordHashingImpl getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public byte[] saltGenerator() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[BYTE_ARRAY_SIZE];
        random.nextBytes(salt);
        return salt;
    }

    @Override
    public byte[] hashGenerator(String password, byte[] salt) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = null;
        byte[] hashCode = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hashCode = factory.generateSecret(spec).getEncoded();

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            LOGGER.error(e);
            throw new RuntimeException("Can't generate hashCode :" + e);
        }
        return hashCode;
    }
}





