package domain.exceptions;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;
    private final Long userId;

    public UserNotFoundException(Long userId, String message) {
        super(message);
        this.userId = userId;
    }
}