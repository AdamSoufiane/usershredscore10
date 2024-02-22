package application.ports.out;

import domain.entities.User;
import java.util.Optional;
import org.springframework.dao.DataAccessException;
import domain.exceptions.UserNotFoundException;
import domain.exceptions.UserAlreadyExistsException;

public interface UserRepositoryPort {

    User saveUser(User user) throws DataAccessException, UserAlreadyExistsException;

    Optional<User> findUserByEmail(String email) throws DataAccessException;

    void deleteUserById(Long id) throws UserNotFoundException;

    boolean existsByEmail(String email);
}