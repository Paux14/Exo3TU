package org.example;

public class PasswordValidator {

    private static final String MSG_NULL = "Password must not be null";
    private static final String MSG_LENGTH = "Password must contain at least 8 characters";
    private static final String MSG_LOWERCASE = "Password must contain at least one lowercase letter";
    private static final String MSG_UPPERCASE = "Password must contain at least one uppercase letter";
    private static final String MSG_DIGIT = "Password must contain at least one digit";
    private static final String MSG_SPECIAL = "Password must contain at least one special character";
    private static final String MSG_VALID = "Password is valid";

    public boolean isValid(String password) {
        return MSG_VALID.equals(getErrorMessage(password));
    }

    public String getErrorMessage(String password) {
        if (password == null) {
            return MSG_NULL;
        }
        if (password.length() < 8) {
            return MSG_LENGTH;
        }
        if (!containsLowercase(password)) {
            return MSG_LOWERCASE;
        }
        if (!containsUppercase(password)) {
            return MSG_UPPERCASE;
        }
        if (!containsDigit(password)) {
            return MSG_DIGIT;
        }
        if (!containsSpecial(password)) {
            return MSG_SPECIAL;
        }
        return MSG_VALID;
    }

    private boolean containsLowercase(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsUppercase(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsDigit(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsSpecial(String password) {
        for (char c : password.toCharArray()) {
            if (c == '!' || c == '@' || c == '#' || c == '$' || c == '%') {
                return true;
            }
        }
        return false;
    }
}
