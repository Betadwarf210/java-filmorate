package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Map;

public interface UserStorage {

    Map<Integer, User> getAll();

    User getById(Integer id);

    User add(User user);

    User update(User user);

    void delete(Integer id);
}
