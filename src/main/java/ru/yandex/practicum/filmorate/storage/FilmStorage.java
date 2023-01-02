package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Map;

/**
 * Загатовка для будущей работы с БД
 */



public interface FilmStorage {

    Map<Integer, Film> getAll();

    Film getById(Integer id);

    Film add(Film film);

    void delete(Integer id);

    Film update(Film film);
}
