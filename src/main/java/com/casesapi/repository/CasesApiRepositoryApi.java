package com.casesapi.repository;

import com.casesapi.model.Cases;
import com.casesapi.model.CasesApiContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CasesApiRepositoryApi {

    private final static Logger LOGGER = LoggerFactory.getLogger(CasesApiRepositoryApi.class);

    @Autowired
    final private CasesApiRepository apiRepository;

    public CasesApiRepositoryApi(CasesApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    /**
     * Get all Cases from database
     * @param cac CasesApiContext
     * @return cases Cases
     * @access public
     */
    public List<Cases> findAllCases(CasesApiContext cac) {
        return apiRepository.findAllCases(cac);
    }

    /**
     * Insert Cases into database service
     * @param cac CasesApiContext
     * @access public
     */
    public void insertCases(CasesApiContext cac) {
        apiRepository.insertCases(cac);
    }
}
