package infrastructure.adapters;

import application.ports.out.UserRepositoryPort;
import domain.entities.User;
import domain.exceptions.CustomDataAccessException;
import domain.exceptions.UserNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class PostgresUserRepository implements UserRepositoryPort {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public User saveUser(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            user = entityManager.merge(user);
        }
        return user;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        try {
            TypedQuery<User> query = entityManager.createNamedQuery("User.findByEmail", User.class);
            query.setParameter("email", email);
            return query.getResultList().stream().findFirst();
        } catch (NoResultException e) {
            throw new EmptyResultDataAccessException("No user found with email: " + email, 1);
        } catch (Exception e) {
            throw new CustomDataAccessException("Persistence operation failed: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        try {
            User user = entityManager.find(User.class, id);
            if (user != null) {
                entityManager.remove(user);
            } else {
                throw new UserNotFoundException("User with id " + id + " not found.");
            }
        } catch (Exception e) {
            throw new CustomDataAccessException("Could not delete user with id " + id + ": " + e.getMessage(), e);
        }
    }
}
