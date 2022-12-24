package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundedException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FilmService {
    InMemoryFilmStorage inMemoryFilmStorage;

    @Autowired
    public FilmService(InMemoryFilmStorage inMemoryFilmStorage) {
        this.inMemoryFilmStorage = inMemoryFilmStorage;
    }

    public Map<Integer, Film> getFilms() {
        return inMemoryFilmStorage.getAll();
    }

    public Film getFilmById(Integer id) {
        existsFilm(id);
        return inMemoryFilmStorage.getById(id);
    }

    public Film addFilm(Film film) {
        return inMemoryFilmStorage.add(film);
    }

    public void deleteFilm(Integer id) {
        inMemoryFilmStorage.delete(id);
    }

    public void updateFilm(Film film) {
        existsFilm(film);
        inMemoryFilmStorage.update(film);
    }

    /**
     * Добавление лайка на фильм
     */
    public void addLikeFilm(Integer filmId, Integer userId) {
        existsFilm(filmId);
        if (!inMemoryFilmStorage.getAll().get(filmId).getUsersLikedFilm().contains(userId)) {
            inMemoryFilmStorage.getAll().get(filmId).addLike(userId);
        }
    }

    /**
     * Удаление лайка на фильм
     */
    public void deleteLikeFilm(Integer filmId, Integer userId) {
        existsFilmOrUser(filmId, userId);
        inMemoryFilmStorage.getAll().get(filmId).deleteLike(userId);
    }

    /**
     * Вывод популярных фильмов
     */
    public List<Film> getMostPopularMoviesOfLikes(Integer count) {
        Comparator<Film> filmComparator = (film1, film2) -> {
            if (film2.getRating().compareTo(film1.getRating()) == 0) {
                return film1.getName().compareTo(film2.getName());
            }
            return film2.getRating().compareTo(film1.getRating());
        };
        return inMemoryFilmStorage.getAll().values().stream()
                .sorted(filmComparator)
                .limit(count)
                .collect(Collectors.toList());
    }

    private Boolean existsFilm(Film film) {
        if (!getFilms().containsKey(film.getId())) {
            throw new NotFoundedException("Нет фильма с таким ID.");
        } else {
            return true;
        }
    }

    private Boolean existsFilm(Integer filmId) {
        if (!getFilms().containsKey(filmId)) {
            throw new NotFoundedException("Нет фильма с таким ID.");
        } else {
            return true;
        }
    }

    private Boolean existsFilmOrUser(Integer id, Integer userId) {
        if (userId < 1) {
            throw new NotFoundedException("Id пользователя должно быть больше 1.");
        } else if (!getFilms().containsKey(id)) {
            throw new NotFoundedException("Нет фильма с таким ID.");
        } else {
            return true;
        }
    }
}
