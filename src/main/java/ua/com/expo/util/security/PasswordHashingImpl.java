package ua.com.expo.util.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class PasswordHashingImpl implements IPasswordHashing {

    @Override
    public byte[] saltGenerator() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[BYTE_ARRAY_SIZE];
        random.nextBytes(salt);
        return salt;
    }

    @Override
    public byte[] hashGenerator(String password, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return factory.generateSecret(spec).getEncoded();
    }
}





