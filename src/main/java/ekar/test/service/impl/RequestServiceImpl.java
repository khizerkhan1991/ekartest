package ekar.test.service.impl;

import ekar.test.model.RequestInfo;
import ekar.test.repository.RequestInfoRepository;
import ekar.test.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestInfoRepository requestInfoRepository;
    @Override
    public void creatAndSaveRequest(String... keyAndValues) {

        List<String> keyAndValueList = new ArrayList<>(Arrays.asList(keyAndValues));

        RequestInfo requestInfo = new RequestInfo(keyAndValueList.toString());
        requestInfoRepository.save(requestInfo);
    }
}
