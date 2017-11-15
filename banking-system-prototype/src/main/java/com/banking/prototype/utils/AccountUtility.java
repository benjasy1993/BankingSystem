package com.banking.prototype.utils;

import java.util.Random;

public class AccountUtility {

    private static final int ACCOUNT_NUM_LENGTH = 10;

    public static String generateAccountNum() {
        Random rng = new Random();
        String original = String.valueOf(rng.nextLong());
        String value = "";
        if (original.length() >= ACCOUNT_NUM_LENGTH) {
            value = original.substring(original.length() - ACCOUNT_NUM_LENGTH, original.length());
        } else {
            for (int i = 0; i < ACCOUNT_NUM_LENGTH - original.length(); i++) {
                value = "0".concat(value);
            }
        }
        return value;
    }
}
