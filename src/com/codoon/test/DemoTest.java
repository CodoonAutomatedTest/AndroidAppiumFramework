package com.codoon.test;


import com.codoon.common.model.HomePage;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class DemoTest extends BaseTest {
    private final static Logger LOG = Logger.getLogger(DemoTest.class);
    private static HomePage mainPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        mainPage = HomePage.getInstance(driver);
        LOG.info("进入运动圈tab首页");
        mainPage.sportcircleTab.click();
        mainPage.discoveryTab.click();
        mainPage.sportsTab.click();
        mainPage.drycargoTab.click();
        mainPage.mineTab.click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        LOG.info("当前case执行完成，返回到App一级界面");
        mHelper.pressBackToHomePage(5);
    }

    @Test(groups = { "other" })
    public void test001() {
        ((Map) driver.getCapabilities().getCapability("desired")).get("platformVersion");
        ((Map) driver.getCapabilities().getCapability("desired")).get("deviceName");
    }
}
