package com.example.library.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class CustomToken {
    private static final String SECRET_KEY="myscrectkey123";
    public static  String generateToken(String username,String department){
        long timestamp = System.currentTimeMillis();
        String payload=username+"|"+department+"|"+timestamp;
        String signature=hashWithSHA256(payload+SECRET_KEY);
        return  payload+"|"+signature;
    }

    public  static  boolean validateToken(String token) {
        String[] parts = token.split("\\|");
        if (parts.length != 4) {
            return false;
        }
        String payload = parts[0] + "|" + parts[1] + "|" + parts[2];
        String providedSignature = parts[3];
        String expectedSignature = hashWithSHA256(payload + SECRET_KEY);
        return providedSignature.equals(expectedSignature);
    }
    public static String extractDepartment(String token) {
        return token.split("\\|")[1];
    }
    public static long extractTimestamp(String token) {
        return Long.parseLong(token.split("\\|")[2]);
    }
    private static String hashWithSHA256(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing data", e);
        }
    }
    }

