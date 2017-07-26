package ru.otus.domain.withdraw;

import ru.otus.domain.atm.Cell;

public interface WithdrawStrategy {
    Cell nextCell(long desiredAmount);
}
