package ua.com.expo.util.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * <p> Four main properties of hash functions to be secure:
 * - deterministic;
 * - not reversible;
 * - high entropy;
 * - resists collisions.
 * Though password hashing functions should be slow to avoid brute force attacks.
 */
public interface IPasswordHashing {

    int BYTE_ARRAY_SIZE = 16;

    /**
     * Creates a salt (is a random sequence that is generated for each new hash)
     *
     * @return the new byte[] salt for further generating password
     */

    byte[] saltGenerator();

    /**
     * 1. Create a PBEKeySpec and a SecretKeyFactory which we'll instantiate using the PBKDF2WithHmacSHA1 algorithm;
     * 2. @param 65536  is effectively the strength parameter.It indicates how many iterations that this algorithm run for,
     * increasing the time it takes to produce the hash;
     *
     * @return the new byte[] hash of required password.
     */
    byte[] hashGenerator(String password, byte[] salt);
}
