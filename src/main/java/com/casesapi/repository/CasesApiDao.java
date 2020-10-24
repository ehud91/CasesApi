package com.casesapi.repository;

import com.casesapi.model.Cases;
import com.casesapi.model.CasesApiContext;

import java.util.List;

public interface CasesApiDao {

    List<Cases> findAllCases(CasesApiContext cac);

    void insertCases(CasesApiContext cac);

    void deleteAllCases(CasesApiContext cac);
}
