package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.yandex.practicum.filmorate.exception.NotFoundedException;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
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

    Set<Integer> friends = new HashSet<>();

    public User(String email, String login, String name, LocalDate birthday) {
        this.email = email;
        this.login = login;
        if (name == null || name.isEmpty() || name.isBlank()) {
            this.name = login;
        } else {
            this.name = name;
        }
        this.birthday = birthday;
    }

    public void addFriend(Integer idFriend) {
        if (idFriend > 0) {
            friends.add(idFriend);
        } else {
            throw new NotFoundedException("Для добавления в друзья,должен быть положительный ID");
        }
    }

    public void deleteFriend(Integer idFriend) {
        if (idFriend > 0) {
            friends.remove(idFriend);
        } else {
            throw new NotFoundedException("Для удаления из друзей,должен быть положительный ID");
        }
    }

}
