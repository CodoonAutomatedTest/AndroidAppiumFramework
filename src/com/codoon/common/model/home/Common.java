package com.codoon.common.model.home;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;


public class Common {

    /**
     * 底部tab导航栏的 元素特征定义
     *
     * 特征可选取uiautomatorviewer的 text、resource-id、 className、 xpath等进行定义
     * 变量命名规则：变量末尾以选取的特征属性结尾，便于引用
     */
    private static final String sportCircleText = "运动圈";
    private static final String discoverText = "发现";
    private static final String sportsText = "运动";
    private static final String drycargoText = "干货";
    private static final String mineText = "我的";

    /**
     * 首页弹框（含灰测弹框、广告）的 元素特征定义
     *
     */
    final static String advPopupId = "com.codoon.gps:id/ads_content";
    final static String advPopupCloseId = "com.codoon.gps:id/btn_cancel";
    final static String descriptionBeta = "com.codoon.gps:id/ll_description";
    final static String descriptionCloseText = "我知道了";
    final static String accpetText = "确定";

    /*------------------------------------ Using --------------------------------------*/
    /**
     *  The value of the "uiautomator" attribute to search for
     *  eg:
     *      driver.findElementByAndroiduiautomator(@value)
     *
     */
    public static final String HOMEPAGE_SPORTSCIRCLE_TEXT = "text(\""+sportCircleText+"\")";
    public static final String HOMEPAGE_SPORTS_TEXT = "text(\""+sportsText+"\")";
    public static final String HOMEPAGE_FIND_TEXT = "text(\""+discoverText+"\")";
    public static final String HOMEPAGE_DRYCARGO_TEXT = "text(\""+drycargoText+"\")";
    public static final String HOMEPAGE_MINE_TEXT = "text(\""+mineText+"\")";
    public static final String HOMEPAGE_DESCRIPTIONCLOSE_TEXT = "text(\""+descriptionCloseText+"\")";
    public static final String LOGINPAGE_ACCPET_TEXT = "text(\""+accpetText+"\")";

    public static final String HOMEPAGE_ADVER_ID = "resourceId(\""+advPopupId+"\")";
    public static final String HOMEPAGE_ADVERCLOSE_ID = "resourceId(\""+advPopupCloseId+"\")";
    public static final String HOMEPAGE_DESCRIPTION_ID = "resourceId(\""+descriptionBeta+"\")";

}
