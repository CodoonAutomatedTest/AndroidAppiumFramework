package com.codoon.common.model.sportcircle;

public class Common {
    /**
     * 运动圈通用 元素定义
     */

//    public static final String MYNICK_NAME = "烤羊肉串";
    public static final String accountNickText = "伤心凉粉123";
    public static final String MYNICK_NAME_TEXT = "text(\""+accountNickText+"\")";
    //各类Android class
    public static final String CLASSNAME_VIEW = "android.view.View";                     //Class of View
    public static final String CLASSNAME_VIEWGROUP = "android.view.ViewGroup";           //Class of ViewGroup
    public static final String CLASSNAME_IMAGEVIEW = "android.widget.ImageView";         //Class of ImageView
    public static final String CLASSNAME_TEXTVIEW = "android.widget.TextView";           //Class of TextView
    public static final String CLASSNAME_LINEARLAYOUT = "android.widget.LinearLayout";   //Class of LinearLayout
    public static final String CLASSNAME_HORIZONSCROLLVIEW = "android.widget.HorizontalScrollView";    //广告banner
    public static final String CLASSNAME_SCROLLVIEW = "android.widget.ScrollView";    //列表页items

    //顶部栏
    public static final String SEARCH_INPUT_ID = "com.codoon.gps:id/searchbar_edit";  //搜索按钮
    public static final String SEARCH_BTN_ID = "com.codoon.gps:id/tv_search";         //搜索输入框
    public static final String SCAN_BTN_ID = "com.codoon.gps:id/iv_scan";             //扫一扫按钮

    //搜索-tab标签
    private static final String userTabText = "用户";
    private static final String groupTabText = "运动团";
    private static final String atricleTabText = "文章";
    private static final String dynamicTabText = "动态";
    private static final String goodsTabText = "商品";
    private static final String matchTabText = "赛事";
    private static String moreBtnText = "更多";

    public static final String SEARCH_USER_TAB_TEXT = "text(\""+userTabText+"\")";  //搜索结果-用户tab栏
    public static final String SEARCH_GROUP_TAB_TEXT = "text(\""+groupTabText+"\")";  //搜索结果-运动团tab栏
    public static final String SEARCH_ATRICLE_TAB_TEXT = "text(\""+atricleTabText+"\")";  //搜索结果-文章tab栏
    public static final String SEARCH_DYNAMIC_TAB_TEXT = "text(\""+dynamicTabText+"\")";  //搜索结果-动态tab栏
    public static final String SEARCH_GOODS_TAB_TEXT = "text(\""+goodsTabText+"\")";  //搜索结果-商品tab栏
    public static final String SEARCH_MATCH_TAB_TEXT = "text(\""+matchTabText+"\")";  //搜索结果-赛事tab栏

    public static final String SEARCH_GROUP_ITEM_ID = "com.codoon.gps:id/groupitem_txt_name";  //搜索结果-运动团列表item
    public static final String SEARCH_ATRICLE_ITEM_ID = "com.codoon.gps:id/txtViewTitle";  //搜索结果-文章列表item
    public static final String SEARCH_USER_ITEM_ID = "com.codoon.gps:id/reserverunitem_txt_nickname";  //搜索结果-用户列表item

    //feed
    public static final String ADDFEED_BTN_ID = "com.codoon.gps:id/btn_add_feed"; //发布feed按钮
    public static final String ADDFEED_BYPHOTO_ID = "com.codoon.gps:id/btn_photo"; 	//“相册”发feed
    public static final String ADDFEED_BYCAMERA_ID = "com.codoon.gps:id/btn_camera"; 	//“拍照”发feed
    public static final String ADDFEED_PICTURE_SELECT_ID = "com.codoon.gps:id/isselected"; //图片选择状态框
    public static final String ADDFEED_CONTENT_ID = "com.codoon.gps:id/et_content";     //feed内容编辑区域
    public static final String ADDFEED_LOCATION_ID= "com.codoon.gps:id/btn_location";   //选择定位按钮
    public static final String ADDFEED_CLOSELOCATION_ID = "com.codoon.gps:id/iv_close"; //定位页面 关闭按钮
    public static final String BYPHOTO_TITLE_TEXT = "text(\"最新照片\")";
    public static final String LOCATION_CITY_TEXT = "text(\"成都市\")";
    private static final String photoSelectSureText = "确定";
    private static final String feedPublishText = "发布";
    private static final String addTopicTagText= "#添加话题";

    private static final String followBtnText = "关注";             //关注btn
    private static final String followedBtnText = "已关注";		 //已关注btn
    public static final String MOREBTN_TEXT = "text(\""+moreBtnText+"\")";

    //创建话题标签
    public static final String TAG_INPUT_EDIT = "com.codoon.gps:id/searchbar_edit";
    public static final String CREAT_TAG_BTN = "com.codoon.gps:id/create_label_btn";
    public static final String TAG_LABEL_TEXT = "com.codoon.gps:id/label_txt";

    //tab标签
    private static final String hotTabText = "热门";          //热门tab
    private static final String caredTabText = "关注";        //关注tab
    private static final String liveTabText = "直播";         //直播tab

    /*------------------------------------ Uiautomator Using --------------------------------------*/
    /*
     *  The value of the "uiautomator" attribute to search for
     *  eg:
     *      driver.findElementByAndroiduiautomator(@value)
     *
     */
    public static final String SPORTCIRCLE_HOTTAB_TEXT = "text(\""+hotTabText+"\")";
    public static final String SPORTCIRCLE_CAREDTAB_TEXT = "text(\""+caredTabText+"\")";
    public static final String SPORTCIRCLE_LIVETAB_TEXT = "text(\""+liveTabText+"\")";
    public static final String FOLLOW_BTN_TEXT = "text(\""+followBtnText+"\")";
    public static final String FOLLOWED_BTN_TEXT = "text(\""+followedBtnText+"\")";
    public static final String PHOTO_SELECT_SURE_TEXT = "text(\""+photoSelectSureText+"\")";
    public static final String FEED_PUBLISH_TEXT = "text(\""+feedPublishText+"\")";
    public static final String ADDTOPIC_TAG_TEXT = "text(\""+addTopicTagText+"\")";

}
