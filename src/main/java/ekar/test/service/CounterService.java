package ekar.test.service;

import java.util.concurrent.atomic.AtomicInteger;

public interface CounterService {
    void setCounter(Integer counter);
    void updateCounter(Integer producerThread, Integer consumerThread);
    void saveAndIncrement(AtomicInteger counter);
    void saveAndDecrement(AtomicInteger counter);
}

