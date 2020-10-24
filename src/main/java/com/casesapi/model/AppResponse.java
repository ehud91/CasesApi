package com.casesapi.model;

import com.casesapi.utils.GeneralUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppResponse {

    private String requestUuid;
    private int status;
    private String desc;
    private List<Cases> cases;

    public AppResponse() {}

    public AppResponse(String requestUuid, int status, String desc, List<Cases> cases) {
        this.requestUuid = requestUuid;
        this.status = status;
        this.desc = desc;
        this.cases = cases;
    }

    public String getRequestUuid() {
        return requestUuid;
    }

    public void setRequestUuid(String requestUuid) {
        this.requestUuid = requestUuid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Cases> getCases() {
        return cases;
    }

    public void setCases(List<Cases> cases) {
        this.cases = cases;
    }

    @Override
    public String toString() {

        Map<String, String> appResponseAttr = new HashMap<>();
        appResponseAttr.put("requestUuid", String.valueOf(requestUuid));
        appResponseAttr.put("status", String.valueOf(status));
        appResponseAttr.put("desc", String.valueOf(desc));
        appResponseAttr.put("cases", String.valueOf(cases));
        return GeneralUtils.parseToJson(appResponseAttr);
    }
}
