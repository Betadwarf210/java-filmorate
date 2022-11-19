package ru.yandex.practicum.filmorate.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.exception.IdValidationException;
import ru.yandex.practicum.filmorate.exception.LoginValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Map;
import java.util.regex.Pattern;

public class ValidationUser {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    public static boolean isValidLogin(String str) {
        log.info("ValidationFieldsUser.isValidLogin: Проверка на пробелы в поле Login");
        Pattern pattern = Pattern.compile("\\s");
        return pattern.matcher(str).find();
    }
    public static void noFoundUser(User user, Map<Integer,User> users) {
        if (!users.containsKey(user.getId())) {
            log.info("ValidationFieldsUser.noFoundUser: Проверка существует ли пользователь");
            throw new IdValidationException("Такого пользователя не существует");
        }
    }
    public static void validateFields(User user) {
        if (isValidLogin(user.getLogin())){
            log.info("ValidationFieldsUser.validateFields: Проверяем на существование пользователя");
            throw new LoginValidationException("Логин содержит пробелы");
        }
        if (user.getName() == null || user.getName().isEmpty() || user.getName().isBlank()){
            log.info("ValidationFieldsUser.validateFields: Установка имя логина как имя пользователя");
            user.setName(user.getLogin());
        }
    }
}
