package com.codoon.common.model.drycargo;

public class Common {
    /**
     * 此class文件定义了干货页面所有的通用元素定义
     */

    //各类Android class
    public static final String CLASSNAME_VIEW = "android.view.View";                     //Class of View
    public static final String CLASSNAME_VIEWGROUP = "android.view.ViewGroup";           //Class of ViewGroup
    public static final String CLASSNAME_IMAGEVIEW = "android.widget.ImageView";         //Class of ImageView
    public static final String CLASSNAME_TEXTVIEW = "android.widget.TextView";           //Class of TextView
    public static final String CLASSNAME_LINEARLAYOUT = "android.widget.LinearLayout";   //Class of LinearLayout
    public static final String CLASSNAME_HORIZONSCROLLVIEW = "android.widget.HorizontalScrollView";    //广告banner
    public static final String CLASSNAME_SCROLLVIEW = "android.widget.ScrollView";    //Class of ScrollView
    public static final String CLASSNAME_VIEWPAGER = "android.support.v4.view.ViewPager";    //广告banner


    /* ---------------------- Using ------------------------- */
//    private static String accountNickText = "烤羊肉串";
    private static String accountNickText = "伤心凉粉123";
    private static final String moreBtnText = "更多";

    //顶部栏
    public static final String SEARCH_INPUT_ID = "com.codoon.gps:id/searchbar_edit";  //搜索按钮
    public static final String SEARCH_BTN_ID = "com.codoon.gps:id/tv_search";         //搜索输入框
    public static final String SCAN_BTN_ID = "com.codoon.gps:id/iv_scan";             //扫一扫按钮

    //tab标签
    private static final String recommendTabText = "推荐";
    private static final String runTabText = "跑步";
    private static final String rideTabText = "骑行";
    private static final String fitnessTabText = "健身";
    private static final String personalTabText = "个性";

    //feed卡片
    public static final String CARD_TITLE_ID = "com.codoon.gps:id/tv_title";
    public static final String CARD_AUTHOR_ID = "com.codoon.gps:id/tv_article_author";
    public static final String ADDINFO_EDIT_ID = "com.codoon.gps:id/mSetAddInfo";
    public static final String SENDINFO_BTN_ID = "com.codoon.gps:id/mBtnSendInfo";
    public static final String ARTICLE_COLLECT_BTN_ID = "com.codoon.gps:id/textViewFav";
    public static final String USER_CARE_BTN_ID = "com.codoon.gps:id/user_center_guest_focus";
    public static final String USER_CARED_BTN_ID = "com.codoon.gps:id/user_center_guest_focus";
    private static final String viewAllInfoText = "查看全部评论";
    public static String followBtnText = "关注";
    public static String followedBtnText = "已关注";
    private static final String followedCannelText = "取消关注";

    //other
    public static final String TRAIN_DETAIL_NAME_ID = "com.codoon.gps:id/name";   //训练计划详情页 训练计划名称ID

    public static final String MORE_BTN_TEXT = "text(\""+moreBtnText+"\")";
    public static final String MYNICK_TEXT = "text(\""+accountNickText+"\")";
    public static final String VIEW_ALLINFO_INDETAIL = "//*[text()='"+viewAllInfoText+"']";
    public static final String FOLLOW_BTN_TEXT = "text(\""+followBtnText+"\")";
    public static final String FOLLOWED_BTN_TEXT = "text(\""+followedBtnText+"\")";
    public static final String FOLLOWED_CANNEL_TEXT = "text(\""+followedCannelText+"\")";
    public static final String DRYCARGO_RECOMMEND_TAB_TEXT = "text(\""+recommendTabText+"\")";
    public static final String DRYCARGO_RUN_TAB_TEXT = "text(\""+runTabText+"\")";
    public static final String DRYCARGO_RIDE_TAB_TEXT = "text(\""+rideTabText+"\")";
    public static final String DRYCARGO_FITNESS_TAB_TEXT = "text(\""+fitnessTabText+"\")";
    public static final String DRYCARGO_PERSON_TAB_TEXT = "text(\""+personalTabText+"\")";

}
