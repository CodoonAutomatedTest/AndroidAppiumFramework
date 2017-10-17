package com.codoon.common.model.drycargo;

public class Ride {
    private static String rideTipsText = "骑行知识";
    private static String rideTopicText = "骑行话题";
    private static String rideNewsText = "骑行资讯";
    private static String rideTalentText = "骑行达人";
    private static String rideColumnText = "骑行专题";
    private static String rideTripText = "单车旅行";


    /*---------------- using ------------------ */
    public static final String RIDE_KNOWLEDGE_NAME = "text(\""+rideTipsText+"\")";  //骑行知识tag
    public static final String RIDE_TOPIC_NAME = "text(\""+rideTopicText+"\")";     //骑行话题tag
    public static final String RIDE_NEWS_NAME = "text(\""+rideNewsText+"\")";       //骑行资讯tag
    public static final String RIDE_TALENT_NAME = "text(\""+rideTalentText+"\")";   //骑行达人tag
    public static final String RIDE_COLUMN_NAME = "text(\""+rideColumnText+"\")";   //骑行专题tag
    public static final String RIDE_TRAVEL_NAME = "text(\""+rideTripText+"\")";     //单车旅行tag
}
