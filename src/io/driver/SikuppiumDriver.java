package io.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Capabilities;
import org.sikuli.api.DefaultScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by huangjingqing on 17/5/16.
 */
public class SikuppiumDriver extends AndroidDriver {

    private final static Logger LOG = LoggerFactory.getLogger(SikuppiumDriver.class);

    private int waitSecondsAfterClick = 2;
    private  double similarityScore = 0.99;
    private int waitSecondsForImage = 30;
    private AppiumDriver driver;
    private PhoneScreen driverScreen;

    public SikuppiumDriver(URL remoteAddress, Capabilities desiredCapabilities) {
        super(remoteAddress, desiredCapabilities);
    }


    public ImageElement findImageElement(String imageUrl) throws MalformedURLException {
        String prefix = String.valueOf(getSize().getWidth()) + "x_";
        String resourceName = prefix.replace(".0", "") + imageUrl;
        File path = new File("src/resources/"+ resourceName);
        URL resource = path.toURL();
        System.out.println(resource);

        try {
            driverScreen = new PhoneScreen(driver);
        } catch (IOException e1) {
            throw new RuntimeException("Unable to init Driver");
        }

        ScreenRegion webdriverRegion = new DefaultScreenRegion(driverScreen);
        webdriverRegion.setScore(similarityScore);

        ImageTarget target = new ImageTarget(resource);
//        ScreenRegion imageRegion = webdriverRegion.wait(target, waitSecondsForImage);
        ScreenRegion imageRegion = webdriverRegion.find(target);

        Rectangle rectangle;

        if (imageRegion != null) {
            rectangle = imageRegion.getBounds();
            LOG.info("Image is found at {} {} {} {}", rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        } else {
            LOG.info("Image is not found");
            return null;
        }

        return new CustomImageElement(
                this.driver,
                rectangle.x,
                rectangle.y,
                rectangle.width,
                rectangle.height,
                this.waitSecondsAfterClick
        );
    }

    public double findImageElement(String imageUrl, int[] value) throws IOException {
        String prefix = String.valueOf(getSize().getWidth()) + "x_";
        String resourceName = prefix.replace(".0", "") + imageUrl + ".png";
        File path = new File("src/resources/"+ resourceName);
        URL resource = path.toURL();
        System.out.println(resource);

        try {
            driverScreen = new PhoneScreen(driver);
        } catch (IOException e1) {
            throw new RuntimeException("Unable to init SikkupiumDriver");
        }

        DefaultScreenRegion webdriverRegion;
        if(value==null){
            webdriverRegion = new DefaultScreenRegion(driverScreen);
        }else{
            webdriverRegion = new DefaultScreenRegion(driverScreen,value[0],value[1],value[2],value[3]);
        }

        webdriverRegion.setScore(similarityScore);

        ImageTarget target = new ImageTarget(resource);
        ScreenRegion imageRegion = webdriverRegion.wait(target, waitSecondsForImage);

        Rectangle rectangle;

        if (imageRegion != null) {
            rectangle = imageRegion.getBounds();
            LOG.info("x:"+rectangle.x+", y:"+rectangle.y+", width:"+rectangle.width+", height:"+rectangle.height);
        } else {
            LOG.info("Image is not found");
            return 0.0;
        }
        System.out.println("Score:"+imageRegion.getScore());

        return imageRegion.getScore();
    }

    public Dimension getSize() {
        try {
            driverScreen = new PhoneScreen(driver);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return driverScreen.getSize();
    }

    public void setWaitSecondsAfterClick(int waitSecondsAfterClick) {
        this.waitSecondsAfterClick = waitSecondsAfterClick;
    }

    public void setSimilarityScore(double similarityScore) {
        this.similarityScore = similarityScore;
    }

    public void setWaitSecondsForImage(int waitSecondsForImage) {
        this.waitSecondsForImage = waitSecondsForImage;
    }

    public void setDriver(AppiumDriver driver) {
        this.driver = driver;
    }

}
