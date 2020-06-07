package ekar.test.bo;

import ekar.test.TypesOfThreads;
import ekar.test.service.CounterService;
import ekar.test.util.Utility;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterBO implements Runnable{

    private TypesOfThreads thread;
    private AtomicInteger counter;
    private CounterService counterService;

    public CounterBO(TypesOfThreads typeOfThread, AtomicInteger counter, CounterService counterService){
        this.thread = typeOfThread;
        this.counter =  counter;
        this.counterService = counterService;
    }

    @Override
    public void run() {

        if(counter.get() <=Utility.LOWEST_VALUE || counter.get() >=Utility.HIGHEST_VALUE)
            return;

        if(TypesOfThreads.PRODUCER_THREAD.equals(thread))
            incrementInAtomicIntAndPrint(counter);
        else
            decrementInAtomicIntAndPrint(counter);

        synchronized (this){
            if(counter.get() == Utility.LOWEST_VALUE)
                counterService.saveAndDecrement(counter);
            else if( counter.get() == Utility.HIGHEST_VALUE )
                counterService.saveAndIncrement(counter);
        }

    }

    private void incrementInAtomicIntAndPrint(AtomicInteger counter){
        System.out.println(" previous value is "+counter+" The thread is producer and the value of counter is "+counter.incrementAndGet()+" the name of counter is "+Thread.currentThread().getName());
    }
    private void decrementInAtomicIntAndPrint(AtomicInteger counter){
        System.out.println(" previous value is "+counter+" The thread is consumer and the value of counter is "+counter.decrementAndGet()+" the name of counter is "+Thread.currentThread().getName());
    }
}
