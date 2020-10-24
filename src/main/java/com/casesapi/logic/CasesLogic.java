package com.casesapi.logic;

import com.casesapi.caseswsapi.CasesWsApi;
import com.casesapi.model.Cases;
import com.casesapi.model.CasesApiContext;
import com.casesapi.repository.CasesApiRepositoryApi;
import com.casesapi.utils.Const;
import com.casesapi.utils.GeneralUtils;
import com.casesapi.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

public class CasesLogic {

    private final static Logger LOGGER = LoggerFactory.getLogger(CasesLogic.class);
    final private static CasesApiContext cac = new CasesApiContext();

    /**
     * Initialize Application context
     * @param uuid UUID
     * @param env Environment
     * @param repository CasesApiRepositoryApi
     * @param restTemplate RestTemplate
     * @param appSession HttpSession
     * @return cac CasesApiContext
     * @access public
     */
    public static CasesApiContext initAppContext(UUID uuid,
                                                 Environment env,
                                                 CasesApiRepositoryApi repository,
                                                 RestTemplate restTemplate,
                                                 HttpSession appSession) {

        LOGGER.info("Initialize application context");
        cac.setCasesApiContext(uuid.toString(), env, repository, restTemplate, appSession);
        return cac;
    }

    /**
     * Run the application business logic
     * @return cac CasesApiContext
     * @access public
     */
    public static CasesApiContext runLogic() {
        getAllCases();
        return cac;
    }

    /**
     * Get all Cases from - WS API / Local Database
     * @access public
     */
    public static void getAllCases() {

        LOGGER.info("Run logic - Get all Cases from - WS API / Local Database");
        if(isLastExternalApiCallLessThenLimit()) {
            cac.getAppSession().setAttribute(Const.SESSION_KEY_LAST_EXTERNAL_API_CALLED, System.currentTimeMillis());
            getAllCasesFromWsApi();
            LOGGER.info("Get all Cases from - WS API");
        } else {
            getAllCasesFromLocalDb();
            LOGGER.info("Get all Cases from Local Database");
        }
    }

    /**
     * Get all cases from WS API
     * @access public
     */
    public static void getAllCasesFromWsApi() {
        List<Cases> cases;
        cac.setLastExternalApiCalled(System.currentTimeMillis());
        cases = CasesWsApi.getCasesFromWsApi(cac);
        cac.setCases(cases);
        cac.getCasesApiRepository().insertCases(cac);
    }

    /**
     * Get all cases from Local Database
     * @access public
     */
    public static void getAllCasesFromLocalDb() {
        List<Cases> cases;
        cases = cac.getCasesApiRepository().findAllCases(cac);
        cac.setCases(cases);
    }

    /**
     * Check if passed the maximum limit time from the last WS API call
     * @return boolean true/false
     * @access private
     */
    private static boolean isLastExternalApiCallLessThenLimit() {
        return (GeneralUtils.isEmpty(cac.getAppSession().getAttribute(Const.SESSION_KEY_LAST_EXTERNAL_API_CALLED)) ||
                TimeUtils.isPassedThirtyMinutes((long) cac.getAppSession().getAttribute(Const.SESSION_KEY_LAST_EXTERNAL_API_CALLED)));
    }
}
