package com.rafaelovna.coursework___3.exception;


public class ValidationException extends RuntimeException{
    public ValidationException(String entity) {
        super("Ошибка валидации сущности: " + entity);
    }

}
