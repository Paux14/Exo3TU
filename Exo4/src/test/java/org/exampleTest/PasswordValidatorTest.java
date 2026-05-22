package org.exampleTest;

import java.util.stream.Stream;

import org.example.PasswordValidator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PasswordValidatorTest {

    private PasswordValidator validator;

    @BeforeEach
    void setUp() {
        validator = new PasswordValidator();
    }

    @Test
    void isValid_validPassword_returnsTrue() {
        assertTrue(validator.isValid("Password1!"));
    }

    @Test
    void isValid_nullPassword_returnsFalse() {
        assertFalse(validator.isValid(null));
    }

    @Test
    void getErrorMessage_validPassword_returnsValidMessage() {
        assertEquals("Password is valid", validator.getErrorMessage("Admin2024@"));
    }

    @Test
    void getErrorMessage_nullPassword_returnsNullMessage() {
        assertEquals("Password must not be null", validator.getErrorMessage(null));
    }

    @ParameterizedTest
    @CsvSource({
            "Password1!, true, Password is valid",
            "Admin2024@, true, Password is valid",
            "short1!, false, Password must contain at least 8 characters",
            "PASSWORD1!, false, Password must contain at least one lowercase letter",
            "password1!, false, Password must contain at least one uppercase letter",
            "Password!, false, Password must contain at least one digit",
            "Password1, false, Password must contain at least one special character"
    })
    void validatePassword_csvSource(String password, boolean expectedValid, String expectedMessage) {
        assertEquals(expectedValid, validator.isValid(password));
        assertEquals(expectedMessage, validator.getErrorMessage(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Password1!", "Admin2024@", "Secure99#", "MyPass42$"})
    void isValid_strongPasswords_returnsTrue(String password) {
        assertTrue(validator.isValid(password));
    }

    @ParameterizedTest
    @MethodSource("invalidPasswordProvider")
    void isValid_invalidPasswords_returnsFalse(String password, String expectedMessage) {
        assertFalse(validator.isValid(password));
        assertEquals(expectedMessage, validator.getErrorMessage(password));
    }

    static Stream<Object[]> invalidPasswordProvider() {
        return Stream.of(
                new Object[]{"short1!", "Password must contain at least 8 characters"},
                new Object[]{"PASSWORD1!", "Password must contain at least one lowercase letter"},
                new Object[]{"password1!", "Password must contain at least one uppercase letter"},
                new Object[]{"Password!", "Password must contain at least one digit"},
                new Object[]{"Password1", "Password must contain at least one special character"}
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void getErrorMessage_nullOrEmpty_returnsExpectedMessage(String password) {
        assertFalse(validator.isValid(password));
        if (password == null) {
            assertEquals("Password must not be null", validator.getErrorMessage(password));
        } else {
            assertEquals("Password must contain at least 8 characters", validator.getErrorMessage(password));
        }
    }
}
