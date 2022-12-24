package ru.yandex.practicum.filmorate.exception;

public class NotFoundedException extends RuntimeException {

    public NotFoundedException(String message) {
        super(message);
    }
}
