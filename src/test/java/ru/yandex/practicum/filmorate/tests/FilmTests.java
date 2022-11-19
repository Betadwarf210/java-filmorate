package ru.yandex.practicum.filmorate.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validators.ValidationFilm;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

public class FilmTests {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();


    @Test
    @DisplayName("")
    void correctlyFieldFilmTest() {
        final Film film = new Film(1, "", "", LocalDate.of(2000, 11, 14), 160);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        Assertions.assertTrue(violations.isEmpty(), "");
    }

    @Test
    @DisplayName("2)")
    void correctlyNameFilmTest() {
        final Film film = new Film(1, "", "", LocalDate.of(2000, 11, 14), 160);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        Assertions.assertFalse(violations.isEmpty(), "");
    }
    @Test
    @DisplayName("3)")
    void correctlyDescriptionFilmTest() {
        final Film film = new Film(1, "", "_________________________________________" +
                "________________________________________________________________________________________" +
                "________________________________________________________________________", LocalDate.of(2000, 11, 14), 160);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        Assertions.assertFalse(violations.isEmpty(), "");
    }
    @Test
    @DisplayName("4) ")
    void correctlyDurationFilmTest() {
        final Film film = new Film(1, "", "", LocalDate.of(2000, 11, 14), -5);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        Assertions.assertFalse(violations.isEmpty(), "");
    }
    @Test
    @DisplayName("5) ")
    void correctlyDateFilmTest() {
        final Film film = new Film(1, "", "", LocalDate.of(1985, 12, 27), 100);
        Assertions.assertFalse(ValidationFilm.isValidDate(film.getReleaseDate()), "");
    }
}