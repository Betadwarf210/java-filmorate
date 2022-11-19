package ru.yandex.practicum.filmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validators.ValidationUser;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    Integer userId = 0;

    Map<Integer, User> users = new HashMap<>();

    @PostMapping
    public User createUser(@Valid @RequestBody User user)
    {
        log.info("UserController.createUser: Создание пользователя"); //Запись в лог
        ValidationUser.validateFields(user); //Валидация данных пользователя
        Integer id = generatedId(); //Генерация Id
        user.setId(id); //Установка Id
        users.put(id, user); //Запись данных о пользователе в таблицу(Создание новго пользователя)
        return user;
    }

    @PutMapping
    public User updateUser(User user) //Обнавления данных о пользователе
    {
        log.info("UserController.updateUser: Обновляем пользователя"); //Запись в лог
        ValidationUser.noFoundUser(user,users); //Валидация данных
        ValidationUser.validateFields(user); //Валидация данных
        users.put(user.getId(), user); //Запись обновленных данных о пользователе в таблицу
        return user;
    }

    @GetMapping
    public List<User> getAllUsers() //Получение списка всех пользователей
    {
        return new ArrayList<>(users.values()); //Список пользователей
    }

    public Integer generatedId() //Генерация id
    {
        userId++;
        return userId;
    }

}
