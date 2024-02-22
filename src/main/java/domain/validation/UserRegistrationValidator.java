package domain.validation;

import domain.entities.User;
import application.ports.in.ValidationRuleManager;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@Getter
@RequiredArgsConstructor
public class UserRegistrationValidator {

    private final ValidationRuleManager validationRuleManager;

    public ValidationResult validateUserRegistration(@NonNull User user) {
        ValidationResult result = new ValidationResult();
        Set<ValidationRule> allRules = validationRuleManager.getValidationRules();
        for (ValidationRule rule : allRules) {
            if (!rule.validate(user)) {
                result.addErrorMessage(rule.getErrorMessage());
            }
        }
        result.setValid(result.getErrorMessages().isEmpty());
        return result;
    }

}
