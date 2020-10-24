package com.casesapi.controller;

import com.casesapi.model.AppResponse;
import com.casesapi.repository.CasesApiRepositoryApi;
import com.casesapi.service.CasesSrv;
import com.casesapi.utils.Const;
import com.casesapi.utils.ResponseApiUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

@RestController
@ComponentScan(basePackages = "com.casesapi.*")
@EntityScan("com.casesapi.*")
@RequestMapping("/api")
@PropertySource("classpath:application.properties")
@EnableAutoConfiguration
public class CasesApi {

    private final static Logger LOGGER = LoggerFactory.getLogger(CasesApi.class);

    @Autowired
    private Environment env;

    @Autowired
    private CasesApiRepositoryApi casesApiRepositoryApi;

    @Autowired
    private RestTemplate restTemplate;

    @GET
    @GetMapping("/getAllCasesApi")
    @Produces("application/json; charset=UTF-8")
    @Operation(summary = "Get All Cases from external Api")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Got AppResponse response", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AppResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid AppResponse", content = @Content),
            @ApiResponse(responseCode = "404", description = "AppResponse not found", content = @Content)})
    public ResponseEntity<AppResponse> getAllCasesApi(HttpSession appSession) {

        LOGGER.info(Const.APPLICATION_LOG_SEPARATOR);
        AppResponse appResponse = CasesSrv.getAllCases(env, casesApiRepositoryApi, restTemplate, appSession);
        if(ResponseApiUtils.isResponseStatusError(appResponse.getStatus())) {
            LOGGER.info(Const.APPLICATION_LOG_SEPARATOR);
            return new ResponseEntity<AppResponse>(appResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info(Const.APPLICATION_LOG_SEPARATOR);
        return new ResponseEntity<AppResponse>(appResponse, HttpStatus.OK);
    }
}
