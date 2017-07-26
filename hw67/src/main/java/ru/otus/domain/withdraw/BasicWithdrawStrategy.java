package ru.otus.domain.withdraw;

import ru.otus.domain.atm.Cell;
import ru.otus.domain.withdraw.exception.NotEnoughMoneyException;

import java.util.List;

/**
 * User: Vladimir Koba
 * Date: 26.07.2017
 * Time: 0:34
 */
public abstract class BasicWithdrawStrategy implements WithdrawStrategy {
    private final List<Cell> cells;

    public BasicWithdrawStrategy(List<Cell> cells) {
        this.cells = cells;
    }


    @Override
    public Cell nextCell(long desiredAmount) {
        return sortCells(cells).stream()
                .filter(cell -> cell.remain() >= desiredAmount)
                .filter(cell -> cell.cellNominal() <= desiredAmount)
                .findFirst()
                .orElseThrow(()-> new NotEnoughMoneyException("Not enough money for this operation"));
    }

    public abstract List<Cell> sortCells(List<Cell> cells);

}
