package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import ru.yandex.practicum.filmorate.validators.ValidationFilm;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@ValidationFilm
public class Film {
    private Integer id; //Идентификатор фильма

    @NotEmpty(message = "Имя не может быть пустым")
    private String name; //Название

    @Size(max = 200,message = "Максимальная длина 200 символов")
    private String description; //Описание фильма (Максимум 200 символов)

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate; //Дата выхода фильма

    @Positive
    private Integer duration; //Продолжительность фильма

    Integer rating;//рейтинг(количество лайков)

    Set<Integer> usersLikedFilm = new HashSet<>();//Храним список пользователей,лайкнувших фильм

    //Рабочий конструктор для тестов
    public Film(String name, String description, LocalDate releaseDate, Integer duration, Integer rating) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        if (rating == null || rating < 0) {
            this.rating = 0;
        } else {
            this.rating = rating;
        }
    }

    public void addLike(Integer idUser) {
        usersLikedFilm.add(idUser);
        rating = rating + usersLikedFilm.size();
    }

    public void deleteLike(Integer idUser) {
        rating = rating - usersLikedFilm.size();
        usersLikedFilm.remove(idUser);
    }

}
