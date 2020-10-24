package com.casesapi.repository;

import com.casesapi.model.Cases;
import com.casesapi.model.CasesApiContext;
import com.casesapi.utils.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CasesApiRepository implements CasesApiDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(CasesApiRepository.class);

    @Autowired
    final private NamedParameterJdbcTemplate jdbcTemplate;

    public CasesApiRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Get All Cases in database
     * @param cac CasesApiContext
     * @return List<Cases> cases
     * @access public
     */
    @Override
    public List<Cases> findAllCases(CasesApiContext cac) {

        LOGGER.info("Get all cases from database");
        final String sql = cac.getApplicationProps().getProperty(Const.SQL_QUERY_FOR_CASES);
        List<Cases> cases = new ArrayList<>();
        try {
            LOGGER.info("Run sql query, sqlQuery={}", sql);
            cases = jdbcTemplate.query(sql, (rs, rowNum) ->
                    new Cases(
                            rs.getInt("case_id"),
                            rs.getInt("customer_id"),
                            rs.getInt("provider_id"),
                            rs.getInt("created_error_code"),
                            rs.getString("status").trim(),
                            rs.getDate("ticket_creation_date"),
                            rs.getDate("last_modified_date"),
                            rs.getString("product_name").trim()
                    ));
        } catch (Exception e) {
            LOGGER.error("Could not get results for cases from database, exception={}", e.getMessage());
        }
        LOGGER.info("Got results for cases from database, cases={}", cases);
        return cases;
    }

    /**
     * Insert all cases as list into database
     * @param cac CasesApiContext
     */
    @Override
    public void insertCases(CasesApiContext cac) {

        LOGGER.info("Insert all cases as list into database");
        final String sql = cac.getApplicationProps().getProperty(Const.SQL_QUERY_INSERT_ALL_CASES);
        List<Cases> cases = cac.getCases();
        try {
            this.deleteAllCases(cac);
            LOGGER.info("Run insert query into database, sqlQuery={}", sql);
            cases.stream().forEach(caseProps -> {
                KeyHolder holder = new GeneratedKeyHolder();
                SqlParameterSource queryParams = fillParams(caseProps);
                jdbcTemplate.update(sql, queryParams, holder);
            });
        } catch (Exception e) {
            LOGGER.error("Could not get cases list from database, exception={}, sqlQuery={}",
                    e.getMessage(), sql);
            e.printStackTrace();
        }
    }

    /**
     * Delete all cases in database
     * @param cac CasesApiContext
     */
    @Override
    public void deleteAllCases(CasesApiContext cac) {

        try {
            LOGGER.info("Delete all cases in database before adding new cases");
            final String sql = cac.getApplicationProps().getProperty(Const.SQL_QUERY_DELETE_ALL_CASES);
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(sql, null, holder);
        } catch (Exception e) {
            LOGGER.error("Could not delete all cases in database, exception={}", e.getMessage());
        }
    }

    /**
     * Build Sql parameters for insert cases into database
     * @param casesProps Cases
     * @return queryParams SqlParameterSource
     * @access private
     */
    private SqlParameterSource fillParams(Cases casesProps) {

        SqlParameterSource queryParams = new MapSqlParameterSource()
                .addValue("caseId", casesProps.getCaseId())
                .addValue("customerId", casesProps.getCustomerId())
                .addValue("providerId", casesProps.getProvider())
                .addValue("createdErrorCode", casesProps.getCreateErrorCode())
                .addValue("mStatus", casesProps.getStatus())
                .addValue("ticketCreationDate", casesProps.getTicketCreatedDate())
                .addValue("lastModifiedDate", casesProps.getLastModifiedDate())
                .addValue("productName", casesProps.getProductName());
        return queryParams;
    }


}
