package com.codoon.common.model;

import com.codoon.common.util.DeviceHelper;
import com.codoon.common.util.WebH5Helper;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.driver.SikuppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static com.codoon.common.model.drycargo.Common.*;
import static com.codoon.common.model.drycargo.Recommend.*;
import static com.codoon.common.model.drycargo.Running.*;
import static com.codoon.common.model.drycargo.Ride.*;
import static com.codoon.common.model.drycargo.Fitness.*;
import static com.codoon.common.model.drycargo.Personality.*;

public class DrycargoPage {
    private final static Logger LOG = Logger.getLogger(SportcirclePage.class);
    private static DrycargoPage instance;
    private static SessionId sessionId;
    private SikuppiumDriver driver;
    private WebH5Helper webH5Helper;

    DeviceHelper mHelper= DeviceHelper.getInstance(driver);

    /* --------------------------- By selector --------------------------------- */
    /*                     selector用于定位Element时的元素标识                      */
    /* ------------------------------------------------------------------------- */

    /**
     * 定位NATIVE_APP页面包含text的元素标识
     * @param str: 查看的目标文本的定位标识
     * @return @selector
     */
    public static By textContainsBy(String str){
        return By.xpath("//*[contains(@text,'"+str+"')]");
    }

    /**
     * 定位NATIVE_APP页面等于text的元素标识
     * @param str: 查看的目标文本的定位标识
     * @return @selector
     */
    public static By textEqualsBy(String str){ return By.xpath("//*[@text='"+str+"']"); }


    /**
     * 定位WEBVIEW的H5页面包含text的元素标识
     * @param str: 查看的目标文本的定位标识
     * @return @selector
     */
    public static By H5webtextContainsBy(String str){ return By.xpath("//*[contains(text(),'"+str+"')]"); }


    /**
     * 定位WEBVIEW的H5页面等于text的元素标识
     * @param str: 查看的目标文本的定位标识
     * @return @selector
     */
    public static By H5webtextEqualsBy(String str){ return By.xpath("//*[text()='"+str+"']"); }
    public static By H5webNameEqualsBy(String str){ return By.xpath("//*[@content-desc='"+str+"']"); }

    public static By scrollViewClassBy = By.className(CLASSNAME_HORIZONSCROLLVIEW);        //scrollview class 定位selector
    public static By viewClassBy = By.className(CLASSNAME_VIEW);                    //view class 定位selector
    public static By groupViewClassBy = By.className(CLASSNAME_VIEWGROUP);          //groupview class 定位selector
    public static By textViewClassBy = By.className(CLASSNAME_TEXTVIEW);            //textview class 定位selector
    public static By trainDetailNameBy = By.id(TRAIN_DETAIL_NAME_ID);               //训练计划详情页名称ID 定位selector

