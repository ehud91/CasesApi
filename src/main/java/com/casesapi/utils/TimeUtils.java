package com.casesapi.utils;

public class TimeUtils {

    /**
     * Check if passed thirty minutes limit time to call ws api
     * @param searchTimestamp long
     * @return boolean true/false
     * @access public
     */
    public static boolean isPassedThirtyMinutes(long searchTimestamp) {
        long thirtyMinutesAgo = System.currentTimeMillis() - Const.THIRTY_MINUTES;
        return (searchTimestamp < thirtyMinutesAgo);
    }


}
