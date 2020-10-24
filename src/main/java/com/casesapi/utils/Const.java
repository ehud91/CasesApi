package com.casesapi.utils;

public class Const {

    // Application statuses
    public final static int HTTP_STATUS_OK = 200;
    public final static int HTTP_BAD_REQUEST = 400;
    public final static int HTTP_REQUEST_ERROR = 500;

    // Determines the timeout in milliseconds until a connection is established.
    public static final int CLIENT_API_CONNECT_TIMEOUT = 30000;

    // The timeout when requesting a connection from the connection manager.
    public static final int CLIENT_API_REQUEST_TIMEOUT = 30000;

    // Refresh results after 30 minutes
    public static final int THIRTY_MINUTES = 30 * 60 * 1000;

    // Properties cases api paths
    public static final String CASES_WS_API_URI_BANANA = "api.cases.path-banana";
    public static final String CASES_WS_API_URI_STRAWBERRY = "api.cases.path-strawberry";

    // Properties Sql queries
    public static final String SQL_QUERY_FOR_CASES = "sql.query.get-cases";
    public static final String SQL_QUERY_INSERT_ALL_CASES = "sql.query.insert-cases";
    public static final String SQL_QUERY_DELETE_ALL_CASES = "sql.query.delete-cases";

    // Properties Sql query fields
    public static final String CASES_WS_API_FIELD_CASE_ID = "CaseID";
    public static final String CASES_WS_API_FIELD_CUSTOMER_ID = "CustomerID";
    public static final String CASES_WS_API_FIELD_PROVIDER_ID = "Provider";
    public static final String CASES_WS_API_FIELD_CREATED_ERROR_CODE = "CREATED_ERROR_CODE";
    public static final String CASES_WS_API_FIELD_STATUS = "STATUS";
    public static final String CASES_WS_API_FIELD_TICKET_CREATION_DATE = "TICKET_CREATION_DATE";
    public static final String CASES_WS_API_FIELD_LAST_MODIFIED_DATE = "LAST_MODIFIED_DATE";
    public static final String CASES_WS_API_FIELD_PRODUCT_NAME = "PRODUCT_NAME";

    public static final String SESSION_KEY_LAST_EXTERNAL_API_CALLED = "LAST_EXTERNAL_API_CALLED";

    // General Consts
    public static final String APPLICATION_LOG_SEPARATOR = "****************************************************";
}
