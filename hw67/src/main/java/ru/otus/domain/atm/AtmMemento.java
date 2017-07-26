package ru.otus.domain.atm;

import java.util.List;

/**
 * User: Vladimir Koba
 * Date: 26.07.2017
 * Time: 23:03
 */

/**
 * Снимок состояния банкомата
 */
public class AtmMemento {
    private final List<Cell> cells;

    public AtmMemento(List<Cell> cells) {
        this.cells = cells;
    }


    public List<Cell> cells() {
        return cells;
    }
}
