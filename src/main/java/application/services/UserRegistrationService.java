package application.services;

import lombok.RequiredArgsConstructor;
import application.ports.out.UserRepositoryPort;
import domain.entities.User;
import domain.exceptions.UserRegistrationFailedException;
import domain.validation.UserRegistrationValidator;
import domain.validation.ValidationResult;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserRepositoryPort userRepositoryPort;
    private final UserRegistrationValidator userRegistrationValidator;

    public User registerUser(User user) throws UserRegistrationFailedException {
        ValidationResult validationResult = userRegistrationValidator.validateUserRegistration(user);
        if (validationResult.isValid()) {
            return userRepositoryPort.saveUser(user);
        } else {
            String errorMessages = validationResult.getErrorMessages().stream()
                                      .collect(Collectors.joining(", "));
            throw new UserRegistrationFailedException("Validation failed for user registration: " + errorMessages);
        }
    }
}
