package com.rentcar.controller.utils;

public class StringToBooleanConvertUtil {
    public static Boolean parseStringToBoolean(String string) {
        if (string.equalsIgnoreCase("yes")) {
            return true;
        }
        if (string.equalsIgnoreCase("no")) {
            return false;
        }
        else {
            throw new IllegalArgumentException(
                    String.format("Conditioner pattern yes or no in any case"));
        }
    }
}
