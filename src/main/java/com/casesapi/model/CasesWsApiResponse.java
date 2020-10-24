package com.casesapi.model;

import java.util.ArrayList;
import java.util.List;

public class CasesWsApiResponse {

    private List<List<CasesWsApiEntity>> cases;

    public CasesWsApiResponse() {
        cases = new ArrayList<>();
    }

    public CasesWsApiResponse(List<List<CasesWsApiEntity>> cases) {
        this.cases = cases;
    }

    public List<List<CasesWsApiEntity>> getCases() {
        return cases;
    }

    public void setCases(List<List<CasesWsApiEntity>> cases) {
        this.cases = cases;
    }
}
