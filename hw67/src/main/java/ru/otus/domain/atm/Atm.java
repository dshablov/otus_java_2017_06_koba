package ru.otus.domain.atm;

import ru.otus.domain.withdraw.WithdrawStrategy;
import ru.otus.domain.withdraw.WithdrawStrategyFactory;
import ru.otus.domain.withdraw.WithdrawStrategyType;

import java.util.ArrayList;
import java.util.List;

public class Atm {
    private Long id;
    private List<Cell> cells;

    public Atm(Long id, List<Cell> cells) {
        this.id = id;
        this.cells = cells;
    }

    /**
     * Выдает желаемую сумму, или кидает ошибку, что денег нет. Считаем, что на вход идут суммы кратные номиналу
     * купюр в ячейках.
     *
     * @param desiredAmount желаемая сумма
     * @param desiredWithdrawTypeStrategy стратегия выдачи наличных
     * @return
     */
    public long withdraw(long desiredAmount, WithdrawStrategyType desiredWithdrawTypeStrategy) {
        long amount = 0;
        WithdrawStrategy withdrawStrategy = WithdrawStrategyFactory.create(desiredWithdrawTypeStrategy, cells, desiredAmount);
        while (desiredAmount != 0) {
            Cell cell = withdrawStrategy.nextCell(desiredAmount);
            amount += cell.takeBanknote();
            desiredAmount -= cell.cellNominal();
        }
        return amount;
    }

    /**
     *
     * @return остаток денег в банкомате
     */
    public long remain() {
        return cells.stream()
                .mapToLong(Cell::remain)
                .sum();
    }

    /**
     * @return снэпшот текущего состояния
     */
    public AtmMemento saveState() {
        List<Cell> newCells = new ArrayList<>();
        for (Cell cell : cells) {
            newCells.add(new Cell(cell));
        }
        return new AtmMemento(newCells);
    }

    /**
     * @return восстановить состояние из снэпшота
     */

    public void restore(AtmMemento memento) {
        this.cells = memento.cells();
    }

    /**
     * @return уникальный идентификатор банкомата
     */
    public Long id() {
        return id;
    }

    /**
     * @return ячейки с купюрами
     */
    public List<Cell> cells() {
        return cells;
    }
}
