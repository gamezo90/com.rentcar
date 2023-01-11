package com.rentcar.controller.utils;

import com.rentcar.domain.Gender;

import static com.rentcar.domain.Gender.*;

public class StringToGenderConvertUtil {

    public static Gender parseStringToGender(String string) {
        if (string.equalsIgnoreCase("MALE")) {
            return MALE;
        }
        if (string.equalsIgnoreCase("FEMALE")) {
            return FEMALE;
        }
        else {
            return NOT_SELECTED;
        }
    }
}
