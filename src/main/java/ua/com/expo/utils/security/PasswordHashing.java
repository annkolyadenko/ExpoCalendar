package ua.com.expo.utils.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

/**
 * <p> Four main properties of hash functions to be secure:
 * - deterministic;
 * - not reversible;
 * - high entropy;
 * - resists collisions.
 * Though password hashing functions should be slow to avoid brute force attacks.
 */
//TO DO!!!
//All static utils rewrite!!!
public class PasswordHashing {

    private static final int BYTE_ARRAY_SIZE = 16;

    /**
     * Creates a salt (is a random sequence that is generated for each new hash)
     *
     * @return the new byte[] salt for further generating password
     */
    public static byte[] saltGenerator() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[BYTE_ARRAY_SIZE];
        random.nextBytes(salt);
        return salt;
    }

    /**
     * 1. Create a PBEKeySpec and a SecretKeyFactory which we'll instantiate using the PBKDF2WithHmacSHA1 algorithm;
     * 2. @param 65536  is effectively the strength parameter.It indicates how many iterations that this algorithm run for,
     * increasing the time it takes to produce the hash;
     *
     * @return the new byte[] hash of required password.
     */
    public static byte[] hashGenerator(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return factory.generateSecret(spec).getEncoded();
    }

    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {

    }
}





