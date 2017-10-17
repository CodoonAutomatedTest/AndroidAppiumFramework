package com.codoon.test;


import com.codoon.common.util.DeviceHelper;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.driver.CapabilitiesFactory;
import io.driver.ImageElement;
import io.driver.SikuppiumDriver;
import org.apache.log4j.Logger;
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
		mHelper.setDefaultIme(defaultIME);
		DISPLAY_WIDTH = driver.manage().window().getSize().width;
		DISPLAY_HEIGHT = driver.manage().window().getSize().height;
        
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
}

