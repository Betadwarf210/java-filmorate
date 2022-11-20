package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class User {
    private Integer id; // Идентификатор

    @NotEmpty
    @Email(message = "Неправильно введен адрес электронной почты")
    private String email; //Электронная почта

    @NotEmpty
    private String login; //Логин пользователя


    private String name; //Имя пользователя

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @PastOrPresent
    private LocalDate birthday; //Дата рождения (Или сегодня, или в прошедшем времени)

}
