package ru.otus.domain.atm;


/**
 * Ячейка банкомата
 */
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


    /**
     * @return выдать банкноту из ячейки
     */
    public long takeBanknote() {
        count--;
        return cellNominal;
    }


    /**
     * @return Остаток по ячейке
     */
    public Integer remain() {
        return cellNominal * count;
    }


    /**
     *
     * @return номинал ячейки
     */
    public Integer cellNominal() {
        return cellNominal;
    }


    /**
     *
     * @return количество банкнот
     */

    public Integer banknoteCount() {
        return count;
    }
}
