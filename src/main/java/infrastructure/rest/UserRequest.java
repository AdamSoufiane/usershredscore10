package infrastructure.rest;

import domain.validation.UserRegistrationValidator;
import domain.validation.ValidationResult;
import domain.exceptions.InvalidUserRegistrationException;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String email;
    private String password;
    private String name;

    public ValidationResult validate(UserRegistrationValidator validator) {
        ValidationResult result = validator.validateUserRegistration(this);
        if (!result.isValid()) {
            throw new InvalidUserRegistrationException("Validation failed", result.getErrorMessages());
        }
        return result;
    }

}