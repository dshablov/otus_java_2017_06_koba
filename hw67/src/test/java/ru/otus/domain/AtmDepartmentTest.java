package ru.otus.domain;

import org.junit.Test;
import ru.otus.domain.atm.Atm;
import ru.otus.domain.atm.AtmDepartment;
import ru.otus.domain.atm.Cell;
import ru.otus.domain.withdraw.WithdrawStrategyType;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;

/**
 * User: Vladimir Koba
 * Date: 26.07.2017
 * Time: 23:19
 */
public class AtmDepartmentTest {


    @Test
    public void totalRemainForTwoAtms() {
        AtmDepartment atmDepartment = new AtmDepartment(
                asList(
                        new Atm(1L, asList(
                                new Cell(100, 5),
                                new Cell(500, 5)
                        )),
                        new Atm(2L, asList(
                                new Cell(500, 2),
                                new Cell(100, 1)
                        ))
                )
        );
        assertTrue("Total remain is 4100", atmDepartment.totalRemain() == 4100);
    }

    @Test
    public void restoreAtms() {
        AtmDepartment atmDepartment = new AtmDepartment(
                asList(
                        new Atm(1L, asList(
                                new Cell(100, 5),
                                new Cell(500, 5)
                        )),
                        new Atm(2L, asList(
                                new Cell(500, 2),
                                new Cell(100, 1)
                        ))
                )
        );
        atmDepartment.atms().get(0).withdraw(100, WithdrawStrategyType.SMALL_FIRST_STRATEGY);
        atmDepartment.atms().get(1).withdraw(500, WithdrawStrategyType.LARGE_FIRST_STRATEGY);
        assertTrue("Total remain is 3500", atmDepartment.totalRemain() == 3500);
        atmDepartment.reset();
        assertTrue("Total remain is 4100", atmDepartment.totalRemain() == 4100);

    }

}