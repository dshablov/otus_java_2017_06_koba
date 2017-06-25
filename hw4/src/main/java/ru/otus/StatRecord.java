package ru.otus;

import java.time.LocalDateTime;

class StatRecord {
    private final LocalDateTime timestamp;
    private final long youngGenerationCalls;
    private final long oldGenerationCalls;
    private final long youngGenerationTimeToCollect;
    private final long oldGenerationTimeToCollect;

    public StatRecord(LocalDateTime timestamp, long youngGenerationCalls, long oldGenerationCalls, long youngGenerationTimeToCollect, long oldGenerationTimeToCollect) {
        this.timestamp = timestamp;
        this.youngGenerationCalls = youngGenerationCalls;
        this.oldGenerationCalls = oldGenerationCalls;
        this.youngGenerationTimeToCollect = youngGenerationTimeToCollect;
        this.oldGenerationTimeToCollect = oldGenerationTimeToCollect;
    }

    public LocalDateTime timestamp() {
        return this.timestamp;
    }

    public long youngGenerationCalls() {
        return this.youngGenerationCalls;
    }

    public long oldGenerationCalls() {
        return this.oldGenerationCalls;
    }

    public long youngGenerationTimeToCollect() {
        return this.youngGenerationTimeToCollect;
    }

    public long oldGenerationTimeToCollect() {
        return this.oldGenerationTimeToCollect;
    }
}
