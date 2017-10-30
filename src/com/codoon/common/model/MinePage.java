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
import java.util.concurrent.TimeUnit;

import static com.codoon.common.model.mine.Common.*;
import static com.codoon.common.model.mine.Profile.*;

public class MinePage {
    private static MinePage instance;
    private SikuppiumDriver driver;
    private static SessionId sessionId;
    DeviceHelper mHelper= DeviceHelper.getInstance(driver);

    /*--------------  By selector  -----------------*/
    public static By editTitleBy = MobileBy.AndroidUIAutomator(PROFILE_EDITTITLE_TEXT);
    public static By caredTitleBy = MobileBy.AndroidUIAutomator(PROFILE_CAREDTITLE_TEXT);
    public static By fansTitleBy = MobileBy.AndroidUIAutomator(PROFILE_FANSTITLE_TEXT);

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
