package com.example.encryption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecretComponent {

    @Value("${my.secret.property}")
    private String secretProperty;

    public void printSecret() {
        System.out.println("Decrypted Property: " + secretProperty);
    }
}

