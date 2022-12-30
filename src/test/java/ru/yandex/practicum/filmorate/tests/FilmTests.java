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
    @DisplayName("Правильные данные")
    void correctlyFieldFilmTest() {
        final Film film = new Film("testName", "testDescription", LocalDate.of(2000, 11, 14), 160, 1);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        Assertions.assertTrue(violations.isEmpty(), "Данные заполнены верно");
    }

    @Test
    @DisplayName("Проверка имени")
    void correctlyNameFilmTest() {
        final Film film = new Film("", "testDescription", LocalDate.of(2000, 11, 14), 160, 1);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        Assertions.assertFalse(violations.isEmpty(), "Пустое имя");
    }

    @Test
    @DisplayName("Проверка максимального количества элементов (201 элемент)")
    void correctlyDescriptionFilmTest() {
        final Film film = new Film("",
                "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111" +
                        "111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111",
                LocalDate.of(2000, 11, 14), 160, 1);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        Assertions.assertFalse(violations.isEmpty(), "Количество элементов больше 200");
    }

    @Test
    @DisplayName("Проверка продолжительности")
    void correctlyDurationFilmTest() {
        final Film film = new Film("testName", "testDescription", LocalDate.of(2000, 11, 14), -5, 1);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        Assertions.assertFalse(violations.isEmpty(), "Отрицательная продолжительность");
    }
}