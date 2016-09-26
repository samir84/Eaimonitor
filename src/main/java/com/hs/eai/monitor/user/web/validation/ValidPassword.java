package com.hs.eai.monitor.user.web.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@NotNull
@Size(min=6, max=45)
@Pattern.List({
    @Pattern(regexp = "(?=.*[0-9]).+", message = "Password must contain one digit."),
    @Pattern(regexp = "(?=.*[a-z]).+", message = "Password must contain one lowercase letter."),
    @Pattern(regexp = "(?=.*[a-z]).+", message = "Password must contain one upper letter."),
    @Pattern(regexp = "(?=.*[!@#$%^&*+=?-_()/\"\\.,<>~`;:]).+", message ="Password must contain one special character."),
    @Pattern(regexp = "(?=\\S+$).+", message = "Password must contain no whitespace.")
})
@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface ValidPassword {

    String message() default "Invalid Password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}