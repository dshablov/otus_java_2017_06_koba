package ru.otus.domain.withdraw;

import ru.otus.domain.atm.Cell;

import java.util.Comparator;
import java.util.List;

/**
 * User: Vladimir Koba
 * Date: 24.07.2017
 * Time: 23:45
 */


/**
 * Стратегия выдачи, сначала пытается выдать мелкими купюрами
 */
public class SmallFirstWithdrawStrategy extends BasicWithdrawStrategy {

    public SmallFirstWithdrawStrategy(List<Cell> cells) {
        super(cells);
    }

    @Override
    public List<Cell> sortCells(List<Cell> cells) {
        cells.sort(Comparator.comparing(Cell::cellNominal));
        return cells;
    }
}
