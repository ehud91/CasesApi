package com.casesapi.caseswsapi;

import com.casesapi.model.Cases;
import com.casesapi.model.CasesApiContext;
import com.casesapi.model.CasesWsApiEntity;
import com.casesapi.utils.Const;
import com.casesapi.utils.GeneralUtils;
import com.casesapi.utils.WsApiUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Double.parseDouble;

public class CasesWsApi {

    private final static Logger LOGGER = LoggerFactory.getLogger(CasesWsApi.class);

    /**
     * Get Cases from a WS API
     * @param cac CasesApiContext
     * @return cases List<Cases>
     * @access public
     */
    public static List<Cases> getCasesFromWsApi(CasesApiContext cac) {

        List<Cases> cases = new ArrayList<>();
        try {
            String uri = cac.getApplicationProps().getProperty(Const.CASES_WS_API_URI_BANANA);
            LOGGER.info("Call external ws api, wsApiUri={}", uri);
            ResponseEntity<String> responseApi = cac.getRestTemplate().getForEntity(uri, String.class);
            if (!WsApiUtils.doGotOkResponseFromApiWs(responseApi.getStatusCode())) {
                LOGGER.error("Could not reach the external ws api, wsApiUri={}, responseStatusCode={}, responseBody={}",
                        uri, responseApi.getStatusCode(), responseApi.getBody());
                return cases;
            }
            List<CasesWsApiEntity> casesWsApi = parseJson(responseApi.getBody());
            casesWsApi.stream().forEach(caseProps -> {
                try {
                    Cases getCase = getSingleCaseFromApiResponse(caseProps);
                    cases.add(getCase);
                    LOGGER.info("Get case from WS API - case={}", getCase);
                } catch (Exception e) {
                    LOGGER.error("Could not collect a case from WS API, caseFromWsApi={}, exception={}",
                            caseProps, e.getMessage());
                }
            });
           return cases;
        } catch (Exception e) {
            LOGGER.error("Got exception from WS API - Could not reach the external ws api, exception={}",
                   e.getMessage());
            return cases;
        }
    }

    /**
     * Parse Json from API WS Response
     * @param json String
     * @return cases List<CasesWsApiEntity>
     * @access private
     */
    private static List<CasesWsApiEntity> parseJson(String json) {

        Gson gson = new Gson();
        List<CasesWsApiEntity> cases = new ArrayList<CasesWsApiEntity>();
        LOGGER.info("Parse json from string, jsonString={}", json);
        List[] result = gson.fromJson(json, List[].class);
        JsonArray jsonArray = gson.toJsonTree(result[0]).getAsJsonArray();
        for (final JsonElement jsonElement : jsonArray) {
            final JsonObject jsonObject = jsonElement.getAsJsonObject();
            CasesWsApiEntity caseEntity = new CasesWsApiEntity(
                    (int) parseDouble(jsonObject.get(Const.CASES_WS_API_FIELD_CASE_ID).toString()),
                    (int) parseDouble(jsonObject.get(Const.CASES_WS_API_FIELD_CUSTOMER_ID).toString()),
                    (int) parseDouble(jsonObject.get(Const.CASES_WS_API_FIELD_PROVIDER_ID).toString()),
                    (int) parseDouble(jsonObject.get(Const.CASES_WS_API_FIELD_CREATED_ERROR_CODE).toString()),
                    jsonObject.get(Const.CASES_WS_API_FIELD_STATUS).toString(),
                    jsonObject.get(Const.CASES_WS_API_FIELD_TICKET_CREATION_DATE).toString(),
                    jsonObject.get(Const.CASES_WS_API_FIELD_LAST_MODIFIED_DATE).toString(),
                    jsonObject.get(Const.CASES_WS_API_FIELD_PRODUCT_NAME).toString()
            );
            cases.add(caseEntity);
        }
        LOGGER.info("Got parser results from json string, cases={}", cases);
        return cases;
    }

    /**
     * Convert Single case from WS API to Case pojo object
     * @param caseProps CasesWsApiEntity
     * @return cases Cases
     * @access private
     */
    private static Cases getSingleCaseFromApiResponse(CasesWsApiEntity caseProps) {

        return new Cases(
                caseProps.getCaseID(),
                caseProps.getCustomerID(),
                caseProps.getProvider(),
                caseProps.getCREATED_ERROR_CODE(),
                GeneralUtils.removeSlashes(caseProps.getSTATUS()).trim(),
                new Date(GeneralUtils.removeSlashes(caseProps.getTICKET_CREATION_DATE()).trim()),
                new Date(GeneralUtils.removeSlashes(caseProps.getLAST_MODIFIED_DATE()).trim()),
                GeneralUtils.removeSlashes(caseProps.getPRODUCT_NAME()).trim()
        );
    }
}
