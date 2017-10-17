package com.codoon.common.util;


/**
 * UiautomatorFormat Class是用于将using格式化一个新的String类型适合于AndroidUIAutomator接收参数的规范
 *
 * 1.  driver.findelementByAndroidUIAutomator(@return)
 * 2.  AndroidFindBy(uiautomator = @return)
 *
 */
public final class UiautomatorFormat {

    public static String parseText(String s){
        return String.format("text(\"%s\")", s);
    }

    public static String parseId(String s){
        return String.format("resourceId(\"%s\")", s);
    }

}
