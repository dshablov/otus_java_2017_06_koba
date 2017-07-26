package ru.otus.domain.withdraw.exception;

/**
 * User: Vladimir Koba
 * Date: 26.07.2017
 * Time: 0:54
 */
public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
