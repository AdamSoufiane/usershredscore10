package infrastructure.rest;

import application.ports.in.UserRegistrationPort;
import domain.exceptions.UserNotFoundException;
import domain.exceptions.UserRegistrationFailedException;
import infrastructure.rest.dto.UserRequest;
import infrastructure.rest.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserRegistrationController {

    private final UserRegistrationPort userRegistrationPort;

    public UserRegistrationController(UserRegistrationPort userRegistrationPort) {
        this.userRegistrationPort = userRegistrationPort;
    }

    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userRegistrationPort.registerUser(userRequest);
        if (userResponse == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred during user registration.");
        }
        return userResponse;
    }

    @ExceptionHandler(UserRegistrationFailedException.class)
    public void handleUserRegistrationFailedException(UserRegistrationFailedException exception) {
        throw new ResponseStatusException(HttpStatus.valueOf(exception.getStatusCode()), exception.getMessage(), exception);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public void handleUserNotFoundException(UserNotFoundException exception) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.", exception);
    }
}