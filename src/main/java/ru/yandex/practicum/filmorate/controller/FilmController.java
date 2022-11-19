package ru.yandex.practicum.filmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validators.ValidationFilm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final Logger log = LoggerFactory.getLogger(FilmController.class);//Лог действий
    private final Map<Integer, Film> films = new HashMap<>();
    Integer filmId = 0; // ID фильма


    @PostMapping
    public Film addFilm(Film film) //Добавление фильма
    {
        log.info("FilmController.createFilm:Добавление фильма"); //Запись в лог
        ValidationFilm.validateFields(film); //Валидация данных о фильме
        Integer id = generateFilmId(); //Генерация id для фильма
        film.setId(id); //Установка сгенерированного id для фильма
        films.put(id, film); //Запись нового фильма в таблицу
        return film;
    }

    @PutMapping
    public Film updateFilm(Film film)
    {
        log.info("FilmController.createFilm: Обновление фильма"); //Запись в лог
        ValidationFilm.noFoundFilm(film,films); //Валидация данных о фильме
        ValidationFilm.validateFields(film); //Валидация данных о фильме
        films.put(film.getId(), film); //Обновление данных о фильме в таблице
        return film;
    }

    public Integer generateFilmId() //Генерация id
    {
        filmId++;
        return filmId;
    }

    @GetMapping
    public List<Film> getAllFilms() //Получение списка всех фильмов
    {
        return new ArrayList<>(films.values()); //Список всех фильмов
    }
}
