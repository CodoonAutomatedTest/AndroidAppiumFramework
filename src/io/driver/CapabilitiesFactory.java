package io.driver;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by huangjingqing on 17/5/16.
 */
public class CapabilitiesFactory {

    protected static DesiredCapabilities capabilities;

    public static DesiredCapabilities getCapabilities() throws Exception {

        capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName", "e6a29a3a");
//        capabilities.setCapability("deviceName", "125683fc");
//        capabilities.setCapability("platformVersion", "6.0.1");
        capabilities.setCapability("platformVersion", "5.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        //以下两项可以通过appium客户端查看
        capabilities.setCapability("appPackage", "com.codoon.gps");
        capabilities.setCapability("appActivity", ".ui.SlideActivity");
        //支持中文输入
        capabilities.setCapability("unicodeKeyboard", true);
        //重置输入法为系统默认
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setCapability("sessionOverride", true);
        capabilities.setCapability("recreateChromeDriverSessions",true);

        return capabilities;
    }

    public static Object getCapability(String s){
        return capabilities.getCapability(s);
    }

}
