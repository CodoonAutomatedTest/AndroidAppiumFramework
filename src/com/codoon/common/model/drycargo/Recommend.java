package com.codoon.common.model.drycargo;

public class Recommend {
    private static final String dailtHotText = "今日热门";
    private static final String explanText = "训练计划";
    private static String equipReviewText = "装备测评";
    private static final String maybeLikeText = "猜你喜欢";

    /* --- Using --- */
    public static final String RECOMMEND_PAGEVIEWER_ID = "com.codoon.gps:id/view_pager";    //干货推荐页面显示区域
    public static final String RECOMMEND_DAILYHOT_NAME = "text(\""+dailtHotText+"\")";  //今日热门tag
    public static final String RECOMMEND_EXPLAN_NAME = "text(\""+explanText+"\")";  //训练计划tag
    public static final String EQUIP_REVIEW_TEXT = "text(\""+equipReviewText+"\")";  //装备评测tag
    public static final String MAYBE_LIKE_TEXT = "text(\""+maybeLikeText+"\")";  //猜你喜欢tag
}
