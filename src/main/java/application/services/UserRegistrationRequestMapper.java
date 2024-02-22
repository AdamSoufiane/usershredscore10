package application.services;

import domain.entities.User;
import infrastructure.rest.UserRequest;
import domain.exceptions.InvalidUserRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserRegistrationRequestMapper {

    /**
     * Maps a UserRequest object to a User domain entity.
     * @param userRequest the user registration request
     * @return a User entity populated with the request data
     * @throws InvalidUserRequestException if the request data is invalid
     */
    public User toUserEntity(UserRequest userRequest) throws InvalidUserRequestException {
        if (userRequest == null || userRequest.getEmail() == null || userRequest.getEmail().trim().isEmpty() || userRequest.getPassword() == null || userRequest.getPassword().trim().isEmpty() || userRequest.getName() == null || userRequest.getName().trim().isEmpty()) {
            throw new InvalidUserRequestException("User request data is null or contains empty fields");
        }

        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        // Additional fields can be set here if they are required

        // Perform further validation if necessary

        return user;
    }

}