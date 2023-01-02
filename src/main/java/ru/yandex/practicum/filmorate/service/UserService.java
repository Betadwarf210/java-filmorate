package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.EntityNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserService {
    private final InMemoryUserStorage inMemoryUserStorage;

    @Autowired
    public UserService(InMemoryUserStorage inMemoryUserStorage) {
        this.inMemoryUserStorage = inMemoryUserStorage;
    }

    public Map<Integer, User> getUsers() {
        return inMemoryUserStorage.getAll();
    }

    public User getUserById(Integer id) {
        ExistsUser(id);
        return inMemoryUserStorage.getById(id);
    }

    public User addUser(User user) {
        log.info("UserService.addUser: Начали создание пользователя");
        if (user.getName() == null || user.getName().isEmpty() || user.getName().isBlank()) {
            log.info("UserController.createUser: Устанавливаем Имя пользователю(его логин)");
            user.setName(user.getLogin());
        }
        System.out.println(user);
        return inMemoryUserStorage.add(user);
    }

    public void deleteUser(Integer id) {
        inMemoryUserStorage.delete(id);
    }

    public User updateUser(User user) {
        log.info("UserService.updateUser: Обновляем пользователя");
        ExistsUser(user);
        if (user.getName() == null || user.getName().isEmpty() || user.getName().isBlank()) {
            log.info("UserController.updateUser: Устанавливаем Имя пользователю(его логин)");
            user.setName(user.getLogin());
        }
        return inMemoryUserStorage.update(user);
    }

    public void addFriend(Integer idUser, Integer idFriend) {
        ExistsUser(idUser, idFriend);
        inMemoryUserStorage.getAll().get(idUser).addFriend(idFriend);
        inMemoryUserStorage.getAll().get(idFriend).addFriend(idUser);
    }

    public void deleteFriend(Integer idUser, Integer idFriend) {
        ExistsUser(idUser, idFriend);
        inMemoryUserStorage.getAll().get(idUser).deleteFriend(idFriend);
        inMemoryUserStorage.getAll().get(idFriend).deleteFriend(idUser);
    }

    /**
     * Выводим друзей Пользователя
     */
    public List<User> getUserFriends(Integer idUser) {
        ExistsUser(idUser);
        List<User> friends = new ArrayList<>();
        for (Integer friendId : inMemoryUserStorage.getAll().get(idUser).getFriends()) {
            if (inMemoryUserStorage.getAll().containsKey(friendId)) {
                friends.add(inMemoryUserStorage.getAll().get(friendId));
            }
        }
        return friends;
    }

    /**
     * Выводим общих друзей
     */
    public List<User> getCommonFriend(Integer idUser, Integer idFriend) {
        ExistsUser(idUser, idFriend);
        List<User> commonFriend = new ArrayList<>();
        for (Integer idFriendUser : inMemoryUserStorage.getAll().get(idUser).getFriends()) {
            if (inMemoryUserStorage.getAll().get(idFriend).getFriends().contains(idFriendUser)) {
                commonFriend.add(inMemoryUserStorage.getAll().get(idFriendUser));
            }
        }
        return commonFriend;
    }

    private void ExistsUser(Integer id) {
        if (!getUsers().containsKey(id)) {
            throw new EntityNotFoundException("Нет пользователя с таким ID.");
        }
    }

    private void ExistsUser(User user) {
        if (!getUsers().containsKey(user.getId())) {
            throw new EntityNotFoundException("Нет пользователя с таким ID.");
        }
    }

    private void ExistsUser(Integer id, Integer otherId) {
        if (!getUsers().containsKey(id) || !getUsers().containsKey(otherId)) {
            throw new EntityNotFoundException("Нет пользователя с таким ID.");
        }
    }
}