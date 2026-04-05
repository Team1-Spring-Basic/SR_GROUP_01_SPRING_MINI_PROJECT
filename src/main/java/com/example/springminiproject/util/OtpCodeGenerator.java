package com.example.springminiproject.util;

import java.util.Random;

public class OtpCodeGenerator {
    public static String generate(){
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}
