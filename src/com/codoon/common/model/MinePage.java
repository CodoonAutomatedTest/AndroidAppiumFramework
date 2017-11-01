package com.codoon.common.model;

import com.codoon.common.util.DeviceHelper;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.driver.SikuppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.codoon.common.model.mine.Common.*;
import static com.codoon.common.model.mine.Profile.*;
import static com.codoon.common.model.mine.History.*;

public class MinePage {
    private static MinePage instance;
    private SikuppiumDriver driver;
    private static SessionId sessionId;
    DeviceHelper mHelper= DeviceHelper.getInstance(driver);

    /*--------------  By selector  -----------------*/
    public static By editTitleBy = MobileBy.AndroidUIAutomator(PROFILE_EDITTITLE_TEXT);
    public static By caredTitleBy = MobileBy.AndroidUIAutomator(PROFILE_CAREDTITLE_TEXT);
    public static By fansTitleBy = MobileBy.AndroidUIAutomator(PROFILE_FANSTITLE_TEXT);
    public static By groupBy = MobileBy.AndroidUIAutomator(PROFILE_GROUP_TEXT);
    public static By groupTitleBy = MobileBy.AndroidUIAutomator(PROFILE_GROUP_HOMEPAGE_TEXT);
    public static By honnorWallTitleBy = MobileBy.AndroidUIAutomator(PROFILE_HONNOR_WALL_TEXT);
    public static By honnorWallBy = By.id(PROFILE_SPORT_METAL_ID);
    public static By runLevelBy = By.id(PROFILE_RUN_LEVEL_ID);

    /*---------------------------------- find -----------------------------------------*/
    @FindBy(id = MY_HEAD_IMG_ID)
    public WebElement headIcon;
    @AndroidFindBy(uiAutomator = PROFILE_EDIT_TEXT)
    public WebElement editProfileBtn;
    @AndroidFindBy(uiAutomator = PROFILE_EDIT_HEAD_TEXT)
    public WebElement editHeadBtn;
    @AndroidFindBy(uiAutomator = PROFILE_EDIT_NICK_TEXT)
    public WebElement editNickBtn;
    @AndroidFindBy(uiAutomator = PROFILE_EDIT_SEX_TEXT)
    public WebElement editSexBtn;
    @AndroidFindBy(uiAutomator = PROFILE_EDIT_INFO_TEXT)
    public WebElement editInfoBtn;
    @AndroidFindBy(uiAutomator = PROFILE_EDIT_INTER_TEXT)
    public WebElement editInterBtn;
    @AndroidFindBy(uiAutomator = PROFILE_EDIT_AGE_TEXT)
    public WebElement editAgeBtn;
    @AndroidFindBy(uiAutomator = PROFILE_EDIT_HEIGH_TEXT)
    public WebElement editHeighBtn;
    @AndroidFindBy(uiAutomator = PROFILE_EDIT_WEIGHT_TEXT)
    public WebElement editWeightBtn;
    @AndroidFindBy(uiAutomator = PROFILE_CANCEL_TEXT)
    public WebElement cancelEditBtn;
    @FindBy(id = PROFILE_CARED_LABEL_ID)
    public WebElement caredLabel;
    @FindBy(id = PROFILE_FANS_LABEL_ID)
    public WebElement fansLabel;
    @FindBy(id = PROFILE_RUN_LEVEL_ID)
    public WebElement runLevelLabel;
    @FindBy(id = PROFILE_WALK_LEVEL_ID)
    public WebElement walkLevelLabel;
    @FindBy(id = PROFILE_RIDE_LEVEL_ID)
    public WebElement rideLevelLabel;
    @FindBy(id = PROFILE_SPORTS_TOTAL_ID)
    public WebElement sportsTotalLabel;
    @FindBy(id = PROFILE_SPORTS_MONTH_ID)
    public WebElement sportsMonthLabel;
    @AndroidFindBy(uiAutomator = MY_HISTORY_VIEW_TEXT)
    public WebElement historyViewBtn;
    @AndroidFindBy(uiAutomator = MY_SPORTSDATA_STAT_TEXT)
    public WebElement statChartBtn;
    @AndroidFindBy(uiAutomator = MY_SPORTSDATA_WEEK_TEXT)
    public WebElement weekBtn;
    @AndroidFindBy(uiAutomator = MY_SPORTSDATA_MONTH_TEXT)
    public WebElement monthBtn;
    @AndroidFindBy(uiAutomator = MY_SPORTSDATA_ALL_TEXT)
    public WebElement allBtn;
    @FindBy(id = MY_SPORTSSTAT_TITLE_ID)
    public WebElement sportsTypeLabel;
    @FindBy(id = MY_SPORTS_VALUE_ID)
    public WebElement dataValueLabel;
    @FindBy(id = PROFILE_GROUP_ID)
    public WebElement groupCard;


    /*---------------------------------- logic -----------------------------------------*/
    public List<Float> getSportsData(String imageElement) throws InterruptedException, MalformedURLException {
        List<Float> dataList = new ArrayList<Float>();
        float monthData;
        float totalData;
        sportsTypeLabel.click();
        if (mHelper.isExistImageElement(imageElement,1)){
            driver.findImageElement(imageElement+".png").tap();
        }else{
            mHelper.pressBack(1);
        }
        monthBtn.click();
        if (dataValueLabel.getText().equals("--")){
            monthData = 0;
        }else{
            monthData = Float.parseFloat(dataValueLabel.getText());
        }
        dataList.add(monthData);
        allBtn.click();
        if (dataValueLabel.getText().equals("--")){
            totalData = 0;
        } else {
            totalData = Float.parseFloat(dataValueLabel.getText());
        }
        dataList.add(totalData);
        return dataList;
    }

    public boolean shareChecker() throws MalformedURLException, InterruptedException {
        driver.findImageElement("share.png").tap();
        boolean b1 = mHelper.isExistImageElement("sportcircle_share",1);
        boolean b2 = mHelper.isExistImageElement("qq_share",1);
        boolean b3 = mHelper.isExistImageElement("wechat_share",1);
        boolean b4 = mHelper.isExistImageElement("friendcircle_share",1);
        boolean b5 = mHelper.isExistImageElement("codoon_share",1);
        boolean b6 = mHelper.isExistImageElement("qqzone_share",1);
        return b1 && b2 && b3 && b4 && b5 && b6;
    }

    public boolean shareMedalChecker() throws MalformedURLException, InterruptedException {
        Thread.sleep(2000L);
        driver.findImageElement("7days_keeping.png").tap();
        boolean b1 = mHelper.isExistImageElement("7days_keeping_detail",1);
        return b1;
    }

    private MinePage(SikuppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), this);
        this.driver = driver;
        sessionId = driver.getSessionId();
    }

    public static MinePage getInstance(SikuppiumDriver driver) {
        if (driver.getSessionId() != sessionId) {
            instance = new MinePage(driver);
        }
        return instance;
    }
}
