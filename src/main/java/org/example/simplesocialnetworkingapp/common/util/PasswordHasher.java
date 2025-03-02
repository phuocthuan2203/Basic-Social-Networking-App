package org.example.simplesocialnetworkingapp.common.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {

    private static final int WORKLOAD = 10; // bcrypt cost factor

    public static String hash(String plaintext) {
        return BCrypt.hashpw(plaintext, BCrypt.gensalt(WORKLOAD));
    }

    public static boolean verify(String plaintext, String hashed) {
        if (hashed == null || !hashed.startsWith("$2a$")) {
            throw new IllegalArgumentException("Invalid hash format");
        }
        return BCrypt.checkpw(plaintext, hashed);
    }
}
