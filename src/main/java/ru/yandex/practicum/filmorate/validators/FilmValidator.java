package ru.yandex.practicum.filmorate.validators;

import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class FilmValidator implements ConstraintValidator<ValidationFilm, Film> {

    @Override
    public boolean isValid(Film film, ConstraintValidatorContext constraintValidatorContext) {
        return !film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28));
    }
}
