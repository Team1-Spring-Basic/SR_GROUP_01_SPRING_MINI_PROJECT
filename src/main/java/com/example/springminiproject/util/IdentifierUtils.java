package com.example.springminiproject.util;

public class IdentifierUtils {
    public static Boolean isEmail(String identifier) {
        return identifier.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
}
