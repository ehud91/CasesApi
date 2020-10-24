package com.casesapi.model;

import com.casesapi.repository.CasesApiRepositoryApi;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.List;

public class CasesApiContext {

    private String uuid;
    private Environment applicationProps;
    private CasesApiRepositoryApi casesApiRepository;
    private RestTemplate restTemplate;
    private List<Cases> cases;
    private long lastExternalApiCalled;
    private HttpSession appSession;

    public CasesApiContext() { super(); }

    public CasesApiContext(String uuid, Environment applicationProps) {
        this.uuid = uuid;
        this.applicationProps = applicationProps;
    }

    public void setCasesApiContext(String uuid, Environment applicationProps, CasesApiRepositoryApi casesApiRepository, RestTemplate restTemplate, HttpSession appSession) {
        this.uuid = uuid;
        this.applicationProps = applicationProps;
        this.casesApiRepository = casesApiRepository;
        this.restTemplate = restTemplate;
        this.lastExternalApiCalled = 0;
        this.appSession = appSession;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Environment getApplicationProps() {
        return applicationProps;
    }

    public void setApplicationProps(Environment applicationProps) {
        this.applicationProps = applicationProps;
    }

    public CasesApiRepositoryApi getCasesApiRepository() {
        return casesApiRepository;
    }

    public void setCasesApiRepository(CasesApiRepositoryApi casesApiRepository) {
        this.casesApiRepository = casesApiRepository;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Cases> getCases() {
        return cases;
    }

    public void setCases(List<Cases> cases) {
        this.cases = cases;
    }

    public long getLastExternalApiCalled() {
        return lastExternalApiCalled;
    }

    public void setLastExternalApiCalled(long lastExternalApiCalled) {
        this.lastExternalApiCalled = lastExternalApiCalled;
    }

    public HttpSession getAppSession() {
        return appSession;
    }

    public void setAppSession(HttpSession appSession) {
        this.appSession = appSession;
    }
}
