package ru.yandex.practicum.filmorate.exception;

public class LoginValidationException extends RuntimeException
{
    public LoginValidationException(String message)
    {
        super(message);
    }
}