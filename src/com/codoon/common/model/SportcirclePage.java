package com.codoon.common.model;

import com.codoon.common.util.DeviceHelper;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.driver.SikuppiumDriver;
import io.utils.Rect;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import static com.codoon.common.model.sportcircle.Common.*;
import static com.codoon.common.model.sportcircle.Popular.*;
import static com.codoon.common.model.sportcircle.Focus.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SportcirclePage {
    private final static Logger LOG = Logger.getLogger(SportcirclePage.class);

    private static SportcirclePage instance;
    private SikuppiumDriver driver;
    private static SessionId sessionId;
    DeviceHelper mHelper= DeviceHelper.getInstance(driver);
    public static final String COMMENT_TEXT = "Great";

    /* ---- By selector ---- */
    public static By viewClassBy = MobileBy.AndroidUIAutomator("className(\""+CLASSNAME_VIEW+"\")");
    public static By viewGroupClassBy = MobileBy.AndroidUIAutomator("className(\""+CLASSNAME_VIEWGROUP+"\")");
    public static By imageViewClassBy = MobileBy.AndroidUIAutomator("className(\""+CLASSNAME_IMAGEVIEW+"\")");
    public static By textViewClassBy = MobileBy.AndroidUIAutomator("className(\""+CLASSNAME_TEXTVIEW+"\")");
    public static By linearLayoutClassBy = MobileBy.AndroidUIAutomator("className(\""+CLASSNAME_LINEARLAYOUT+"\")");
    public static By scrollViewClassBy = MobileBy.AndroidUIAutomator("className(\""+CLASSNAME_SCROLLVIEW+"\")");



    public static By myNickTextBy = MobileBy.AndroidUIAutomator(MYNICK_NAME_TEXT);
    public static By photoTitleTextBy = MobileBy.AndroidUIAutomator(BYPHOTO_TITLE_TEXT);
    public static By locationCityTextBy = MobileBy.AndroidUIAutomator(LOCATION_CITY_TEXT);
    public static By addFeedBtnBy = By.id(ADDFEED_BTN_ID);
    public static By searchGrouListBy = By.id(SEARCH_GROUP_ITEM_ID);
    public static By searchAtriListBy = By.id(SEARCH_ATRICLE_ITEM_ID);
    public static By searchUserListBy = By.id(SEARCH_USER_ITEM_ID);
    public static By pageTitleBy = By.id(PAGE_TITLE);
    public static By discussItemTitleBy = By.id(DISCUSS_LIST_ITEM_TITLE);

    /**
     * 当前driver页面包含字符串Str
     * @params
     * @return
     */
    public static By textContainsBy(String s){
        return By.xpath("//*[contains(@text,'"+s+"')]");
    }
    public static By textEqualsBy(String s){
        return By.xpath("//*[@text='"+s+"']");
    }

    //feed
    public static By feedContentBy = By.id(FEED_CONTENT_ID);
    public static By feedDetailNickBy = By.id(FEED_NICK_ID);
    public static By feedDetailCommentBy = By.id(FEED_DETAILS_COMMENT_ID);
    public static By feedDetailCLikeBy = By.id(FEED_DETAILS_LIKE_ID);

    public static By moreBtnBy = MobileBy.AndroidUIAutomator(MOREBTN_TEXT);
    /* --- Popular --- */
    public static By hotTopicBy = MobileBy.AndroidUIAutomator(POPULAR_TOPIC_TEXT);
    public static By discussColumnBy = MobileBy.AndroidUIAutomator(POPULAR_DISCUSS_TEXT);
    public static By discussTitleBy = MobileBy.AndroidUIAutomator(POPULAR_DISCUSS_MORETITLE_TEXT);
    public static By replyCommentBy = By.id(RE);
    public static By talentBy = MobileBy.AndroidUIAutomator(POPULAR_TALENT_TEXT);
    public static By userAtiveBy = MobileBy.AndroidUIAutomator(POPULAR_USER_TEXT);
    public static By dynamicBy = MobileBy.AndroidUIAutomator(POPULAR_DYNAMIC_TEXT);
    public static By feedDetailBy = MobileBy.AndroidUIAutomator(POPULAR_FEED_DETAILTITLE_TEXT);
    /* --- Cared --- */
    public static By suggestUserBy = MobileBy.AndroidUIAutomator(FEED_SUGGESTUSER_TAG_TEXT);
    public static By changeOtherBy = By.id(OTHER_CHANGE_BTN_ID);
    public static By userNickBy = By.id(USER_NICK_TEXT);
    public static By feedNearbyLabelBy = By.id(FEED_NEARBY_ID);
    public static By feedNearbyTagBy = MobileBy.AndroidUIAutomator(FEED_NEARBY_TEXT);
    public static By feedNearbyMoreBy = MobileBy.AndroidUIAutomator(FEED_NEARBY_MORETITLE_TEXT);
    public static By feedCommentCountBy = By.id(FEED_COMMENTCOUNT_ID);

    public static By followBtnBy = MobileBy.AndroidUIAutomator(FOLLOW_BTN_TEXT);
    public static By followedBtnBy = MobileBy.AndroidUIAutomator(FOLLOWED_BTN_TEXT);

    /* ----- element ----- */
    //顶部栏控件
    @FindBy(id = SEARCH_INPUT_ID)
    public WebElement searchInputEdit;
    @FindBy(id = SEARCH_BTN_ID)
    public WebElement searchSubmitButton;
    @FindBy(id = SCAN_BTN_ID)
    public WebElement scanButton;
    @FindBys({
            @FindBy(id = POPULAR_PAGEVIEWER_ID),
            @FindBy(className = CLASSNAME_HORIZONSCROLLVIEW)
    })
    public WebElement advScollView;

    //搜索结果
    @AndroidFindBy(uiAutomator = SEARCH_USER_TAB_TEXT)
    public WebElement userTabInSearch;
    @AndroidFindBy(uiAutomator = SEARCH_GROUP_TAB_TEXT)
    public WebElement groupTabInSearch;
    @AndroidFindBy(uiAutomator = SEARCH_ATRICLE_TAB_TEXT)
    public WebElement atricleTabInSearch;
    @AndroidFindBy(uiAutomator = SEARCH_DYNAMIC_TAB_TEXT)
    public WebElement dynamicTabInSearch;
    @AndroidFindBy(uiAutomator = SEARCH_GOODS_TAB_TEXT)
    public WebElement goodsTabInSearch;
    @AndroidFindBy(uiAutomator = SEARCH_MATCH_TAB_TEXT)
    public WebElement matchTabInSearch;
    @FindBy(id = SEARCH_GROUP_ITEM_ID)
    public WebElement searchGroupListItem;
    @FindBy(id = SEARCH_ATRICLE_ITEM_ID)
    public WebElement searchAtriListItem;
    @FindBy(id = SEARCH_USER_ITEM_ID)
    public WebElement searchUserListItem;
    @AndroidFindBy(uiAutomator = POPULAR_TALENT_TEXT)
    public WebElement talentTag;
    @AndroidFindBy(uiAutomator = POPULAR_DYNAMIC_TEXT)
    public WebElement dynamicTag;
    @FindBys({ @FindBy(id = POPULAR_PAGEVIEWER_ID), @FindBy(className = CLASSNAME_TEXTVIEW) })
    public WebElement dynamicText; // 精选动态tag下，feed列表文本


    //分类tab栏
    @AndroidFindBy(uiAutomator = SPORTCIRCLE_HOTTAB_TEXT)
    public WebElement hotTabIcon;
    @AndroidFindBy(uiAutomator = SPORTCIRCLE_CAREDTAB_TEXT)
    public WebElement caredTabIcon;
    @AndroidFindBy(uiAutomator = SPORTCIRCLE_LIVETAB_TEXT)
    public WebElement liveTabIcon;

    //热门页
    @FindBys({ @FindBy(className = CLASSNAME_SCROLLVIEW), @FindBy(className = CLASSNAME_VIEW),@FindBy(className = CLASSNAME_VIEW) })
//    @FindBys({ @FindBy(className = CLASSNAME_SCROLLVIEW), @FindBy(className = CLASSNAME_VIEWGROUP),@FindBy(className = CLASSNAME_VIEWGROUP) })
    public WebElement topic; // 话题页话题列表
    //讨论专区
    @FindBys({
            @FindBy(id=POPULAR_LISTVIEW_DISCUSS_ID),
            @FindBy(className=CLASSNAME_LINEARLAYOUT)
    })
    public WebElement discussList;
    @FindBy(id = COMMENT_INPUT_ID)
    public WebElement infoInputEdit;
    @AndroidFindBy(uiAutomator = SEND_BTN_TEXT)
    public WebElement infoSendBtn;
    @FindBy(id = RE)
    public WebElement replyCommentIcon;
    //关注页
    @AndroidFindBy(uiAutomator = FEED_SUGGESTUSER_TAG_TEXT)
    public WebElement suggestUserTag;
    @FindBy(id = OTHER_CHANGE_BTN_ID)
    public WebElement changeOtherBtn;
    @FindAll({
            @FindBy(id = USER_NICK_TEXT)
    })
    public List<WebElement> userNickLabel;
    @AndroidFindBy(uiAutomator = FEED_NEARBY_TEXT)
    public WebElement nearbyFeed;
    @FindBy(id = FEED_NICK_ID)
    public WebElement feedNickLabel;
    @FindBy(id = FEED_HEAD_ID)
    public WebElement feedHead;
    @FindBy(id = FEED_LIKE_BTN_ID)
    public WebElement feedLike;
    @FindBy(id = FEED_LIKECOUNT_TEXT_ID)
    public WebElement feedLikeNum;
    @FindBy(id = FEED_COMMENTCOUNT_ID)
    public WebElement feedComment;
    @FindBy(id = FEED_DETAILS_COMMENTEDIT_ID)
    public WebElement detailCommentEdit;
    @AndroidFindBy(uiAutomator = FEED_DETAILS_SEEDBTN_NAME)
    public WebElement detailSendComment;
    @FindBy(id = FEED_CONTENT_ID)
    public WebElement feedContext; // feed内容文字显示区域
    @FindBy(id = FEED_DETAILS_FEEDCOLLECT_ID)
    public WebElement detailCollectButton; // feed内容文字显示区域
    @FindBy(id = FEEDMORE_BTN_ID)
    public WebElement feedMore;
    @AndroidFindBy(uiAutomator = FEED_DETAILS_REPORT_TEXT)
    public WebElement detailReportButton;
    @AndroidFindAll({ @AndroidFindBy(uiAutomator = FEED_DETAILS_REPORT_REASON1), @AndroidFindBy(uiAutomator = FEED_DETAILS_REPORT_REASON2),
            @AndroidFindBy(uiAutomator = FEED_DETAILS_REPORT_REASON3), @AndroidFindBy(uiAutomator = FEED_DETAILS_REPORT_REASON4) })
    public List<WebElement> reportReason;
    @FindBy(id = FEED_COMMENTCOUNT_ID)
    public WebElement feedCommentNum;
    //feed详情页
    @FindBy(id = FEED_DETAILS_COMMENT_ID)
    public WebElement feedDetailCommentIcon;
    @FindBy(id = FEED_DETAILS_LIKE_ID)
    public WebElement feedDetailLikeIcon;


    //发feed相关控件
    @FindBy(id = ADDFEED_BTN_ID)
    public WebElement addFeedElement; // "+"发feed
    public Point addFeedBtn; // 发feed“+”按钮正中的坐标点
    public Point dynamicFeedBtn; // 动态按钮正中的坐标点
    public Point liveFeedBtn; // 直播按钮正中的坐标点
    public Point cannelFeedBtn; // 取消动态按钮正中的坐标点

    @FindBy(id = ADDFEED_BYPHOTO_ID)
    public WebElement photoButton;       //“相册” 选择图片
    @FindBy(id = ADDFEED_BYCAMERA_ID)
    public WebElement cameraButton;     //“拍照” 选择图片
    @FindAll({
            @FindBy(id = ADDFEED_PICTURE_SELECT_ID)
    })
    public List<WebElement> photoSelectIcon;    //feed图片选框
    @AndroidFindBy(uiAutomator = PHOTO_SELECT_SURE_TEXT)
    public WebElement photoSelectSureIcon;   //feed位置页面关闭icon
    @FindBy(id = ADDFEED_CONTENT_ID)
    public WebElement contetInputEdit;    //feed内容编辑区域
    @FindBy(id = ADDFEED_LOCATION_ID)
    public WebElement feedLocationIcon;   //feed定位icon
    @AndroidFindBy(uiAutomator = ADDTOPIC_TAG_TEXT)
    public WebElement feedTopicTagIcon;    //feed话题标签
    @FindBy(id = ADDFEED_CLOSELOCATION_ID)
    public WebElement feedLocationCloseIcon;   //feed位置页面关闭icon
    @AndroidFindBy(uiAutomator = FEED_PUBLISH_TEXT)
    public WebElement feedPublishButton;   //feed发布按钮

    //other
    @AndroidFindBy(uiAutomator = FOLLOW_BTN_TEXT)
    public WebElement followButton;        // "关注"按钮
    @AndroidFindBy(uiAutomator = FOLLOWED_BTN_TEXT)
    public WebElement followedButton;      // "已关注"按钮


    private void initElements() {
        Rect rect = mHelper.getVisibleBounds(addFeedElement);
        addFeedBtn = new Point(rect.centerX() + 30, rect.centerY() + rect.height() / 2 - 15);
        dynamicFeedBtn = new Point(rect.centerX() + 30, rect.centerY() - rect.height() / 2 + 70);
        liveFeedBtn = new Point(rect.centerX() + 30, rect.centerY());
        cannelFeedBtn = new Point(rect.centerX() + 30, rect.centerY() + rect.height() / 2 - 70);
    }

    private SportcirclePage(SikuppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), this);
        this.driver = driver;
        initElements();
        sessionId = driver.getSessionId();
    }

    public static SportcirclePage getInstance(SikuppiumDriver driver) {
        if (driver.getSessionId() != sessionId) {
            instance = new SportcirclePage(driver);
        }
        return instance;
    }


    /* -------------------------------  LOGIC PART --------------------------------- */

    /**
     * 点击热门界面tag标签后的"更多"按钮
     * @param by tag标签的selector
     * @throws InterruptedException
     */
    public void clickHotpageMore(By by) throws InterruptedException {
        mHelper.searchBy(by, 10);
        mHelper.includeElementToRight(by, 0, MobileBy.AndroidUIAutomator(MOREBTN_TEXT)).click();
        mHelper.waitForVisible(driver, textViewClassBy, 5);
    }

    /**
     * 点击 话题列表页第一项
     *
     * @return
     */
    public String clickTopicListView() throws InterruptedException {
        String topicName = topic.findElement(textViewClassBy).getText();
        this.topic.click();
        Thread.sleep(5000L);
        return topicName;
    }

    /**
     * 未关注状态 和已关注状态 点击状态切换
     * @param li1: 未关注按钮的元素列表
     * @param li2: 已关注按钮的元素列表
     * @return
     */
    public boolean followStatusChanged(List<WebElement> li1, List<WebElement> li2) {
        boolean flag = false;
        if (li1.size() == 0) {
            LOG.info("推荐达人均已关注");
            for (int i = 0; i < li2.size(); i++) {
                li2.get(i).click();
            }
            flag = mHelper.isExistBySelector(driver, followBtnBy, 5);
            LOG.info("推荐达人已全部取消关注");
        } else if (li2.size() == 0) {
            LOG.info("推荐达人均未关注");
            for (int i = 0; i < li1.size(); i++) {
                li1.get(i).click();
            }
            flag = mHelper.isExistBySelector(driver, followedBtnBy, 5);
            LOG.info("推荐达人已全部关注");
        } else {
            LOG.info("推荐达人一关注一未关注");
            li1.get(0).click();
            flag = mHelper.isExistBySelector(driver, followBtnBy, 5);
            LOG.info("推荐达人已全部关注");
        }
        return flag;
    }

    public void sendFeed(int num, boolean local, boolean hasTopic) throws InterruptedException {
        for (int i = 0; i < num; i++) {
            photoSelectIcon.get(i).click();
//            driver.findElements(By.id(COMMON_ADDFEED_SELECTICON_ID)).get(i).click();
        }
        photoSelectSureIcon.click();
//        driver.findElement(By.name(COMMON_ADDFEED_SELECTSURE_NAME)).click();
        contetInputEdit.sendKeys("this is a joke");
//        driver.findElement(By.id(COMMON_ADDFEED_CONTENT_ID)).click();

//        driver.findElement(By.id(COMMON_ADDFEED_CONTENT_ID)).sendKeys("this is a joke");
        if (local) {
            feedLocationIcon.click();
//            driver.findElement(By.id(COMMON_ADDFEED_LOCATION_ID)).click();
            mHelper.isExistBySelector(driver, locationCityTextBy, 5);
            driver.findElement(locationCityTextBy).click();
            feedLocationCloseIcon.click();
//            driver.findElement(By.id(COMMON_ADDFEED_CLOSELOCATION_ID)).click();
        }
        if (hasTopic) {
            addTagTxt("jokes");
        }
        feedPublishButton.click();
//        driver.findElement(By.name(COMMON_ADDFEED_PUBLISH_NAME)).click();
        Thread.sleep(10000L);
        if (!mHelper.waitText(accountNickText, 2)) {
            mHelper.swipeDownSlowly();
        }
    }

    private void addTagTxt(String tag) {
        feedTopicTagIcon.click();
        driver.findElement(By.id(TAG_INPUT_EDIT)).sendKeys(tag);
        if (mHelper.isExistBySelector(driver, By.id(CREAT_TAG_BTN), 5)) {
            driver.findElement(By.id(CREAT_TAG_BTN)).click();
        } else {
            driver.findElement(By.id(TAG_LABEL_TEXT)).click();
        }
    }

    public void fixPositionOnFeed() throws InterruptedException {
        while (mHelper.waitText(accountNickText, 3)) {
            mHelper.swipeUp(3);
        }
        mHelper.searchBy(By.id(FEED_HEAD_ID), 3);
        mHelper.moveToViewTop(feedHead);
        if (mHelper.searchBy(By.id(FEED_LIKE_BTN_ID), 10))
            mHelper.moveToHalfScreen(feedLike);
    }

    public boolean feedTakeLikeOrDislike() {
        boolean flag = true;
        int oldcout, newcout;
        oldcout = getCurrentLikeCount();
        this.feedLike.click();
        newcout = getCurrentLikeCount();
        if (oldcout + 1 == newcout) {
            LOG.info("feed已点赞!");
        } else if (oldcout - 1 == newcout) {
            LOG.info("feed已取消点赞!");
        } else {
            flag = false;
        }
        return flag;
    }

    public void feedComment() throws InterruptedException, IOException {
        feedComment.click();
        mHelper.changePhoneIME("com.samsung.inputmethod/.SamsungIME");
        detailCommentEdit.sendKeys(COMMENT_TEXT);
        this.detailSendComment.click();
        Thread.sleep(1000L);
    }

    public boolean checkCollectionInMine(String str) throws InterruptedException {
        mHelper.pressBackToHomePage(5);
        HomePage.mineTab.click();
        mHelper.searchBy(MobileBy.AndroidUIAutomator("text(\"收藏\")"), 10);
        driver.findElement(MobileBy.AndroidUIAutomator("text(\"收藏\")")).click();
        Thread.sleep(2000L);
        driver.findElement(MobileBy.AndroidUIAutomator("text(\"动态\")")).click();
        Thread.sleep(2000L);
        return mHelper.searchBy(MobileBy.AndroidUIAutomator("text(\""+str+"\")"), 10);
    }

    public void findAllComment() throws InterruptedException { // com.codoon.gps:id/tv_comment_count
        while (mHelper.waitText(accountNickText, 3)) {
            mHelper.swipeUp(2);
        }
        mHelper.searchBy(feedCommentCountBy, 20);
        feedCommentNum.click();
    }

    public boolean feedDetailClickLike(){
        int num1 = 0;
        int num2 = 0;
        try {
            num1 = Integer.parseInt(mHelper.includeElementToRight(feedDetailCommentBy, 0, textViewClassBy).getText());
        } catch (Exception e) {
            ;
        }
        LOG.info("num1->"+num1);
        try {
            mHelper.includeElementToRight(feedDetailCommentBy, 0, feedDetailCLikeBy).click();
            num2 = Integer.parseInt(mHelper.includeElementToRight(feedDetailCommentBy, 0, textViewClassBy).getText());
        } catch (Exception e) {
            ;
        }
        LOG.info("num2->"+num2);
//        Assert.assertTrue(num1 + 1 == num2 || num1-1 == num2, "评论点赞失败");
        return num1 + 1 == num2 || num1-1 == num2;
    }

    public boolean feedDetailReplyInfo(String str) throws InterruptedException, IOException {
        mHelper.changePhoneIME("com.samsung.inputmethod/.SamsungIME");
        mHelper.findElementToRight(feedDetailNickBy, 0, feedDetailCommentBy).click();
        detailCommentEdit.sendKeys(str);
        detailSendComment.click();
        return mHelper.searchDownBy(MobileBy.AndroidUIAutomator("textContains(\""+str+"\")"), 10);
    }

    private int getCurrentLikeCount() {
        try {
            String txt1 = feedLikeNum.getText(); // 获取点赞人数
            return Integer.valueOf(txt1.split("人赞了")[0]);
        } catch (Exception e) {
            return 0; // 若无点赞人数文案出现，则点赞为0
        }
    }

}
