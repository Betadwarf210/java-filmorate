package ru.yandex.practicum.filmorate.validators;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.exception.DateValidationException;
import ru.yandex.practicum.filmorate.exception.IdValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.Map;

@UtilityClass
public class ValidationFilm {
    private static final Logger logger = LoggerFactory.getLogger(FilmController.class);//Лог действий
    public static boolean isValidDate(LocalDate dateStr) {
        logger.info("ValidationFieldsFilm.isValidDate: Проверка даты по кретерию");//Запись в лог
        return dateStr.isBefore(LocalDate.of(1895, 12, 28));
    }
    public static void noFoundFilm(Film film, Map<Integer,Film> films) {
        if (!films.containsKey(film.getId())) {
            logger.info("ValidationFieldsFilm.isValidDate: Проверка существует ли фильм");//Запись в лог
            throw new IdValidationException("Такого фильма не существует"); //Исключение
        }
    }
    public static void validateFields(Film film) {
        logger.info("ValidationFieldsFilm.validateFields: Проверка даты по кретерию");//Запись в лог
        if (isValidDate(film.getReleaseDate())){
            //Исключение
            throw new DateValidationException("Дата выхода фильма не должна быть раньше 28 декабря 1895 года");
        }
    }
}
