package ru.otus.domain;

import org.junit.Test;
import ru.otus.domain.atm.Atm;
import ru.otus.domain.atm.Cell;
import ru.otus.domain.withdraw.WithdrawStrategyType;
import ru.otus.domain.withdraw.exception.NotEnoughMoneyException;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;

/**
 * User: Vladimir Koba
 * Date: 24.07.2017
 * Time: 23:56
 */
public class AtmTest {

    @Test
    public void take300RublesWithSmallFirstStrategy() {
        Atm atm = new Atm(1L, asList(
                new Cell(500, 5),
                new Cell(100, 5))
        );
        atm.withdraw(300, WithdrawStrategyType.SMALL_FIRST_STRATEGY);
        assertTrue("First cell is not change", cellWith500(atm) == 5);
        assertTrue("Second cell took 3 banknote", cellWith100(atm) == 2);
    }

    @Test
    public void take500RublesWithSmallFirstStrategy() {
        Atm atm = new Atm(1L, asList(
                new Cell(500, 5),
                new Cell(100, 5))
        );
        atm.withdraw(500, WithdrawStrategyType.SMALL_FIRST_STRATEGY);
        assertTrue("First cell is not change", cellWith500(atm) == 5);
        assertTrue("Second cell took 5 banknote", cellWith100(atm) == 0);
    }

    @Test
    public void take600RublesWithSmallFirstStrategy() {
        Atm atm = new Atm(1L, asList(
                new Cell(500, 5),
                new Cell(100, 5))
        );
        atm.withdraw(600, WithdrawStrategyType.SMALL_FIRST_STRATEGY);
        assertTrue("First cell took 1 banknote", cellWith500(atm) == 4);
        assertTrue("Second cell took 1 banknote", cellWith100(atm) == 4);
    }

    @Test
    public void take1100RublesWithLargeFirstStrategy() {
        Atm atm = new Atm(1L, asList(
                new Cell(500, 5),
                new Cell(100, 5))
        );
        atm.withdraw(1100, WithdrawStrategyType.LARGE_FIRST_STRATEGY);
        assertTrue("First cell took 2 banknote", cellWith500(atm) == 3);
        assertTrue("Second cell took 1 banknote", cellWith100(atm) == 4);
    }

    @Test
    public void take500RublesWithLargeFirstStrategy() {
        Atm atm = new Atm(1L, asList(
                new Cell(500, 5),
                new Cell(100, 5))
        );
        atm.withdraw(500, WithdrawStrategyType.LARGE_FIRST_STRATEGY);
        assertTrue("First cell took 1 banknote", cellWith500(atm) == 4);
        assertTrue("Second cell is not change", cellWith100(atm) == 5);
    }

    @Test
    public void take600RublesWithLargeFirstStrategy() {
        Atm atm = new Atm(1L,asList(
                new Cell(500, 5),
                new Cell(100, 5))
        );
        atm.withdraw(600, WithdrawStrategyType.LARGE_FIRST_STRATEGY);
        assertTrue("First cell took 1 banknote", cellWith500(atm) == 4);
        assertTrue("Second cell took 1 banknote", cellWith100(atm) == 4);
    }

    @Test
    public void takeVeryLargeAmount() {
        Atm atm = new Atm(1L,asList(
                new Cell(500, 5),
                new Cell(100, 5))
        );
        try {
            atm.withdraw(20000, WithdrawStrategyType.LARGE_FIRST_STRATEGY);
        } catch (NotEnoughMoneyException e) {
            System.out.println();
        }
        assertTrue("First cell is not change", cellWith500(atm) == 5);
        assertTrue("Second cell is not change", cellWith100(atm) == 5);
    }

    private Integer cellWith100(Atm atm) {
        return atm.cells().stream()
                .filter(cell -> cell.cellNominal() == 100)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Unknown nominal"))
                .banknoteCount();
    }

    private Integer cellWith500(Atm atm) {
        return atm.cells().stream()
                .filter(cell -> cell.cellNominal() == 500)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Unknown nominal"))
                .banknoteCount();
    }

}