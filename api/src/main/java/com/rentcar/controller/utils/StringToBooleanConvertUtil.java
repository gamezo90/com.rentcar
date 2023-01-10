package com.rentcar.controller.utils;

public class StringToBooleanConvertUtil {
    public static Boolean parseStringToBoolean(String string) {
        if (string == "yes" || string == "Yes") {
            return true;
        }
        if (string == "no" || string == "No") {
            return false;
        }
        else {
            throw new IllegalArgumentException(
                    String.format("Conditioner pattern yes/Yes/no/No"));
        }
    }
}
