package com.example.encryption;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class EncryptionUtils {
    private static PooledPBEStringEncryptor encryptor;

    static {
        encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("test123"); // Password for encryption
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
    }

    public static String encrypt(String input) {
        return encryptor.encrypt(input);
    }

    public static String decrypt(String input) {
        return encryptor.decrypt(input);
    }

    public static void main(String[] args) {
        String plaintext = "mySecretProperty";
        String encryptedText = encrypt(plaintext);
        System.out.println("Encrypted: " + encryptedText);
        String decryptedText = decrypt(encryptedText);
        System.out.println("Decrypted: " + decryptedText);
    }
}
