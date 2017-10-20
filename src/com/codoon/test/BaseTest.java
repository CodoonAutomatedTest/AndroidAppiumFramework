package com.codoon.test;


import com.codoon.common.model.HomePage;
import com.codoon.common.util.DeviceHelper;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.driver.CapabilitiesFactory;
import io.driver.ImageElement;
import io.driver.SikuppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertTrue;

public class BaseTest {
	private final static Logger LOG = Logger.getLogger(BaseTest.class);
	
	protected SikuppiumDriver driver;
	public static DeviceHelper mHelper;
    private static HomePage homePage;
	private static int DISPLAY_WIDTH;
	private static int DISPLAY_HEIGHT;
	static int TIMEOUT = 5000;
    

    @BeforeClass(alwaysRun=true)
    public void setUpClass() throws Exception {

        String defaultIME = getDefaultIME();

        driver = new SikuppiumDriver(
                new URL("http://127.0.0.1:4723/wd/hub"),
                CapabilitiesFactory.getCapabilities()
        );
        Thread.sleep(10000);
        LOG.info("driver启动完毕，开始初始化testsuit...");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.setDriver(driver);
        driver.setSimilarityScore(0.95);
        driver.setWaitSecondsAfterClick(2);
        driver.setWaitSecondsForImage(10);
        
        PageFactory.initElements(new AppiumFieldDecorator(driver, 1, TimeUnit.SECONDS), this);
		mHelper = DeviceHelper.getInstance(driver);
        homePage = HomePage.getInstance(driver);
		mHelper.setDefaultIme(defaultIME);
		DISPLAY_WIDTH = driver.manage().window().getSize().width;
		DISPLAY_HEIGHT = driver.manage().window().getSize().height;

		runTestBefore();
        
    }

    @AfterClass(alwaysRun=true)
    public void tearDownClass() {
    	LOG.info("testsuit执行完毕，关闭driver");
        if (driver != null) {
            driver.quit();
        }
    }

    public String getDefaultIME() {
        Process process = null;
        String line = "";
        String ime = "";
        List<String> processList = new ArrayList<String>();
        try {
            process = Runtime.getRuntime().exec("adb shell settings get secure default_input_method");
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = input.readLine()) != null) {
                ime = line;
                processList.add(line);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOG.info(ime);
        return ime;
    }

    protected ImageElement waitForImageElement(String resourceName, int secondsToWait) throws InterruptedException, MalformedURLException {
        sleep(2000);
//        String prefix = String.valueOf(driver.getSize().getWidth()) + "x_";
//        resourceName = prefix.replace(".0", "") + resourceName + ".png";
//        URL resource = this.getClass().getClassLoader().getResource(resourceName);
//        System.out.println(resource);
        ImageElement image = driver.findImageElement(resourceName);

        int attempts = 0;
        while (image == null && attempts < secondsToWait / 10) {
            sleep(10000);

            image = driver.findImageElement(resourceName);
            attempts++;
            if (image != null) {
                break;
            }
        }
        assertTrue(image != null, String.format("Cannot find image %s in %s seconds", resourceName, String.valueOf(secondsToWait)));
        return image;
    }

    protected boolean isImageExist(String resourceName, int secondsToWait) throws InterruptedException, MalformedURLException {
        sleep(2000);
        boolean result=false;
//        String prefix = String.valueOf(driver.getSize().getWidth()) + "x_";
//        resourceName = prefix.replace(".0", "") + resourceName + ".png";
//        URL resource = this.getClass().getClassLoader().getResource(resourceName);
//        System.out.println(resource);
        ImageElement image = driver.findImageElement(resourceName);

        int attempts = 0;
        while (image == null && attempts < secondsToWait / 10) {
            sleep(10000);
            image = driver.findImageElement(resourceName);
            attempts++;
            if (image != null) {
                result=true;
                break;
            }
        }
        if(image !=null){
            result=true;
        }
        return result;
    }

    protected double imageMatch(String resourceName, int[] value, int secondsToWait) throws InterruptedException, IOException {
        sleep(2000);
        boolean result=false;
//        String prefix = String.valueOf(driver.getSize().getWidth()) + "x_";
//        resourceName = prefix.replace(".0", "") + resourceName + ".png";
//        URL resource = this.getClass().getClassLoader().getResource(resourceName);
//        System.out.println(resource);


//        String prefix = String.valueOf(driver.getSize().getWidth()) + "x_";
//        resourceName = prefix.replace(".0", "") + resourceName + ".png";
//        File path = new File("src/resources/"+ resourceName);
//        URL resource = path.toURL();
//        System.out.println(resource);
        double score = driver.findImageElement(resourceName,value);

        int attempts = 0;
        while (score == 0.0 && attempts < secondsToWait / 10) {
            sleep(10000);
            score = driver.findImageElement(resourceName,value);
            attempts++;
            if (score > 0) {
                result=true;
                break;
            }
        }
        return score;
    }

    private void runTestBefore() throws InterruptedException {
        LOG.info("开始执行用例的前置操作...");
        if (driver.currentActivity()==""){
            this.login();
        }
        if (mHelper.isExistBySelector(driver, homePage.advPopupCloseBy,1)){
            LOG.info("发现广告浮层框...");
            driver.findElement(homePage.advPopupCloseBy).click();
        }
        if (mHelper.isExistBySelector(driver, homePage.descripCloseBy,1)){
            LOG.info("发现灰测介绍框...");
            driver.findElement(homePage.descripCloseBy).click();
        }
    }

    private void login() throws InterruptedException {
        while (driver.currentActivity().equals(".ui.login.LoginActivity")){
            if (mHelper.isExistBySelector(driver,homePage.acceptOkBy,1)){
                driver.findElement(homePage.acceptOkBy).click();
            }
            driver.findElement(By.id("com.codoon.gps:id/tv_login")).click();
            driver.findElement(By.id("com.codoon.gps:id/login_text_account")).sendKeys("002@test.com");
            driver.findElement(By.id("com.codoon.gps:id/login_text_password")).sendKeys("123456");
            driver.findElement(By.id("com.codoon.gps:id/login_btn_experience")).click();
        }
        Thread.sleep(10*1000);
    }
}

