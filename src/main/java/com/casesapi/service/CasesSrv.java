package com.casesapi.service;

import com.casesapi.logic.CasesLogic;
import com.casesapi.model.AppResponse;
import com.casesapi.model.Cases;
import com.casesapi.model.CasesApiContext;
import com.casesapi.repository.CasesApiRepository;
import com.casesapi.repository.CasesApiRepositoryApi;
import com.casesapi.utils.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

public class CasesSrv {

    private final static Logger LOGGER = LoggerFactory.getLogger(CasesSrv.class);
    private static CasesApiContext cac = new CasesApiContext();

    /**
     * Service Get all cases
     * @param env Environment
     * @param repository CasesApiRepositoryApi
     * @param restTemplate RestTemplate
     * @param appSession HttpSession
     * @return appResponse AppResponse
     * @access public
     */
    public static AppResponse getAllCases(Environment env, CasesApiRepositoryApi repository, RestTemplate restTemplate, HttpSession appSession) {

        AppResponse appResponse;
        UUID uuid = UUID.randomUUID();
        LOGGER.info("Start Get All Cases Service, uuid={}", uuid.toString());
        try {
            cac = CasesLogic.initAppContext(uuid, env, repository, restTemplate, appSession);
            cac = CasesLogic.runLogic();
            List<Cases> cases = cac.getCases();
            appResponse = new AppResponse(uuid.toString(),
                    Const.HTTP_STATUS_OK, "Get all cases service success", cases);
        } catch (Exception e) {
            LOGGER.info("Got Exception from - Get All Cases Service, uuid={}, exception={}",
                    uuid.toString(), e.getMessage());
            appResponse = new AppResponse(uuid.toString(),
                    Const.HTTP_REQUEST_ERROR, "Internal error occured", null);
        }
        LOGGER.info("End Get All Cases Service, uuid={}", uuid.toString());
        return appResponse;
    }
}
