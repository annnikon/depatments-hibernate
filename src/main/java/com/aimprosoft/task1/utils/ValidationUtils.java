package com.aimprosoft.task1.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {


    private Map<String, String> errors = new HashMap<>();

    public void validateText(String input, String fieldName, String regexp) {
        if (input == null) {
            errors.put(fieldName, "Cannot be empty");

        }
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            errors.put(fieldName, "Should contain only allowed symbols");

        }

    }

    public void validateDate(String input, String fieldName, String dataFormat) {

        if (input == null) {
            errors.put(fieldName, "Cannot be empty");

        }

        try {
            DateFormat format = new SimpleDateFormat(dataFormat);
            format.parse(input);

        } catch (ParseException e) {
            errors.put(fieldName, "Should be in format: " + dataFormat);

        }


    }

    public void validateInteger(String input, String fieldName) {

        if (input == null) {
            errors.put(fieldName, "Cannot be empty");


        }
        try {
            Integer.parseInt(input);

        } catch (NumberFormatException e) {
            errors.put(fieldName, e.getMessage());


        }
    }

    public void validateNotNull(String input, String fieldName) {

        if (input == null || input.isEmpty() ) {
            errors.put(fieldName, "Cannot be empty");

        }
    }


    public boolean isValid() {
        return errors.isEmpty();
    }

    public Map<String, String> getErrors() {
        return errors;
    }


}