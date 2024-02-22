package application.ports.in;

import domain.exceptions.UserNotFoundException;
import domain.exceptions.UserRegistrationFailedException;
import domain.exceptions.UserAlreadyExistsException;
import infrastructure.rest.UserRequest;
import infrastructure.rest.UserResponse;

/**
 * The UserRegistrationPort interface defines the input port for the user registration feature.
 * It allows the infrastructure layer to trigger the registration process,
 * abstracting the underlying business logic handled by the application layer.
 */
public interface UserRegistrationPort {

    /**
     * Handles the registration of a new user.
     *
     * @param userRequest The registration request containing user details.
     * @return UserResponse The response containing registered user details.
     * @throws UserRegistrationFailedException If the registration process fails.
     * @throws UserNotFoundException If the user cannot be found.
     * @throws UserAlreadyExistsException If a user with the same email already exists.
     */
    UserResponse registerUser(UserRequest userRequest) throws UserRegistrationFailedException, UserNotFoundException, UserAlreadyExistsException;

}