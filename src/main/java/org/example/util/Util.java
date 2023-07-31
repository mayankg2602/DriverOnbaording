package org.example.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class providing common functionalities across the application.
 * It is declared as final with a private constructor to prevent instantiation.
 */
public final class Util {

    /**
     * Regular expression pattern for validating mobile number.
     * Allows 10 digit number starting with 6-9 or prefixed with 0 or 91 and then followed by 10 digit number starting with 6-9.
     */
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^(0|91)?[6-9]\\d{9}$");

    // Private constructor to prevent instantiation of this utility class.
    private Util() {
    }

    /**
     * Method to validate a phone number against a defined pattern.
     *
     * @param phoneNumber The phone number to validate.
     * @return {@code true} if phone number is valid, {@code false} otherwise.
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Create a matcher object to match the input string against the regex
        Matcher matcher = PHONE_NUMBER_PATTERN.matcher(phoneNumber);

        // Return true if the input string matches the regex pattern, otherwise false
        return matcher.matches();
    }

}
