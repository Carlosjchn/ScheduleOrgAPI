package com.jpa1prueba.jpademo.services;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.security.SecureRandom;

@Service
public class JweService {
    // A proper base64 encoded 256-bit (32 bytes) key
    private static final String SECRET_KEY_BASE64 = "dYCYZ9l+CvUH/I8FGlkzlHYAYY+TxCLUdipZK0S7Gxw=";
    private byte[] secretKey;

    @PostConstruct
    public void init() {
        // Decode the base64 secret key
        secretKey = Base64.getDecoder().decode(SECRET_KEY_BASE64);
    }

    // Method to generate a new key (used to generate SECRET_KEY_BASE64)
    private static String generateNewKey() {
        byte[] key = new byte[32];
        new SecureRandom().nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

    public String encryptUser(Long userId, String userName, String userType) {
        try {
            // Create JSON-style payload
            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .claim("userId", userId.toString())
                .claim("userName", userName)
                .claim("userType", userType)
                .issueTime(new Date())
                .build();

            // Create JWE header with "dir" algorithm and A256GCM encryption
            JWEHeader header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A256GCM);

            // Create encrypted JWT
            EncryptedJWT jwt = new EncryptedJWT(header, claims);

            // Create encrypter with the shared secret
            DirectEncrypter encrypter = new DirectEncrypter(secretKey);

            // Encrypt the JWT
            jwt.encrypt(encrypter);

            // Return the encrypted token
            return jwt.serialize();

        } catch (JOSEException e) {
            throw new RuntimeException("Error encrypting user data", e);
        }
    }

    public JWTClaimsSet decryptToken(String encryptedToken) {
        try {
            // Parse the encrypted JWT
            EncryptedJWT jwt = EncryptedJWT.parse(encryptedToken);

            // Create decrypter
            DirectDecrypter decrypter = new DirectDecrypter(secretKey);

            // Decrypt the JWT
            jwt.decrypt(decrypter);

            // Return the claims
            return jwt.getJWTClaimsSet();

        } catch (Exception e) {
            throw new RuntimeException("Error decrypting token", e);
        }
    }

    // Add this method to expose the secret key for frontend configuration
    public String getSecretKeyBase64() {
        return SECRET_KEY_BASE64;
    }
}