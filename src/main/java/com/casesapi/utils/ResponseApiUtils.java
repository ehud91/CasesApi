package com.casesapi.utils;

public class ResponseApiUtils {

    /**
     * is Got Error from Response code
     * @param status int
     * @return boolean true/false
     * @access public
     */
    public static boolean isResponseStatusError(int status) {
        return (status == Const.HTTP_REQUEST_ERROR);
    }
}
