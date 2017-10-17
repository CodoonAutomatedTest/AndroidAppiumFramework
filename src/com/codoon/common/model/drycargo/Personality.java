package com.codoon.common.model.drycargo;

public class Personality {
    private static String healthFashionText = "健康时尚";
    private static String outsideTripText = "户外旅行";
    private static String limitBrokenText = "竞技极限";
    private static String codoonGroupText = "咕咚运动团";
    private static String activitiesHotText = "热门活动";
    private static String triathlionText = "铁人三项";


    /* --- Using --- */
    public static final String SPECAIL_FASHION_NAME = "text(\""+healthFashionText+"\")";    //健康时尚tag
    public static final String SPECAIL_OUTSIDE_NAME = "text(\""+outsideTripText+"\")";      //户外旅行tag
    public static final String SPECAIL_EXTREME_NAME = "text(\""+limitBrokenText+"\")";      //竞技极限tag
    public static final String SPECAIL_GROUP_NAME = "text(\""+codoonGroupText+"\")";        //咕咚运动团tag
    public static final String SPECAIL_HOT_NAME = "text(\""+activitiesHotText+"\")";        //热门活动tag
    public static final String SPECAIL_TRIATHLION_NAME = "text(\""+triathlionText+"\")";    //铁人三项tag
}
