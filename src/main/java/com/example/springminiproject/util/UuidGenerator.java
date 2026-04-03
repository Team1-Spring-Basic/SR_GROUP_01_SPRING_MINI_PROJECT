package com.example.springminiproject.util;

import java.util.UUID;

public class UuidGenerator {

    public static UUID generate() {
        return UUID.randomUUID();
    }

    public static String generateAsString() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String generateAsStringWithDashes() {
        return UUID.randomUUID().toString();
    }
}
