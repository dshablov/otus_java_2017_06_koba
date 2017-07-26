package ru.otus.domain.atm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Сервис управления банкоматами
 */
public class AtmDepartment {
    private List<Atm> atms;
    private Map<Long, AtmMemento> atmsInitialState;

    public AtmDepartment(List<Atm> atms) {
        this.atms = atms;
        atmsInitialState = new HashMap<>();
        saveState(atms);
    }


    /**
     *
     * @return список банкоматов
     */
    public List<Atm> atms() {
        return atms;
    }


    /**
     * @return остаток по всем банкоматам
     */
    public long totalRemain() {
        return atms.stream()
                .mapToLong(Atm::remain)
                .sum();
    }

    /**
     * Сбросить все банкоматы в начальное состояние
     */
    public void reset() {
        for (Atm atm : atms) {
            atm.restore(atmsInitialState.get(atm.id()));
        }
    }

    private void saveState(List<Atm> atms) {
        for (Atm atm : atms) {
            atmsInitialState.put(atm.id(), atm.saveState());
        }
    }


}