    //推荐
    public static By dailyTagTextBy = MobileBy.AndroidUIAutomator(RECOMMEND_DAILYHOT_NAME); //今日热门tag 定位selector
    public static By explanTagTextBy = MobileBy.AndroidUIAutomator(RECOMMEND_EXPLAN_NAME);  //训练计划tag 定位selector
    public static By equipTagTextBy = MobileBy.AndroidUIAutomator(EQUIP_REVIEW_TEXT);       //装备评测tag 定位selector
    public static By maybelikeTextBy = MobileBy.AndroidUIAutomator(MAYBE_LIKE_TEXT);        //猜你喜欢tag 定位selector
    //跑步
    public static By runKnowledgeTextBy = MobileBy.AndroidUIAutomator(RUNNING_KNOWLEDGE_NAME);  //跑步知识tag 定位selector
    public static By runTrainTextBy = MobileBy.AndroidUIAutomator(RUNNING_TRAIN_NAME);          //跑步训练tag 定位selector
    public static By runNewsTextBy = MobileBy.AndroidUIAutomator(RUNNING_NEWS_NAME);            //跑步资讯tag 定位selector
    public static By runSafetyTextBy = MobileBy.AndroidUIAutomator(RUNNING_SAFETY_NAME);        //损伤防护tag 定位selector
    public static By runTopicTextBy = MobileBy.AndroidUIAutomator(RUNNING_TOPIC_NAME);          //跑步话题tag 定位selector
    public static By runEquipTextBy = MobileBy.AndroidUIAutomator(RUNNING_EQUIP_NAME);          //跑步装备tag 定位selector
    public static By runStoryTextBy = MobileBy.AndroidUIAutomator(RUNNING_STORY_NAME);          //跑者故事tag 定位selector
    public static By runOffrodeTextBy = MobileBy.AndroidUIAutomator(RUNNING_OFFRODE_NAME);      //越野跑tag 定位selector
    //健身
    public static By basicFitnessTextBy = MobileBy.AndroidUIAutomator(FITNESS_BASIC_NAME);  //基础健身tag定位selector
    public static By lossWeightTextBy = MobileBy.AndroidUIAutomator(FITNESS_LOSS_NAME);     //减肥塑形tag定位selector
    public static By foodFitnessTextBy = MobileBy.AndroidUIAutomator(FITNESS_FOOD_NAME);    //健身饮食tag定位selector
    public static By talentFitnessTextBy = MobileBy.AndroidUIAutomator(FITNESS_TALENT_NAME); //健身达人tag定位selector
    public static By powerFitnessTextBy = MobileBy.AndroidUIAutomator(FITNESS_POWER_NAME);   //力量增肌tag定位selector
    //骑行
    public static By rideKnowledgeTextBy = MobileBy.AndroidUIAutomator(RIDE_KNOWLEDGE_NAME);    //骑行知识tag 定位selector
    public static By rideTopicTextBy = MobileBy.AndroidUIAutomator(RIDE_TOPIC_NAME);            //骑行话题tag 定位selector
    public static By rideNewsTextBy = MobileBy.AndroidUIAutomator(RIDE_NEWS_NAME);              //骑行资讯tag 定位selector
    public static By rideTalentTextBy = MobileBy.AndroidUIAutomator(RIDE_TALENT_NAME);          //骑行达人tag 定位selector
    public static By rideColumnTextBy = MobileBy.AndroidUIAutomator(RIDE_COLUMN_NAME);          //骑行专题tag 定位selector
    public static By rideTravelTextBy = MobileBy.AndroidUIAutomator(RIDE_TRAVEL_NAME);          //单车旅行tag 定位selector
    //个性
    public static By specFashionTextBy = MobileBy.AndroidUIAutomator(SPECAIL_FASHION_NAME);     //健康时尚tag 定位selector
    public static By specOutsideTextBy = MobileBy.AndroidUIAutomator(SPECAIL_OUTSIDE_NAME);     //户外旅行tag 定位selector
    public static By specExtreTextBy = MobileBy.AndroidUIAutomator(SPECAIL_EXTREME_NAME);       //竞技极限tag 定位selector
    public static By specGroupTextBy = MobileBy.AndroidUIAutomator(SPECAIL_GROUP_NAME);         //咕咚运动团tag 定位selector
    public static By specHotTextBy = MobileBy.AndroidUIAutomator(SPECAIL_HOT_NAME);             //热门活动tag 定位selector
    public static By specTriathTextBy = MobileBy.AndroidUIAutomator(SPECAIL_TRIATHLION_NAME);   //铁人三项tag 定位selector


    /* --------------------------- Find Element --------------------------------- */
    /*                 定义页面上的WebElement元素，在testcase中直接引用               */
    /* -------------------------------------------------------------------------- */

    @AndroidFindBy(uiAutomator = DRYCARGO_RECOMMEND_TAB_TEXT)
    public WebElement recommendTab;                                             //干货页面公共元素:-> 推荐tab WebElement
    @AndroidFindBy(uiAutomator = DRYCARGO_RUN_TAB_TEXT)
    public WebElement runTab;                                                   //干货页面公共元素:-> 跑步tab WebElement
    @AndroidFindBy(uiAutomator = DRYCARGO_RIDE_TAB_TEXT)
    public WebElement rideTab;                                                  //干货页面公共元素:-> 骑行tab WebElement
    @AndroidFindBy(uiAutomator = DRYCARGO_FITNESS_TAB_TEXT)
    public WebElement fitnessTab;                                               //干货页面公共元素:-> 健身tab WebElement
    @AndroidFindBy(uiAutomator = DRYCARGO_PERSON_TAB_TEXT)
    public WebElement specialTab;                                               //干货页面公共元素:-> 个性tab WebElement
    @AndroidFindBys({ @AndroidFindBy(id = RECOMMEND_PAGEVIEWER_ID), @AndroidFindBy(className = CLASSNAME_VIEWPAGER) })
//    @AndroidFindBys({ @AndroidFindBy(id = RECOMMEND_PAGEVIEWER_ID), @AndroidFindBy(className = CLASSNAME_HORIZONSCROLLVIEW) })
    public WebElement advertBanner;                                             //干货推荐页面元素:-> 广告banner WebElement
    @AndroidFindBy(uiAutomator = RECOMMEND_DAILYHOT_NAME)
    public WebElement dailyHot;                                                 //干货推荐页面元素:-> 今日热门tag WebElement
    @AndroidFindBy(uiAutomator = RECOMMEND_EXPLAN_NAME)
    public WebElement trainingPlan;                                             //干货推荐页面元素:-> 训练计划tag WebElement
    @AndroidFindBy(uiAutomator = MAYBE_LIKE_TEXT)
    public WebElement maybeLike;                                                //干货推荐页面元素:-> 猜你喜欢tag WebElement

