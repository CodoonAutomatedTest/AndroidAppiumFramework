package com.codoon.common.model.setting;

public class Common {

    /*------------------------------------ Page Text --------------------------------------*/
    /*一级页面*/
    private static final String settingTitleText = "设置";
    private static final String sportsSettingText = "运动设置";
    private static final String mobileSetpText = "手机计步";
    private static final String wechatSetpText = "微信步数排行";

    private static final String messageNoticeText = "消息通知";
    private static final String imageLowText = "图片浏览省流量";
    private static final String cacheCleanText = "清除缓存";
    private static final String authorityText = "设置运动权限";
    private static final String sensorCheckText = "测试传感器";
    private static final String blackListText = "黑名单";
    private static final String codoonIDText = "咕咚ID";
    private static final String bindingText = "账号绑定";
    private static final String gradeText = "求鼓励";
    private static final String faqText = "帮助与反馈";
    private static final String aboutText = "关于咕咚";
    private static final String logoutText = "退出登录";
    /*二级页面*/
    //运动设置
    private static final String voicePlayText = "语音播报";
    private static final String beatText = "节拍器";
    private static final String extendText = "跑后拉伸";
    private static final String actionText = "动作预览";
    private static final String autoPauseText = "自动暂停";
    private static final String keepLightText = "运动时保持屏幕常亮";
    private static final String viewDateText = "锁屏时显示运动数据";
    private static final String autoLockText = "运动时屏幕自动锁定";
    private static final String mapSettingText = "地图设置";
    private static final String heartDeviceText = "心率设备";
    private static final String codoonshoeText = "咕咚智能跑鞋";
    private static final String treadmillText = "连接蓝牙跑步机";
    //手机计步
    public static final String SETTING_STEP_TURN_BUTTON_ID = "com.codoon.gps:id/health_step_turn";
    public static final String SETTING_STEP_SLIP_BUTTON_ID = "com.codoon.gps:id/setting_slip_keep_step";
    public static final String SETTING_DAILY_SETP_VALUE_ID = "com.codoon.gps:id/accessory_target_step";
    public static final String SETTING_DAILY_SETP_WHEEL_ID = "com.codoon.gps:id/sport_target_wheel";


    /*------------------------------------ Using --------------------------------------*/
    /*
     *  The value of the "uiautomator" attribute to search for
     *  eg:
     *      driver.findElementByAndroiduiautomator(@value)
     *
     */
    public static final String SETTING_TITLE_TEXT = "text(\""+settingTitleText+"\")";
    public static final String SETTING_SPORT_ENTER_TEXT = "text(\""+sportsSettingText+"\")";
    public static final String SETTING_MOBILE_SETP_TEXT = "text(\""+mobileSetpText+"\")";
    public static final String SETTING_WECHAT_SETP_TEXT = "text(\""+wechatSetpText+"\")";
    public static final String SETTING_MESSAGE_NOTICE_TEXT = "text(\""+messageNoticeText+"\")";
    public static final String SETTING_IMAGE_LOW_TEXT = "text(\""+imageLowText+"\")";
    public static final String SETTING_CACHE_CLEAN_TEXT = "text(\""+cacheCleanText+"\")";
    public static final String SETTING_SPORT_AUTHOR_TEXT = "text(\""+authorityText+"\")";
    public static final String SETTING_CHECK_SENSOR_TEXT = "text(\""+sensorCheckText+"\")";
    public static final String SETTING_BLACK_LIST_TEXT = "text(\""+blackListText+"\")";
    public static final String SETTING_CODOONID_TEXT = "text(\""+codoonIDText+"\")";
    public static final String SETTING_ACCOUT_BIND_TEXT = "text(\""+bindingText+"\")";
    public static final String SETTING_GRADE_TEXT = "text(\""+gradeText+"\")";
    public static final String SETTING_FAQ_TEXT = "text(\""+faqText+"\")";
    public static final String SETTING_ABOUT_TEXT = "text(\""+aboutText+"\")";
    public static final String SETTING_LOGOUT_TEXT = "text(\""+logoutText+"\")";
}
