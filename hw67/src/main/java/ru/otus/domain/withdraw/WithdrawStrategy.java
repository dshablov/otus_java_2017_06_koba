package ru.otus.domain.withdraw;

import ru.otus.domain.atm.Cell;


/**
 * Определяет стратегию выдачи денег банкоматом. Метод должен вернуть ячейку,
 * из которой нужно взять очередную купюру. На вход идет остаток суммы, которую надо получить.
 */
public interface WithdrawStrategy {
    Cell nextCell(long desiredAmount);
}
