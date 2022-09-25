package com.pollup.api.security;

import com.pollup.api.model.Artist;
import com.pollup.api.repository.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class RegistrationValidator implements Validator {

    private final ArtistRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Artist.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println(target);
        if(target instanceof Artist) {
            Artist user = (Artist) target;

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");

            if(userRepository.findByEmail(user.getEmail()).isPresent()) {
                errors.rejectValue("email", "Duplicate.userForm.email");
            }

            if(user.getEmail().length() > 64) {
                errors.rejectValue("email", "Size.userForm");
            }

            if (user.getPassword().length() < 8) {
                errors.rejectValue("password", "Size.userForm.password");
            }

            if(user.getPassword().length() > 64) {
                errors.rejectValue("password", "Size.userForm");
            }
        }
    }
}
