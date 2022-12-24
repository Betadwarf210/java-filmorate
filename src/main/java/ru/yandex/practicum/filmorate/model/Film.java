package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
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

}
