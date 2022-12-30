package ru.yandex.practicum.filmorate.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FilmValidator.class)
public @interface ValidationFilm {
    String message() default "Date less 1895-12-28";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
