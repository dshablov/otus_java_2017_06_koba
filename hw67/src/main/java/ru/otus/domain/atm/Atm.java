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

    public long remain() {
        return cells.stream()
                .mapToLong(Cell::remain)
                .sum();
    }

    public AtmMemento saveState() {
        List<Cell> newCells = new ArrayList<>();
        for (Cell cell : cells) {
            newCells.add(new Cell(cell));
        }
        return new AtmMemento(newCells);
    }

    public void restore(AtmMemento memento) {
        this.cells = memento.cells();
    }

    public Long id() {
        return id;
    }

    public List<Cell> cells() {
        return cells;
    }
}
