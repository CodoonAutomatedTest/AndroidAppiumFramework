package com.codoon.common.model.drycargo;

public class Fitness {
    /**
     * 此class文件定义了干货界面健身tab所含的页面元素
     */

    private static String basicFitnessText = "基础健身";
    private static String bodyThinText = "减肥塑形";
    private static String dietSportsText = "健身饮食";
    private static String talentSportsText = "健身达人";
    private static String powerUpText = "力量增肌";

    /* --------------- Using --------------------- */
    public static final String FITNESS_BASIC_NAME = "text(\"" + basicFitnessText + "\")";  //基础健身tag标签
    public static final String FITNESS_LOSS_NAME = "text(\"" + bodyThinText + "\")";       //减肥塑形tag标签
    public static final String FITNESS_FOOD_NAME = "text(\"" + dietSportsText + "\")";     //健身饮食tag标签
    public static final String FITNESS_TALENT_NAME = "text(\"" + talentSportsText + "\")"; //健身达人tag标签
    public static final String FITNESS_POWER_NAME = "text(\"" + powerUpText + "\")";       //力量增肌tag标签
}
