package io.driver;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.sikuli.api.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by huangjingqing on 17/5/16.
 */
public class PhoneScreen implements Screen {

    public AppiumDriver driver;
    private Dimension size;

    public PhoneScreen(AppiumDriver driver) throws IOException {
        this.driver = driver;
        size = new Dimension(driver.manage().window().getSize().getWidth(), driver.manage().window().getSize().getHeight());
    }

    @Override
    public BufferedImage getScreenshot(int x, int y, int width, int height) {
        File screenshotFile = driver.getScreenshotAs(OutputType.FILE);
        try {
            BufferedImage full = ImageIO.read(screenshotFile);
            BufferedImage cropped = crop(full, x, y, width, height);
//            size = new Dimension(full.getWidth(), full.getHeight());
            return cropped;
        } catch (IOException e) {
        }
        return null;
    }

    private BufferedImage crop(BufferedImage src, int x, int y, int width, int height) {
        BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics graphics = dest.getGraphics();
        graphics.drawImage(src, 0, 0, width, height, x, y, x + width, y + height, null);
        graphics.dispose();
        return dest;
    }

    @Override
    public java.awt.Dimension getSize() {
        return new java.awt.Dimension(size.getWidth(), size.getHeight());
    }
}