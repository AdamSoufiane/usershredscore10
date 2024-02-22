package application.services;

import domain.entities.User;
import infrastructure.rest.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import application.exceptions.MappingException;
import java.time.DateTimeException;
import java.util.Objects;

public class UserRegistrationResponseMapper {

    private static final Logger logger = LoggerFactory.getLogger(UserRegistrationResponseMapper.class);

    /**
     * Maps a User domain entity to a UserResponse data structure.
     * If the User entity is null or fields are invalid, a MappingException is thrown with detailed context about the failure.
     * 
     * @param user the User entity to map from, must not be null and must have valid fields
     * @return the mapped UserResponse, never null
     * @throws MappingException if the user is null, fields are invalid, or if any error occurs during the mapping process
     */
    public UserResponse toUserResponse(User user) throws MappingException {
        if (user == null) {
            String errorMessage = "User entity is null and cannot be mapped to UserResponse.";
            logger.error(errorMessage);
            throw new MappingException(errorMessage);
        }

        try {
            Objects.requireNonNull(user.getId(), "User id cannot be null");
            Objects.requireNonNull(user.getName(), "User name cannot be null");
            Objects.requireNonNull(user.getEmail(), "User email cannot be null");

            UserResponse response = new UserResponse();
            response.setId(user.getId());
            response.setName(user.getName());
            response.setEmail(user.getEmail());
            response.setCreatedAt(user.getCreatedDate());
            response.setUpdatedAt(user.getUpdatedDate());
            logger.info("Successfully mapped User to UserResponse.");
            return response;
        } catch (NullPointerException | DateTimeException | IllegalArgumentException e) {
            String errorMessage = "Mapping failure: " + e.getMessage();
            logger.error(errorMessage, e);
            throw new MappingException(errorMessage, e);
        }
    }
}
