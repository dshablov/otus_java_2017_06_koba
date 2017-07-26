package ru.otus.domain.atm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtmDepartment {
    private List<Atm> atms;
    private Map<Long, AtmMemento> atmsInitialState;

    public AtmDepartment(List<Atm> atms) {
        this.atms = atms;
        atmsInitialState = new HashMap<>();
        saveState(atms);
    }

    public List<Atm> atms() {
        return atms;
    }

    public long totalRemain() {
        return atms.stream()
                .mapToLong(Atm::remain)
                .sum();
    }

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
