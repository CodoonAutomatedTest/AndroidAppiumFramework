package com.codoon.common.model.sportcircle;

public class Focus {
    private static final String accountSuggText = "推荐用户";
    private static String nearbyMoreTitleText = "附近";
    private static final String nearbyFeedText = "附近动态";
    private static final String feedSendinfoText = "发送";
    private static String shareWithWechatText = "微信好友";
    private static String shareWithWecircleText = "微信朋友圈";
    private static final String feedReportText = "举报";
    private static final String feedReportReason1Text = "色情或违法信息";
    private static final String feedReportReason2Text = "广告信息";
    private static final String feedReportReason3Text = "骚扰谩骂";
    private static final String feedReportReason4Text = "其他";

    /* --------------------------------- Using ---------------------------------------- */
    /*
     * feed流页
     */
    public static final String FEED_HEAD_ID = "com.codoon.gps:id/head_img_icon";  //feed头像
    public static final String FEED_CONTENT_ID = "com.codoon.gps:id/tv_content";  //feed内容
    public static final String FEED_NICK_ID = "com.codoon.gps:id/tv_name";		//feed昵称
    public static final String FEED_TIME_ID = "com.codoon.gps:id/tv_time";		//feed时间
    public static final String FEED_LIKE_BTN_ID = "com.codoon.gps:id/btn_like";   //feed点赞
    public static final String FEED_COMMENT_BTN_ID = "com.codoon.gps:id/btn_comment";   //feed评论
    public static final String FEED_SHARE_BTN_ID = "com.codoon.gps:id/btn_share";   //feed分享
    public static final String FEEDMORE_BTN_ID = "com.codoon.gps:id/btn_more";   //feed举报
    public static final String FEED_LINK_ID = "com.codoon.gps:id/ll_link";   //feed链接
    public static final String FEED_CARD_ID = "com.codoon.gps:id/feed_card"; //feed卡片
    public static final String FEED_VIDEO_ID = "com.codoon.gps:id/feed_video"; //视频feed
    public static final String FEED_LIKECOUNT_TEXT_ID = "com.codoon.gps:id/tv_like_count"; //feed点赞数
    public static final String FEED_COMMENTCOUNT_ID = "com.codoon.gps:id/tv_comment_count"; //feed评论数
    public static final String FEED_SUGGESTUSER_TAG_TEXT = "text(\""+accountSuggText+"\")";      //推荐用户
    public static final String FEED_NEARBY_ID = "com.codoon.gps:id/rl_recommend";
    public static final String FEED_NEARBY_MORETITLE_TEXT = "text(\""+nearbyMoreTitleText+"\")";
    public static final String FEED_NEARBY_TEXT = "text(\""+nearbyFeedText+"\")";
    public static final String OTHER_CHANGE_BTN_ID = "com.codoon.gps:id/tv_change";  //换一批
    public static final String USER_NICK_TEXT = "com.codoon.gps:id/tv_nick";  //换一批
    // feed详情
    public static final String FEED_DETAILS_PAGEVIEWER_ID = "com.codoon.gps:id/recyclerView";    //feed详情内容显示区
    public static final String FEED_DETAILS_COMMENTCOUNT_ID = "com.codoon.gps:id/tv_comment_count"; //feed详情页评论数
    public static final String FEED_DETAILS_COMMENT_ID = "com.codoon.gps:id/btn_comment";			  //feed详情页回复评论
    public static final String FEED_DETAILS_LIKEDETAILS_ID = "com.codoon.gps:id/btn_like_detail";    //详情页点赞详情
    public static final String FEED_DETAILS_LIKE_ID = "com.codoon.gps:id/btn_like";			  //feed详情页点赞评论
    public static final String FEED_DETAILS_LIKECOUNT_ID = "com.codoon.gps:id/tv_like_count";	  //feed详情页点赞评论数
    public static final String FEED_DETAILS_COMMENTEDIT_ID = "com.codoon.gps:id/conversation_sendinfo";  // feed评论输入框
    public static final String FEED_DETAILS_FEEDCOLLECT_ID = "com.codoon.gps:id/btn_collect";		// feed收藏
    public static final String FEED_DETAILS_SEEDBTN_NAME = "text(\""+feedSendinfoText+"\")";		// 发送评论
    public static final String FEED_DETAILS_EMO_BTN_ID = "com.codoon.gps:id/btnEmo";				//表情键盘按钮
    public static final String FEED_DETAILS_FEEDSHARE_ID = "com.codoon.gps:id/btn_share";    		// feed分享
    public static final String FEED_DETAILS_WECHATCIRCLE_TEXT = "text(\""+shareWithWecircleText+"\")";	// 微信朋友圈icon
    public static final String FEED_DETAILS_WECHAT_TEXT = "text(\""+shareWithWechatText+"\")";	// 微信好友icon
    public static final String FEED_DETAILS_MORE_ID = "com.codoon.gps:id/btn_more";				// feed详情页更多
    public static final String FEED_DETAILS_REPORT_TEXT = "text(\""+feedReportText+"\")";
    public static final String FEED_DETAILS_REPORT_REASON1 = "text(\""+feedReportReason1Text+"\")"; // 举报原因
    public static final String FEED_DETAILS_REPORT_REASON2 = "text(\""+feedReportReason2Text+"\")";
    public static final String FEED_DETAILS_REPORT_REASON3 = "text(\""+feedReportReason3Text+"\")";
    public static final String FEED_DETAILS_REPORT_REASON4 = "text(\""+feedReportReason4Text+"\")";
}
