package com.rentcar.controller.utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConvertUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static LocalDate parseToLocalDate(String date) {

        return LocalDate.parse(date, DATE_TIME_FORMATTER);
    }

}
