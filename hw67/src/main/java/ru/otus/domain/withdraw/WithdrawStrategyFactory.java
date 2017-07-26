package ru.otus.domain.withdraw;

import ru.otus.domain.atm.Cell;

import java.util.List;

/**
 * User: Vladimir Koba
 * Date: 25.07.2017
 * Time: 0:03
 */

public class WithdrawStrategyFactory {

    public static WithdrawStrategy create(WithdrawStrategyType withdrawStrategyType, List<Cell> cells, long desiredAmount) {
        switch (withdrawStrategyType) {
            case SMALL_FIRST_STRATEGY:
                return new SmallFirstWithdrawStrategy(cells);
            case LARGE_FIRST_STRATEGY:
                return new LargeFirstWithdrawStrategy(cells);
            default:
                throw new RuntimeException("Unknown strategy type: " + withdrawStrategyType);
        }
    }
}
