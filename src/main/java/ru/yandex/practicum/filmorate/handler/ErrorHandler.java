package ru.yandex.practicum.filmorate.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.exception.EntityNotFoundException;

//Обработка некорректных запросов
@RestControllerAdvice
@Slf4j
public class ErrorHandler {


    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(final EntityNotFoundException e) {
        log.info("ErrorHandler. handleNotFoundException. Нет данных.");
        return new ErrorResponse(
                e.getMessage(), "Нет данных."
        );
    }
}
