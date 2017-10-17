package com.codoon.common.util;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.driver.SikuppiumDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangjingqing on 17/5/11.
 */
public class WebH5Helper {
    private static WebH5Helper instance;
    private SikuppiumDriver driver;
    private static SessionId sessionId;

    private WebH5Helper(SikuppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), this);
        this.driver = driver;
        sessionId = driver.getSessionId();
    }

    public static WebH5Helper getInstance(SikuppiumDriver driver) {
//        if(instance == null) {
//            instance = new WebH5Helper(driver);
//        }
        if (driver == null) {
            return instance;
        } else if (driver.getSessionId() != sessionId) {
            instance = new WebH5Helper(driver);
        }
        return instance;
    }

    /**
     * NATIVE_APP , WEBVIEW_undefined
     * @param context
     */
    public void switchContext(String context){
        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            System.out.println(contextName);
            if (contextName.contains(context)){
                driver.context(contextName);
            }else
            {
                System.out.println("no "+context);
            }
        }
    }
}
