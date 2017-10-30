package com.codoon.common.model.mine;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class Profile {
    //Page Text
    private static final String editText = "编辑";
    private static final String editTitleText = "编辑个人资料";
    private static final String changHeadText = "修改头像";
    private static final String changNickText = "昵称";
    private static final String changSexText = "性别";
    private static final String changInfoText = "个性签名";
    private static final String changInterText = "兴趣";
    private static final String changAgeText = "生日";
    private static final String changHeightText = "身高";
    private static final String changWeightText = "体重";
    private static final String cancelText = "取消";

    private static final String caredTitleText = "关注";
    private static final String fansTitleText = "粉丝";

    public static final String PROFILE_CARED_LABEL_ID = "com.codoon.gps:id/user_center_guanzhu_container";
    public static final String PROFILE_FANS_LABEL_ID = "com.codoon.gps:id/user_center_fensi_container";
    public static final String PROFILE_DYNAMIC_LABEL_ID = "com.codoon.gps:id/my_feed_dongtai_layout";


    public static final String PROFILE_RUN_LEVEL_ID = "com.codoon.gps:id/run_level";
    public static final String PROFILE_WALK_LEVEL_ID = "com.codoon.gps:id/walk_level";
    public static final String PROFILE_RIDE_LEVEL_ID = "com.codoon.gps:id/riding_level";

     /*------------------------------------ Using --------------------------------------*/
     public static final String PROFILE_CANCEL_TEXT = "text(\""+cancelText+"\")";
     public static final String PROFILE_EDIT_TEXT = "text(\""+editText+"\")";
     public static final String PROFILE_EDITTITLE_TEXT = "text(\""+editTitleText+"\")";
     public static final String PROFILE_EDIT_HEAD_TEXT = "text(\""+changHeadText+"\")";
     public static final String PROFILE_EDIT_NICK_TEXT = "text(\""+changNickText+"\")";
     public static final String PROFILE_EDIT_SEX_TEXT = "text(\""+changSexText+"\")";
     public static final String PROFILE_EDIT_INFO_TEXT = "text(\""+changInfoText+"\")";
     public static final String PROFILE_EDIT_INTER_TEXT = "text(\""+changInterText+"\")";
     public static final String PROFILE_EDIT_AGE_TEXT = "text(\""+changAgeText+"\")";
     public static final String PROFILE_EDIT_HEIGH_TEXT = "text(\""+changHeightText+"\")";
     public static final String PROFILE_EDIT_WEIGHT_TEXT = "text(\""+changWeightText+"\")";

    public static final String PROFILE_CAREDTITLE_TEXT = "text(\""+caredTitleText+"\")";
    public static final String PROFILE_FANSTITLE_TEXT = "text(\""+fansTitleText+"\")";
}
