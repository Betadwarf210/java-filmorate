package ru.yandex.practicum.filmorate.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

public class UserTests {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();


    @Test
    @DisplayName("Проверка валидации. Передаем верно-заполненный объект")
    void correctlyFilledUserTest() {
        final User user = new User("mr@gmail.ru", "testLogin", "testName", LocalDate.of(2000, 12, 12));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        Assertions.assertTrue(violations.isEmpty(),"Все заполнено верно");
    }
    @Test
    @DisplayName("Проверка валидации. Передаем неверно-заполненный Email")
    void correctlyEmailUserTestFirst() {
        final User user = new User("badTestEmail", "testLogin", "testName", LocalDate.of(2000, 12, 12));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        Assertions.assertFalse(violations.isEmpty(),"Неверно заполненый email");
    }
    @Test
    @DisplayName("Проверка валидации. Передаем пустой Email")
    void correctlyEmailUserTestSecond() {
        final User user = new User("", "testLogin", "testName", LocalDate.of(2000, 12, 12));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        Assertions.assertFalse(violations.isEmpty(),"Blank email");
    }

    @Test
    @DisplayName("Проверка валидации. Передаем будущую дату")
    void correctlyBirthdayUserTest() {
        final User user = new User("mr@gmail.ru", "testLogin", "testName", LocalDate.of(2025, 11, 14));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        Assertions.assertFalse(violations.isEmpty(),"Не верная дата(будующий сегмент)");
    }
//    @Test
//    @DisplayName("Проверка валидации. Передаем пустой логин")
//    void correctlyLoginUserTest() {
//        final User user = new User("mr@gmail.ru", "", "testName", LocalDate.of(2000, 11, 14));
//        Set<ConstraintViolation<User>> violations = validator.validate(user);
//        Assertions.assertFalse(violations.isEmpty(),"Пустой логин");
//    }

}