    @FindBy(id = CARD_TITLE_ID)
    public WebElement cardTitle;                                               //干货页面公共元素:-> 文章卡片title WebElement
    @FindBy(id = CARD_AUTHOR_ID)
    public WebElement cardAuthor;                                              //干货页面公共元素:-> 文章卡片作者 WebElement
    @FindBy(id = USER_CARE_BTN_ID)
    public WebElement careBtn;                                                 //个人资料页面元素:-> 关注按钮 WebElement
    @FindBy(name = USER_CARED_BTN_ID)
    public WebElement caredBtn;                                                //个人资料页面元素:-> 已关注按钮 WebElement
    @FindBy(name = FOLLOWED_CANNEL_TEXT)
    public WebElement careCannelBtn;                                           //个人资料页面元素:-> 取消关注按钮 WebElement
    @FindBy(id = ADDINFO_EDIT_ID)
    public WebElement infoCommentEdit;                                        //干货 文章详情页:-> 评论输入框 WebElement
    @FindBy(id = SENDINFO_BTN_ID)
    public WebElement infoSendBtn;                                            //干货 文章详情页:-> 评论发送按钮 WebElement
    @FindBy(id = ARTICLE_COLLECT_BTN_ID)
    public WebElement articleCollectBtn;                                      //干货 文章详情页:-> 文章收藏按钮 WebElement
//    @AndroidFindBy(uiAutomator = VIEW_ALLINFO_INDETAIL)
    @FindBy(xpath = VIEW_ALLINFO_INDETAIL)
//    @FindBy(xpath = "//*[@resource-id=\"goto-comment-list\"]")
    public WebElement allComments;                                            //干货 文章详情页:-> 查看全部评论 WebElement


    private DrycargoPage(SikuppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), this);
        this.driver = driver;
        sessionId = driver.getSessionId();
    }

    public static DrycargoPage getInstance(SikuppiumDriver driver) {
        if (driver.getSessionId() != sessionId) {
            instance = new DrycargoPage(driver);
        }
        return instance;
    }

    /* --------  Logic Part  ---------- */
    public List<String> swipeLeftTrainingCard(WebElement viewer) throws InterruptedException {
        List<String> list = new ArrayList<>();
        String plan = "";

        mHelper.swipeRight(viewer, 3);
        while (true) {
            List<WebElement> planCard = mHelper.findElementsToDown(scrollViewClassBy,
                    viewClassBy, new Dimension(804, 321));
//                    groupViewClassBy, new Dimension(804, 321));
            for (WebElement el : planCard) {
                LOG.info(el.findElement(textViewClassBy).getText());
                if (plan.equals(el.findElement(textViewClassBy).getText())) {
                    return list;
                }
                plan = el.findElement(textViewClassBy).getText();
                list.add(plan);
            }
            mHelper.swipeLeft(viewer, 1);
            Thread.sleep(1000L);
        }
    }

    public void DrycargoPageClickMore(By by) throws InterruptedException {
        mHelper.searchBy(by, 5);
        mHelper.includeElementToRight(by, 0, MobileBy.AndroidUIAutomator(MORE_BTN_TEXT)).click();
        mHelper.waitForVisible(driver, textViewClassBy, 5);
    }


    public boolean trainingLinkDetails(WebElement viewer) throws InterruptedException {
        boolean flag = false;
        String plan = "";
        String on = "";
        int planCount = 0;
        mHelper.swipeRight(viewer, 3);
        for (;;) {
            List<WebElement> planCard = mHelper.findElementsToDown(scrollViewClassBy,
                    viewClassBy, new Dimension(804, 321));   //viewClassBy
//                    groupViewClassBy, new Dimension(804, 321));   //viewClassBy
            on = planCard.get(0).findElement(textViewClassBy).getText();
            if (on.equals(plan)) {
                break;
            }
            planCard.get(0).click();
            String detailName = mHelper.getTextByElement(trainDetailNameBy);
            LOG.info(on.equals(detailName));

            mHelper.pressBack();
            Thread.sleep(1000L);

            plan = detailName;
            planCount++;
            mHelper.swipeLeft(viewer, 1);
            Thread.sleep(1000L);
        }
        LOG.info("训练计划个数：" + planCount);
        if (planCount == 3) {
            flag = true;
        }
        return flag;
    }


    public boolean articleCardForwardingByTag(By tagby) throws InterruptedException {
        mHelper.searchBy(tagby, 3);
        mHelper.moveToHalfScreen(driver.findElement(tagby));
        List<WebElement> list = mHelper.findElementsToDown(tagby,
                By.xpath("//android.widget.RelativeLayout/.."), new Dimension(984, 330));
        String title = list.get(1).findElement(textViewClassBy).getText();
        LOG.info(title);
        list.get(1).click();
        String title1 = mHelper.getH5AtricleTitle();
        boolean exist = title1.equals(title);
        LOG.info(exist);
        mHelper.pressBack();
        return exist;
    }


