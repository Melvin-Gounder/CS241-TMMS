package fj.usp.tmms.web;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationFormTest {
    private static Validator validator;

    @BeforeAll
    static void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test void validEmailPasses() { assertTrue(validator.validate(form("user@example.com")).isEmpty()); }
    @Test void plusAddressPasses() { assertTrue(validator.validate(form("user+traffic@example.com")).isEmpty()); }
    @Test void missingDomainSuffixFails() { assertFalse(validator.validate(form("user@invalid")).isEmpty()); }
    @Test void extraAtSymbolFails() { assertFalse(validator.validate(form("user@@example.com")).isEmpty()); }
    @Test void equalsSymbolFails() { assertFalse(validator.validate(form("user=name@example.com")).isEmpty()); }
    @Test void spacesFail() { assertFalse(validator.validate(form("user name@example.com")).isEmpty()); }

    private RegistrationForm form(String email) {
        RegistrationForm f = new RegistrationForm();
        f.setFullName("Test User");
        f.setEmail(email);
        f.setPassword("Password1!");
        return f;
    }
}
