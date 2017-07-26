package ru.otus.domain.atm;

public class Cell {
    private Integer cellNominal;
    private Integer count;

    public Cell(Integer cellNominal, Integer count) {
        this.cellNominal = cellNominal;
        this.count = count;
    }

    public Cell(Cell cell) {
        this.cellNominal = cell.cellNominal;
        this.count = cell.count;
    }


    public boolean couldTakeBanknote(long checkingSum) {
        return checkingSum > cellNominal;
    }

    public long takeBanknote() {
        count--;
        return cellNominal;
    }


    public Integer remain() {
        return cellNominal * count;
    }


    public Integer cellNominal() {
        return cellNominal;
    }

    public Integer banknoteCount() {
        return count;
    }
}
