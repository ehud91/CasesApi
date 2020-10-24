package com.casesapi.utils;

import org.springframework.http.HttpStatus;

public class WsApiUtils {

    /**
     * Do we got Ok Response from service
     * @param status HttpStatus
     * @return boolean true/false
     * @access boolean true/false
     */
    public static boolean doGotOkResponseFromApiWs(HttpStatus status) {
        return (status.equals(HttpStatus.OK));
    }
}