//    public boolean drygoodJumpProfileAndCared(String author) throws InterruptedException{
//        webH5Helper.switchContext("WEBVIEW_com.codoon.gps");
//        Point point = driver.findElement(By.name(author)).getLocation();
//        boolean before = mHelper.isExistBySelector(driver, MobileBy.AndroidUIAutomator(FOLLOW_BTN_TEXT), 3);
//        if(before){
//            mHelper.tap(1, point.x/2, point.y, 1, 5);
//            careBtn.click();
//        }else{
//            mHelper.tap(1, point.x/2, point.y, 1, 5);
//            caredBtn.click();
//            careCannelBtn.click();
//        }
//        mHelper.pressBack();
//        boolean current = mHelper.isExistBySelector(driver,  MobileBy.AndroidUIAutomator(FOLLOW_BTN_TEXT), 3);
//        return (before==current);
//    }

    public boolean followedAuthorInH5(String author) throws InterruptedException{
//        LOG.info(driver.getPageSource());
        webH5Helper= WebH5Helper.getInstance(driver);
        webH5Helper.switchContext("WEBVIEW_com.codoon.gps");
//        LOG.info(driver.getPageSource());
//        if (!driver.findElement(By.id("user-nick")).getText().equals(author)){
//            return false;
//        }
//        String before = driver.findElement(By.xpath("//*[@resource-id='follow-btn']")).getAttribute("name");
        String before = driver.findElement(By.id("follow-btn")).getText();
        LOG.info(before);
//        driver.findElement(By.xpath("//*[@resource-id='follow-btn']")).click();
        driver.findElement(By.id("follow-btn")).click();
        Thread.sleep(3000L);
//        String after = driver.findElement(By.xpath("//*[@resource-id='follow-btn']")).getAttribute("name");
        String after = driver.findElement(By.id("follow-btn")).getText();
        LOG.info(after);
        webH5Helper.switchContext("NATIVE_APP");
        if(before.equals(followBtnText)){
            return after.equals(followedBtnText);
        } else {
            return after.equals(followBtnText);
        }
    }


    public boolean sendInfoForComment(String message) throws InterruptedException, IOException{
        mHelper.changePhoneIME("com.samsung.inputmethod/.SamsungIME");
        infoCommentEdit.sendKeys(message);
        infoSendBtn.click();
        webH5Helper= WebH5Helper.getInstance(driver);
        webH5Helper.switchContext("WEBVIEW_com.codoon.gps");
        boolean send = mHelper.isExistBySelector(driver,H5webtextEqualsBy(message),10);
//        boolean send = mHelper.searchBy(H5webtextEqualsBy(message),50);
//        boolean send = mHelper.searchBy(H5webNameEqualsBy(message),50);
//        LOG.info(driver.getPageSource());
        return send;
    }


    public boolean checkCollectionInMine(String str) throws InterruptedException {
        mHelper.pressBackToHomePage(5);
        HomePage.mineTab.click();
        mHelper.searchBy(MobileBy.AndroidUIAutomator("text(\"收藏\")"), 10);
        driver.findElement(MobileBy.AndroidUIAutomator("text(\"收藏\")")).click();
        Thread.sleep(2000L);
        driver.findElement(MobileBy.AndroidUIAutomator("text(\"文章\")")).click();
        Thread.sleep(2000L);
        return mHelper.searchBy(MobileBy.AndroidUIAutomator("text(\""+str+"\")"), 10);
    }


    /**
     * 查看文章中的全部评论
     * @return
     * @throws InterruptedException
     */
    public boolean showAllComments() throws InterruptedException{
        cardTitle.click();
        Thread.sleep(3000L);
        mHelper.swipeUpQuickly(20);
//        LOG.info(driver.getPageSource());
        webH5Helper= WebH5Helper.getInstance(driver);
        webH5Helper.switchContext("WEBVIEW_com.codoon.gps");
        allComments.click();
        webH5Helper.switchContext("NATIVE_APP");
        Thread.sleep(5000L);
        boolean right = mHelper.isExistBySelector(driver, MobileBy.AndroidUIAutomator("text(\"评论\")"),5);
        return right;
    }
}
