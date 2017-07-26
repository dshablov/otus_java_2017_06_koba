package ru.otus.domain.withdraw;

import ru.otus.domain.atm.Cell;

import java.util.Comparator;
import java.util.List;

/**
 * User: Vladimir Koba
 * Date: 24.07.2017
 * Time: 23:45
 */
public class LargeFirstWithdrawStrategy extends BasicWithdrawStrategy {

    public LargeFirstWithdrawStrategy(List<Cell> cells) {
        super(cells);
    }

    @Override
    public List<Cell> sortCells(List<Cell> cells) {
        cells.sort(Comparator.comparing(Cell::cellNominal).reversed());
        return cells;
    }
}
