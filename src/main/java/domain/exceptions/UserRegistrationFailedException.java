package domain.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserRegistrationFailedException extends RuntimeException {

    private static final long serialVersionUID = -3941022475762235895L;

    private final int statusCode;
    private final LocalDateTime timestamp;
    private final String errorCode;
    private final Map<String, Object> details;
    private final String userAction;
    private final ExceptionSeverity severity;

    public UserRegistrationFailedException(String message, int statusCode, String errorCode, Map<String, Object> details, String userAction, ExceptionSeverity severity) {
        super(message);
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
        this.errorCode = errorCode;
        this.details = details;
        this.userAction = userAction;
        this.severity = severity;
    }
}
