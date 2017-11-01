package com.codoon.common.model.mine;

public class History {

    //Page Text
    private static final String chartText = "统计";
    private static final String weekText = "周";
    private static final String monthText = "月";
    private static final String yearText = "年";
    private static final String allText = "总";

    /*------------------------------------ Using --------------------------------------*/
    /*
     *  The value of the "uiautomator" attribute to search for
     *  eg:
     *      driver.findElementByAndroiduiautomator(@value)
     *
     */
    public static final String MY_SPORTSDATA_STAT_TEXT = "text(\""+chartText+"\")";
    public static final String MY_SPORTSDATA_WEEK_TEXT = "text(\""+weekText+"\")";
    public static final String MY_SPORTSDATA_MONTH_TEXT = "text(\""+monthText+"\")";
    public static final String MY_SPORTSDATA_YEAR_TEXT = "text(\""+yearText+"\")";
    public static final String MY_SPORTSDATA_ALL_TEXT = "text(\""+allText+"\")";
    public static final String MY_SPORTSSTAT_TITLE_ID = "com.codoon.gps:id/textViewTitle";
    public static final String MY_SPORTS_VALUE_ID = "com.codoon.gps:id/textViewDistance";
}
