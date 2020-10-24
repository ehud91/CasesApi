package com.casesapi.model;

import com.casesapi.utils.GeneralUtils;

import java.util.HashMap;
import java.util.Map;

public class CasesWsApiEntity {

    private int CaseID;
    private int CustomerID;
    private int Provider;
    private int CREATED_ERROR_CODE;
    private String STATUS;
    private String TICKET_CREATION_DATE;
    private String LAST_MODIFIED_DATE;
    private String PRODUCT_NAME;

    public CasesWsApiEntity(int caseID, int customerID, int provider, int CREATED_ERROR_CODE, String STATUS, String TICKET_CREATION_DATE, String LAST_MODIFIED_DATE, String PRODUCT_NAME) {
        CaseID = caseID;
        CustomerID = customerID;
        Provider = provider;
        this.CREATED_ERROR_CODE = CREATED_ERROR_CODE;
        this.STATUS = STATUS;
        this.TICKET_CREATION_DATE = TICKET_CREATION_DATE;
        this.LAST_MODIFIED_DATE = LAST_MODIFIED_DATE;
        this.PRODUCT_NAME = PRODUCT_NAME;
    }

    public int getCaseID() {
        return CaseID;
    }

    public void setCaseID(int caseID) {
        CaseID = caseID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public int getProvider() {
        return Provider;
    }

    public void setProvider(int provider) {
        Provider = provider;
    }

    public int getCREATED_ERROR_CODE() {
        return CREATED_ERROR_CODE;
    }

    public void setCREATED_ERROR_CODE(int CREATED_ERROR_CODE) {
        this.CREATED_ERROR_CODE = CREATED_ERROR_CODE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getTICKET_CREATION_DATE() {
        return TICKET_CREATION_DATE;
    }

    public void setTICKET_CREATION_DATE(String TICKET_CREATION_DATE) {
        this.TICKET_CREATION_DATE = TICKET_CREATION_DATE;
    }

    public String getLAST_MODIFIED_DATE() {
        return LAST_MODIFIED_DATE;
    }

    public void setLAST_MODIFIED_DATE(String LAST_MODIFIED_DATE) {
        this.LAST_MODIFIED_DATE = LAST_MODIFIED_DATE;
    }

    public String getPRODUCT_NAME() {
        return PRODUCT_NAME;
    }

    public void setPRODUCT_NAME(String PRODUCT_NAME) {
        this.PRODUCT_NAME = PRODUCT_NAME;
    }

    @Override
    public String toString() {

        Map<String, String> casesAttributes = new HashMap<>();
        casesAttributes.put("CaseID", String.valueOf(CaseID));
        casesAttributes.put("CustomerID", String.valueOf(CustomerID));
        casesAttributes.put("Provider", String.valueOf(Provider));
        casesAttributes.put("CREATED_ERROR_CODE", String.valueOf(CREATED_ERROR_CODE));
        casesAttributes.put("STATUS", String.valueOf(STATUS));
        casesAttributes.put("TICKET_CREATION_DATE", String.valueOf(TICKET_CREATION_DATE));
        casesAttributes.put("LAST_MODIFIED_DATE", String.valueOf(LAST_MODIFIED_DATE));
        casesAttributes.put("PRODUCT_NAME", String.valueOf(PRODUCT_NAME));
        return GeneralUtils.parseToJson(casesAttributes);
    }
}
