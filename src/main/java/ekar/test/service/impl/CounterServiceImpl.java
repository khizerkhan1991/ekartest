package ekar.test.service.impl;

import ekar.test.TypesOfThreads;
import ekar.test.bo.CounterBO;
import ekar.test.model.CountTable;
import ekar.test.repository.CountTableRepository;
import ekar.test.service.CounterService;
import ekar.test.service.RequestService;
import ekar.test.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CounterServiceImpl implements CounterService {
    
    private static final String COUNTER = "Counter";

    @Autowired
    private CountTableRepository countTableRepository;
    @Autowired
    private RequestService requestService;

    @Override
    public void setCounter(Integer counter) {

        requestService.creatAndSaveRequest(COUNTER, counter.toString());

        Optional<CountTable> countTableOptional = countTableRepository.findById(Utility.FIRST_ID);

        CountTable countTable = new CountTable();
        if(countTableOptional.isPresent())
            countTable = countTableOptional.get();

        countTable.setCount(counter);

        countTableRepository.save(countTable);
    }

    @Override
    public void updateCounter(Integer numberOfProducerThread, Integer numberOfConsumerThread) {

        requestService.creatAndSaveRequest(TypesOfThreads.PRODUCER_THREAD.getCode(), numberOfProducerThread.toString(),
                TypesOfThreads.CONSUMER_THREAD.getCode(), numberOfConsumerThread.toString());

        AtomicInteger atomicCounter = new AtomicInteger(countTableRepository.getOne(Utility.FIRST_ID).getCount());
        int totalNumberOfThread = numberOfProducerThread + numberOfConsumerThread;
        for (int i=1; i<= totalNumberOfThread; i++){

            if( i<=numberOfProducerThread ){
               new Thread(new CounterBO(TypesOfThreads.PRODUCER_THREAD, atomicCounter, this)).start();
                continue;
            }

            new Thread(new CounterBO(TypesOfThreads.CONSUMER_THREAD, atomicCounter, this)).start();
        }
    }

    @Override
    public  void saveAndDecrement(AtomicInteger counter) {

        if(counter.get() != Utility.LOWEST_VALUE)
            return;

        CountTable countTable = new CountTable(counter.getAndDecrement());
        countTableRepository.save(countTable);
    }
    @Override
    public void saveAndIncrement(AtomicInteger counter) {

        if(counter.get() != Utility.HIGHEST_VALUE)
            return;

        CountTable countTable = new CountTable(counter.getAndIncrement());
        countTableRepository.save(countTable);
    }
}
