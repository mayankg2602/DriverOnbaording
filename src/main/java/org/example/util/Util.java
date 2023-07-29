package org.example.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Regular expression for valid mobile number
        String regex = "^(0|91)?[6-9]\\d{9}$";

        // Create a pattern object and compile the regex
        Pattern pattern = Pattern.compile(regex);

        // Create a matcher object to match the input string against the regex
        Matcher matcher = pattern.matcher(phoneNumber);

        // Return true if the input string matches the regex pattern, otherwise false
        return matcher.matches();
    }

}
