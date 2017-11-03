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
import static com.codoon.common.model.home.Common.*;

public class HomePage {
    private static HomePage instance;
    private SikuppiumDriver driver;
    private static SessionId sessionId;
    DeviceHelper mHelper= DeviceHelper.getInstance(driver);

    /*--------------  By selector  -----------------*/
    public static By sportCircleBy = MobileBy.AndroidUIAutomator(HOMEPAGE_SPORTSCIRCLE_TEXT);
    public static By sportsBy = MobileBy.AndroidUIAutomator(HOMEPAGE_SPORTS_TEXT);
    public static By discoverBy = MobileBy.AndroidUIAutomator(HOMEPAGE_FIND_TEXT);
    public static By drycargoBy = MobileBy.AndroidUIAutomator(HOMEPAGE_DRYCARGO_TEXT);
    public static By mineBy = MobileBy.AndroidUIAutomator(HOMEPAGE_MINE_TEXT);

    public static By startSportsBy = MobileBy.AndroidUIAutomator(HOMEPAGE_GOSPORTS_ID);
    public static By advPopupBy = MobileBy.AndroidUIAutomator(HOMEPAGE_ADVER_ID);
    public static By advPopupCloseBy = MobileBy.AndroidUIAutomator(HOMEPAGE_ADVERCLOSE_ID);
    public static By descripBy = MobileBy.AndroidUIAutomator(HOMEPAGE_DESCRIPTION_ID);
    public static By descripCloseBy = MobileBy.AndroidUIAutomator(HOMEPAGE_DESCRIPTIONCLOSE_TEXT);
//    public static By acceptOkBy = MobileBy.AndroidUIAutomator(LOGINPAGE_ACCPET_TEXT);
    public static By acceptOkBy = MobileBy.AndroidUIAutomator(LOGINPAGE_ACCPET_TEXT);


    /*---------------------------------- find -----------------------------------------*/

    @AndroidFindBy(uiAutomator = HOMEPAGE_SPORTSCIRCLE_TEXT)
    public static WebElement sportcircleTab;
    @AndroidFindBy(uiAutomator = HOMEPAGE_FIND_TEXT)
    public static WebElement discoveryTab;
    @AndroidFindBy(uiAutomator = HOMEPAGE_SPORTS_TEXT)
    public static WebElement sportsTab;
    @AndroidFindBy(uiAutomator = HOMEPAGE_DRYCARGO_TEXT)
    public static WebElement drycargoTab;
    @AndroidFindBy(uiAutomator = HOMEPAGE_MINE_TEXT)
    public static WebElement mineTab;
    @AndroidFindBy(uiAutomator = HOMEPAGE_GOSPORTS_ID)
    public static WebElement startSportsBtn;
    @AndroidFindBy(uiAutomator = HOMEPAGE_ADVERCLOSE_ID)
    public static WebElement advrCloseBtn;
    @AndroidFindBy(uiAutomator = HOMEPAGE_DESCRIPTIONCLOSE_TEXT)
    public static WebElement descripCloseBtn;


    private HomePage(SikuppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), this);
        this.driver = driver;
        sessionId = driver.getSessionId();
    }

    public static HomePage getInstance(SikuppiumDriver driver) {
        if (driver.getSessionId() != sessionId) {
            instance = new HomePage(driver);
        }
        return instance;
    }
}
