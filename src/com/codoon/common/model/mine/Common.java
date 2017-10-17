package com.codoon.common.model.mine;

public class Common {

    //Page Text
    private static final String followedText = "关注";
    private static final String fansText = "粉丝";
    private static final String feedText = "动态";
    private static final String historyViewText = "查看记录";
    private static final String resultText = "成绩";
    private static final String medalText = "奖章";
    private static final String rankText = "排行";
    private static final String trainText = "训练计划";
    private static final String collectText = "收藏";
    private static final String groupText = "运动团";
    private static final String matchText = "赛事";
    private static final String equipText = "装备";
    private static final String orderText = "订单";
    private static final String walletText = "钱包";
    private static final String clubText = "俱乐部";
    private static final String settingText = "设置";

    /*------------------------------------ Using --------------------------------------*/
    /*
     *  The value of the "uiautomator" attribute to search for
     *  eg:
     *      driver.findElementByAndroiduiautomator(@value)
     *
     */
    public static final String MY_HEAD_IMG_ID = "com.codoon.gps:id/head_img_icon";             //个人头像icon
    public static final String MY_SPORTS_LEVEL_ID = "com.codoon.gps:id/my_sport_level";        //运动等级icon
    public static final String MY_MESSAGE_BTN_ID = "com.codoon.gps:id/mine_message_btn";       //消息icon
    public static final String MY_MESSAGE_NUM_ID = "com.codoon.gps:id/message_num_text";       //消息红点数量icon
    public static final String HOMEPAGE_MESSAGE_NUM_ID = "com.codoon.gps:id/message_num_ball"; //底部导航栏消息红点数量icon
    public static final String MY_SPORTCOUNT_ID = "com.codoon.gps:id/my_sport_count";          //运动次数显示区域
    public static final String MY_LASTSPORT_ID = "com.codoon.gps:id/my_sport_last";            //最近运动信息显示区域
    public static final String MY_FEEDBACK_ID = "com.codoon.gps:id/feedback_gray_beta";        //意见反馈button

    public static final String MY_FOLLOWED_TEXT = "text(\""+followedText+"\")";                 //关注好友列表 文案
    public static final String MY_FANS_TEXT = "text(\""+fansText+"\")";                         //粉丝列表 文案
    public static final String MY_FEED_TEXT = "text(\""+feedText+"\")";                         //动态列表 文案
    public static final String MY_HISTORY_VIEW_TEXT = "text(\""+historyViewText+"\")";          //运动历史"查看全部"
    public static final String MY_RESULT_TEXT = "text(\""+resultText+"\")";                     //"成绩"item 文案
    public static final String MY_MEDAL_TEXT = "text(\""+medalText+"\")";                       //"奖牌"item 文案
    public static final String MY_RANK_TEXT = "text(\""+rankText+"\")";                         //"排行"item 文案
    public static final String MY_TRAIN_TEXT = "text(\""+trainText+"\")";                       //"训练计划"item 文案
    public static final String MY_COLLECT_TEXT = "text(\""+collectText+"\")";                   //"收藏"item 文案
    public static final String MY_GROUP_TEXT = "text(\""+groupText+"\")";                       //"运动团"item 文案
    public static final String MY_MATCH_TEXT = "text(\""+matchText+"\")";                       //"赛事"item 文案
    public static final String MY_EQUIP_TEXT = "text(\""+equipText+"\")";                       //"装备"item 文案
    public static final String MY_ORDER_TEXT = "text(\""+orderText+"\")";                       //"订单"item 文案
    public static final String MY_WALLET_TEXT = "text(\""+walletText+"\")";                     //"钱包"item 文案
    public static final String MY_CLUB_TEXT = "text(\""+clubText+"\")";                         //"俱乐部"item 文案
    public static final String MY_SETTING_TEXT = "text(\""+settingText+"\")";                   //"设置"item 文案

}
