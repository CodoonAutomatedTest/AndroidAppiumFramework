package com.codoon.common.util;

import com.codoon.common.model.HomePage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.TouchAction;
import io.driver.CapabilitiesFactory;
import io.driver.ImageElement;
import io.driver.SikuppiumDriver;
import io.utils.Rect;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.SessionId;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertTrue;

/**
 * Created by huangjingqing on 17/3/28.
 */
public class DeviceHelper {
	private final static Logger LOG = Logger.getLogger(DeviceHelper.class);
	private final static String ADB_PATH = "/Users/xiaoq/android-sdk-macosx/platform-tools/";

	private static DeviceHelper instance;
    private static WebH5Helper webH5Helper;
	private SikuppiumDriver driver;
	private static SessionId sessionId;
	private static int DISPLAY_WIDTH;
	private static int DISPLAY_HEIGHT;
	private static String DEFAULT_IME;

	public void setDefaultIme(String s){
	    DEFAULT_IME = s;
    }

	private DeviceHelper(SikuppiumDriver driver) {
		this.driver = driver;
		sessionId = driver.getSessionId();
	}

	public static DeviceHelper getInstance(SikuppiumDriver driver) {
		if (driver == null) {
			return instance;
		} else if (driver.getSessionId() != sessionId) {
			instance = new DeviceHelper(driver);
			DISPLAY_WIDTH = driver.manage().window().getSize().width;
			DISPLAY_HEIGHT = driver.manage().window().getSize().height;
		}
		return instance;
	}

	/**
	 * 滑动
	 *
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 * @param steps
	 *            步长
	 * @throws InterruptedException
	 */
	public void swipe(int startX, int startY, int endX, int endY, int steps) throws InterruptedException {
		Thread.sleep(1000L);
		driver.swipe(startX, startY, endX, endY, steps);
	}

	/**
	 * 以常速向上滑动半屏
	 *
	 * @throws InterruptedException
	 */
	public void swipeUp() throws InterruptedException {
		// Thread.sleep(1000L);
		driver.swipe(DISPLAY_WIDTH / 2, DISPLAY_HEIGHT * 3 / 4, DISPLAY_WIDTH / 2, DISPLAY_HEIGHT / 4, 4000);
	}

	public void swipeUpQuickly(int times) throws InterruptedException {
		// Thread.sleep(1000L);
		for (int i = 0; i < times; i++) {
			driver.swipe(DISPLAY_WIDTH / 2, DISPLAY_HEIGHT * 3 / 4, DISPLAY_WIDTH / 2, DISPLAY_HEIGHT / 4, 600);
			Thread.sleep(1000L);
		}
	}

	public void swipeDownQuickly(int times) throws InterruptedException {
		// Thread.sleep(1000L);
		for (int i = 0; i < times; i++) {
			driver.swipe(DISPLAY_WIDTH / 2, DISPLAY_HEIGHT / 5, DISPLAY_WIDTH / 2, DISPLAY_HEIGHT * 4 / 5, 600);
			Thread.sleep(1000L);
		}
	}

	/**
	 * 以常速向上滑动半屏
	 *
	 * @param times
	 *            步数
	 * @throws InterruptedException
	 */
	public void swipeUp(int times) throws InterruptedException {
		for (int i = 0; i < times; i++) {
			driver.swipe(DISPLAY_WIDTH / 2, DISPLAY_HEIGHT * 4 / 5, DISPLAY_WIDTH / 2, DISPLAY_HEIGHT / 5, 4000);
			Thread.sleep(1000L);
		}
	}

	/**
	 * 以常速向下滑动半屏
	 *
	 * @throws InterruptedException
	 */
	public void swipeDown() throws InterruptedException {
		driver.swipe(DISPLAY_WIDTH / 2, DISPLAY_HEIGHT / 4, DISPLAY_WIDTH / 2, DISPLAY_HEIGHT * 3 / 4, 2000);
		Thread.sleep(1000L);
	}

	/**
	 * 以慢速向下滑动一点点
	 *
	 * @throws InterruptedException
	 */
	public void swipeDownSlowly() throws InterruptedException {
		driver.swipe(DISPLAY_WIDTH / 2, DISPLAY_HEIGHT / 4, DISPLAY_WIDTH / 2, DISPLAY_HEIGHT / 2, 6000);
		Thread.sleep(1000L);
	}

	/**
	 * 以常速向上滑动半屏
	 *
	 * @param times
	 *            步数
	 * @throws InterruptedException
	 */
	public void swipeDown(int times) throws InterruptedException {
		Thread.sleep(1000);
		for (int i = 0; i < times; i++) {
			driver.swipe(DISPLAY_WIDTH / 2, DISPLAY_HEIGHT / 4, DISPLAY_WIDTH / 2, DISPLAY_HEIGHT * 3 / 4, 4000);
			Thread.sleep(1000L);
		}
	}

	/**
	 * 以常速向左滑动
	 *
	 * @throws InterruptedException
	 */
	public void swipeLeft() throws InterruptedException {
		Thread.sleep(1000L);
		this.swipe((DISPLAY_WIDTH * 4) / 5, DISPLAY_HEIGHT / 2, DISPLAY_WIDTH / 5, DISPLAY_HEIGHT / 2, 500);
	}

	/**
	 * 以常速向左滑动
	 *
	 * @param times
	 *            步数
	 * @throws InterruptedException
	 */
	public void swipeLeft(int times) throws InterruptedException {
		Thread.sleep(1000L);
		for (int i = 0; i < times; i++) {
			this.swipe((DISPLAY_WIDTH * 3) / 4, DISPLAY_HEIGHT / 2, 0, DISPLAY_HEIGHT / 2, 4000);
			Thread.sleep(1000L);
		}
	}

	/**
	 * 以常速向右滑动
	 *
	 * @throws InterruptedException
	 */
	public void swipeRight() throws InterruptedException {
		Thread.sleep(1000L);
		this.swipe(DISPLAY_WIDTH / 4, DISPLAY_HEIGHT / 2, DISPLAY_WIDTH, DISPLAY_HEIGHT / 2, 4000);
	}

	/**
	 * 以常速向右滑动元素
	 * 
	 * @param el
	 * @throws InterruptedException
	 */
	public void swipeRight(WebElement el, int times) throws InterruptedException {
		int startX = el.getLocation().x;
		int startY = el.getLocation().y;
		int endX = el.getSize().width + el.getLocation().x;
		int endY = el.getSize().height + el.getLocation().y;
		int centerX = (startX + endX) / 2;
		int centerY = (startY + endY) / 2;
		for (; times > 0; times--) {
			Thread.sleep(1000L);
			this.swipe(centerX / 5, centerY, centerX * 5 / 3, centerY, 2000);
		}
	}

	/**
	 * 以常速向右滑动元素
	 * 
	 * @param el
	 * @throws InterruptedException
	 */
	public void swipeLeft(WebElement el, int times) throws InterruptedException {
		int startX = el.getLocation().x;
		int startY = el.getLocation().y;
		int endX = el.getSize().width + el.getLocation().x;
		int endY = el.getSize().height + el.getLocation().y;
		int centerX = (startX + endX) / 2;
		int centerY = (startY + endY) / 2;
		for (; times > 0; times--) {
			Thread.sleep(1000L);
			this.swipe(centerX * 5 / 3, centerY, centerX / 5, centerY, 2000);
		}
	}

	/**
	 * 以常速向右滑动
	 *
	 * @param times
	 *            步长
	 * @throws InterruptedException
	 */
	public void swipeRight(int times) throws InterruptedException {
		Thread.sleep(1000L);
		for (int i = 0; i <= times; i++) {
			this.swipe(DISPLAY_WIDTH / 5, DISPLAY_HEIGHT / 2, DISPLAY_WIDTH * 4 / 5, DISPLAY_HEIGHT / 2, 1000);
			Thread.sleep(1000L);
		}
	}

	/**
	 * 长按
	 *
	 * @param x
	 * @param y
	 * @param duration
	 *            持续时间
	 */
	public void longClick(int x, int y, int duration) {
		new TouchAction(driver).longPress(x, y, duration);
	}

	/**
	 * 长按
	 *
	 * @param el
	 * @param duration
	 */
	public void longClick(WebElement el, int duration) {
		new TouchAction(driver).longPress(el, duration);
	}

	/**
	 * 长按
	 *
	 * @param x
	 * @param y
	 * @param duration
	 *            持续时间
	 */
	public void longClick2(int x, int y, int duration) {
		driver.tap(1, x, y, duration);
	}

	/**
	 * 长按
	 *
	 * @param el
	 * @param duration
	 */
	public void longClick2(WebElement el, int duration) {
		driver.tap(1, el, duration);
	}

	/**
	 * 移动
	 *
	 * @param el
	 * @param x
	 * @param y
	 */
	public void moveTo(WebElement el, int x, int y) {
		new TouchAction(driver).longPress(el).moveTo(x, y).release().perform();
	}

	/**
	 * 元素移动到顶部可显示位置
	 * 
	 * @param el
	 */
	public void moveToViewTop(WebElement el) {
		// driver.findElementById("com.codoon.gps:id/tv_content")
		this.moveTo(el, el.getLocation().x + el.getSize().width / 2,
				driver.findElementById("com.codoon.gps:id/scroll_view").getLocation().y
						+ driver.findElementById("com.codoon.gps:id/scroll_view").getSize().height - 20);
	}

	/**
	 * 元素移动到半屏位置
	 *
	 * @param el
	 */
	public void moveToHalfScreen(WebElement el) {
		driver.swipe(el.getLocation().x, el.getLocation().y, el.getLocation().x, driver.getSize().height / 2, 4000);
	}

	/**
	 * 移动
	 *
	 * @param moveE1
	 * @param toE2
	 */
	public void moveTo(WebElement moveE1, WebElement toE2) {
		new TouchAction(driver).longPress(moveE1).moveTo(toE2).release().perform();
	}

	/**
	 * 按下电源键
	 */
	public void pressPower() {
		driver.sendKeyEvent(26);
	}

	/**
	 * 音量+
	 */
	public void pressVolumeeUp() {
		driver.sendKeyEvent(24);
	}

	/**
	 * 音量-
	 */
	public void pressVolumeDown() {
		driver.sendKeyEvent(25);
	}

	/**
	 * 扬声器静音键
	 */
	public void pressVolumeMute() {
		driver.sendKeyEvent(164);
	}

	/**
	 * 按下home键
	 */
	public void pressHome() {
		driver.sendKeyEvent(3);
	}

	/**
	 * back返回
	 *
	 * @param times
	 * @return
	 * @throws InterruptedException
	 */
	public boolean pressBack(int times) throws InterruptedException {
		if (times <= 0) {
			return false;
		} else {
			int i = 0;

			while (i < times) {
				++i;
				driver.sendKeyEvent(4);
				Thread.sleep(1000L);
			}
			return true;
		}
	}

	/**
	 * back返回
	 *
	 * @return
	 * @throws InterruptedException
	 */
	public boolean pressBack() throws InterruptedException {
		return this.pressBack(1);
	}

	/**
	 * back返回一级主页
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public boolean pressBackToHomePage(int times) throws InterruptedException {
	    if (!driver.getContext().equals("NATIVE_APP")){
	        driver.context("NATIVE_APP");
        }

		while (!driver.currentActivity().equals(".ui.SlideActivity")) {
			LOG.info(driver.currentActivity());
			this.pressBack(1);
			times--;
			if (times < 0) {
				return false;
			}
			if (isExistBySelector(driver, MobileBy.AndroidUIAutomator("text(\"退出编辑\")"), 1)) {
				driver.findElement(MobileBy.AndroidUIAutomator("text(\"退出编辑\")")).click();

			}
//			if (isExistBySelector(driver, HomePage.sportCircleBy,1)){
//				HomePage.sportsTab.click();
//			}
			Thread.sleep(1000L);
		}
		return true;
	}

	/**
	 * back 返回桌面
	 *
	 * @return
	 * @throws InterruptedException
	 */
	public boolean pressBackToHome() throws InterruptedException {
		for (int defaultTime = 8; defaultTime > 0 && !driver.currentActivity().contains("launcher"); --defaultTime) {
			this.pressBack(1);
		}
		return driver.currentActivity().contains("launcher");
	}

    public int changePhoneIME(String imeName) throws InterruptedException, IOException {
		int i = Runtime.getRuntime()
				.exec(ADB_PATH + "adb shell ime set "+ DEFAULT_IME).waitFor();
		LOG.info("输入法: " + DEFAULT_IME);
		return i;
	}

	public int changeAppiumIME() throws InterruptedException, IOException {
		int i = Runtime.getRuntime().exec(ADB_PATH + "adb shell ime set io.appium.android.ime/.UnicodeIME").waitFor();
		return i;
	}

	/**
	 * 获取元素element的文本，超时返回null
	 * 
	 * @param by
	 * @param timeout
	 * @return
	 */
	public String getElementText(By by, long timeout) {
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < timeout) {
			if (waitForVisible(driver, by, 1)) {
				return driver.findElement(by).getText();
			}
		}
		return null;
	}

	public String getElementsText(List<WebElement> li) {
		List<String> textList = new ArrayList<String>();
		for (int i = 0; i < li.size(); i++) {
			textList.add(li.get(i).getText());
		}
		return textList.toString();
	}
	
	public String getElementsTextBySelector(List<WebElement> li, By by) {
		List<String> textList = new ArrayList<String>();
		for (int i = 0; i < li.size(); i++) {
			textList.add(li.get(i).findElement(by).getText());
		}
		return textList.toString();
	}

	/**
	 * 当前页面等待text出现
	 * 
	 * @param text
	 * @param timeout
	 * @return
	 * @throws InterruptedException
	 */
	public boolean waitText(String text, long timeout) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < timeout*1000) {
			if (waitForVisible(driver, By.xpath("//android.widget.TextView[contains(@text, '" + text + "')]"), 1)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 取当前屏幕显示区域的第一个textview文本，向上滑动半屏后查看文本是否存在，若没找到文本则加载了新内容，否则加载失败
	 * 
	 * @throws InterruptedException
	 */
	public boolean swipeUpLoadMore(WebElement el, int times) throws InterruptedException {
		boolean flag = true;
		while (times > 0) {
			String context = el.getText();
			// String context =
			// driver.findElement(By.id(HOTPAGE_PAGEVIEWER_ID)).findElement(By.className("android.widget.TextView")).getText();
			LOG.info(context);
			this.swipeUpQuickly(2);
			times--;
			Thread.sleep(1000L);
			if (!waitText(context, 5)) {
				continue;
			} else {
				flag = false;
				break;
			}
		}
		return flag;

	}

	/**
	 * 向上滑动查找text
	 *
	 * @param text
	 * @param timeout
	 *            超时
	 * @return
	 * @throws InterruptedException
	 */
	public boolean searchText(String text, long timeout) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < timeout) {
			if (waitForVisible(driver, By.xpath("//*[contains(@text, '" + text + "')]"), 1)) {
				return true;
			}
			this.swipeUp();
		}
		return false;
	}

	/**
	 * 向上滑动查找by
	 *
	 * @param by
	 * @param timeout
	 *            超时 单位:秒
	 * @return
	 * @throws InterruptedException
	 */
	public boolean searchBy(By by, long timeout) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < timeout * 1000) {
			if (waitForVisible(driver, by, 1)) {
				return true;
			}
			this.swipeUp(1);
		}
		return false;
	}

	/**
	 * 向下滑动查找by
	 *
	 * @param by
	 * @param timeout
	 *            超时
	 * @return
	 * @throws InterruptedException
	 */
	public boolean searchDownBy(By by, long timeout) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < timeout) {
			if (waitForVisible(driver, by, 1)) {
				return true;
			}
			this.swipeDown(1);
		}
		return false;
	}

	/**
	 * 向上滑动查找id
	 *
	 * @param id
	 * @param timeout
	 *            超时
	 * @return
	 * @throws InterruptedException
	 */
	public boolean searchId(String id, long timeout) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < timeout) {
			if (waitForVisible(driver, By.id(id), 1)) {
				return true;
			}
			this.swipeUp();
		}
		return false;
	}

	/**
	 * 向上滑动查找包含text
	 *
	 * @param by
	 * @param text
	 * @param timeout
	 * @return
	 * @throws InterruptedException
	 */
	public boolean searchTextContainsBySelector(By by, String text, long timeout) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		while (System.currentTimeMillis() - startTime < timeout) {
			try {
				if (driver.findElement(by).getText().contains(text)) {
					return true;
				} else {
					swipeUp();
				}
			} catch (Exception e) {
				this.swipeUp();
			}
		}
		return false;
	}

	/**
	 * @param selector
	 * @param instance
	 * @param targetSelector
	 * @return
	 */
	public boolean containsElementBySelector(By selector, int instance, By targetSelector) {
		boolean result = false;
		WebElement object = driver.findElements(selector).get(instance);
		WebElement targetObject = object.findElement(targetSelector);

		Rect rectObject = getVisibleBounds(object);
		Rect rectTargetObject = getVisibleBounds(targetObject);
		result = rectObject.contains(rectTargetObject);

		return result;
	}

	/**
	 * @param selector
	 * @param instance
	 * @param targetSelectors
	 * @return
	 */
	public boolean containsElementsBySelector(By selector, int instance, List<By> targetSelectors) {
		boolean result = false;
		Rect rectObject = null;
		try {
			WebElement object = driver.findElements(selector).get(instance);
			rectObject = getVisibleBounds(object);

			for (By by : targetSelectors) {
				WebElement targetObject = findElementInner(selector, instance, by);
				Rect rectTargetObject = getVisibleBounds(targetObject);

				result = rectObject.contains(rectTargetObject);

				if (!result) {
					break;
				}

				rectObject = rectTargetObject;
			}

		} catch (Exception e) {
			return false;
		}
		return result;
	}

	public boolean containsListBySelector(By selector, int instance, List<By> targetSelectors) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		boolean result = false;
		Rect rectObject = null;
		try {
			WebElement object = driver.findElements(selector).get(instance);
			rectObject = getVisibleBounds(object);

			for (By by : targetSelectors) {
				WebElement targetObject = findElementInner(selector, instance, by);
				Rect rectTargetObject = getVisibleBounds(targetObject);
				result = rectObject.contains(rectTargetObject);
				if (!result) {
					break;
				}
			}
		} catch (Exception e) {
			return false;
		}

		return result;
	}

	/**
	 * 等待元素可见
	 *
	 * @param driver
	 * @param by
	 * @param waitTime
	 * @return
	 */
	public boolean waitForVisible(WebDriver driver, final By by, int waitTime) {
		boolean result = false;
		for (int attempt = 0; attempt < waitTime; attempt++) {
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElement(by);
				result = true;
				break;
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			}
		}
		return result;
	}

	/**
	 * 等待一个元素都可见
	 * 
	 * @param driver
	 * @param el
	 * @param waitTime
	 * @return
	 */
	public boolean waitForVisible(WebDriver driver, WebElement el, int waitTime) {
		boolean result = false;
		for (int attempt = 0; attempt < waitTime; attempt++) {
			try {
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				LOG.info(el.getLocation());
				result = true;
				break;
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			}
		}
		return result;
	}

	/**
	 * 等待多个元素都可见
	 *
	 * @param driver
	 * @param waitTime
	 * @return
	 */
	public boolean waitForVisible(WebDriver driver, List<WebElement> li, int waitTime) {
		boolean result = false;
		for (int attempt = 0; attempt < waitTime; attempt++) {
			try {
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				for (WebElement el : li) {
					LOG.info(el.getLocation());
				}
				result = true;
				break;
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			}
		}
		return result;
	}

	public Rect intersect(Rect rect1, Rect rect2) {
		int top = rect1.top > rect2.top ? rect1.top : rect2.top;
		int bottom = rect1.bottom < rect2.bottom ? rect1.bottom : rect2.bottom;
		int left = rect1.left > rect2.left ? rect1.left : rect2.left;
		int right = rect1.right < rect2.right ? rect1.right : rect2.right;
		return new Rect(left, top, right, bottom);
	}

	/**
	 * 相对元素下方找到目标元素
	 *
	 * @param bySelector
	 * @param instance
	 * @param targetBySelector
	 * @return
	 */
	public WebElement findElementToDown(By bySelector, int instance, By targetBySelector) {
		int dist;
		WebElement targetObject;
		WebElement found = null;
		WebElement object = (WebElement) driver.findElements(bySelector).get(instance);

		List targetObject2List = driver.findElements(targetBySelector);
		Iterator var8 = targetObject2List.iterator();
		do {
			if (!var8.hasNext()) {
				return found;
			}
			targetObject = (WebElement) var8.next();

			Rect rectObject = getVisibleBounds(object);
			Rect rectTargetObject = getVisibleBounds(targetObject);

			Rect bounds = this.intersect(rectObject, rectTargetObject);

			int top = bounds.left;
			int bottom = bounds.right;

			dist = top < bottom ? rectTargetObject.top - rectObject.bottom : -1;
		} while (dist < 0);

		found = targetObject;
		return found;
	}

	/**
	 * 相对元素下方找到目标元素集合
	 *
	 * @param bySelector
	 * @param instance
	 * @param targetBySelector
	 * @return
	 */
	public List<WebElement> findElementsToDown(By bySelector, int instance, By targetBySelector) {
		int dist;
		WebElement targetObject;
		List<WebElement> found = new ArrayList<WebElement>();
		WebElement object = (WebElement) driver.findElements(bySelector).get(instance);

		List targetObject2List = driver.findElements(targetBySelector);
		Iterator var8 = targetObject2List.iterator();
		do {
			if (!var8.hasNext()) {
				break;
			}
			targetObject = (WebElement) var8.next();

			Rect rectObject = getVisibleBounds(object);
			Rect rectTargetObject = getVisibleBounds(targetObject);
			Rect bounds = this.intersect(rectObject, rectTargetObject);

			int top = bounds.left;
			int bottom = bounds.right;
			dist = top < bottom ? rectTargetObject.top - rectObject.bottom : -1;

			if (dist > 0) {
				found.add(targetObject);
			}
		} while (true);
		return found;
	}

	public List<WebElement> findElementsToDown(By bySelector, By targetBySelector, Dimension targetDimension) {
		int dist, cout = 0;
		WebElement targetObject;
		List<WebElement> found = new ArrayList<WebElement>();
		WebElement object = (WebElement) driver.findElement(bySelector);

		List targetObject2List = driver.findElements(targetBySelector);
		Iterator var8 = targetObject2List.iterator();
		do {
			if (!var8.hasNext()) {
				break;
			}
			targetObject = (WebElement) var8.next();

			if (!targetObject.getSize().equals(targetDimension) ) {
				continue;
			}
			if ((driver.getSize().width - targetObject.getLocation().x) < targetDimension.width){
				continue;
			}
			cout += 1;

			Rect rectObject = getVisibleBounds(object);
			Rect rectTargetObject = getVisibleBounds(targetObject);
			Rect bounds = this.intersect(rectObject, rectTargetObject);

			int top = bounds.left;
			int bottom = bounds.right;
			dist = top < bottom ? rectTargetObject.top - rectObject.bottom : -1;

			if (dist > 0) {
//				LOG.info(targetObject.getAttribute("className"));
				found.add(targetObject);
			}
		} while (true);
		LOG.info("found " + cout + " targetElement");
		return found;
	}

	/**
	 * 相对元素上方找到目标元素
	 *
	 * @param bySelector
	 * @param instance
	 * @param targetBySelector
	 * @return
	 */
	public WebElement findElementToUp(By bySelector, int instance, By targetBySelector) {
		int dist;
		WebElement targetObject;
		WebElement found = null;
		WebElement object = (WebElement) driver.findElements(bySelector).get(instance);

		List targetObject2List = driver.findElements(targetBySelector);
		Iterator var8 = targetObject2List.iterator();
		do {
			if (!var8.hasNext()) {
				return found;
			}
			targetObject = (WebElement) var8.next();

			Rect rectObject = getVisibleBounds(object);
			Rect rectTargetObject = getVisibleBounds(targetObject);

			Rect bounds = this.intersect(rectObject, rectTargetObject);

			int top = bounds.left;
			int bottom = bounds.right;

			dist = top < bottom ? rectObject.top - rectTargetObject.bottom : -1;
		} while (dist < 0);

		found = targetObject;
		return found;
	}

	/**
	 * 相对元素下方找到目标元素
	 *
	 * @param bySelector
	 * @param instance
	 * @param targetBySelector
	 * @return
	 */
	public WebElement findElementInner(By bySelector, int instance, By targetBySelector) {
		boolean dist = false;
		WebElement targetObject;
		WebElement found = null;
		WebElement object = (WebElement) driver.findElements(bySelector).get(instance);

		List targetObject2List = driver.findElements(targetBySelector);
		Iterator var8 = targetObject2List.iterator();
		do {
			if (!var8.hasNext()) {
				return found;
			}
			targetObject = (WebElement) var8.next();

			Rect rectObject = getVisibleBounds(object);
			Rect rectTargetObject = getVisibleBounds(targetObject);

			Rect bounds = this.intersect(rectObject, rectTargetObject);

			dist = bounds.equals(rectTargetObject);
		} while (!dist);

		found = targetObject;
		return found;
	}

	/**
	 * 相对元素左方找到元素
	 *
	 * @param bySelector
	 * @param instance
	 * @param targetBySelector
	 * @return
	 */
	public WebElement findElementToLeft(By bySelector, int instance, By targetBySelector) {
		int dist;
		WebElement targetObject;
		WebElement found = null;
		WebElement object = (WebElement) driver.findElements(bySelector).get(instance);

		List targetObject2List = driver.findElements(targetBySelector);
		Iterator var8 = targetObject2List.iterator();
		do {
			if (!var8.hasNext()) {
				return found;
			}
			targetObject = (WebElement) var8.next();

			Rect rectObject = getVisibleBounds(object);
			Rect rectTargetObject = getVisibleBounds(targetObject);

			Rect bounds = this.intersect(rectObject, rectTargetObject);

			int top = bounds.left;
			int bottom = bounds.right;

			dist = top < bottom ? rectObject.left - rectTargetObject.right : -1;
		} while (dist < 0);

		found = targetObject;
		return found;
	}
	
	public WebElement findElementToLeft(By bySelector, By targetBySelector, Dimension targetDimension) {
		int dist=0;
		int cout=0;
		WebElement targetObject;
		WebElement found = null;
		WebElement object = (WebElement) driver.findElement(bySelector);

		List targetObject2List = driver.findElements(targetBySelector);
		Iterator var8 = targetObject2List.iterator();
		do {
			if (!var8.hasNext()) {
				return found;
			}
			targetObject = (WebElement) var8.next();
			if (!targetObject.getSize().equals(targetDimension)) {
				continue;
			}
			cout += 1;

			Rect rectObject = getVisibleBounds(object);
			LOG.info("参照元素范围: " + rectObject);
			Rect rectTargetObject = getVisibleBounds(targetObject);
			LOG.info("目标元素范围: " + rectTargetObject);
			// Rect bounds = this.intersect(rectObject, rectTargetObject);
			// System.out.println("相交："+bounds.left+","+bounds.right+","+bounds.top+","+bounds.bottom);
			//
			// int top = bounds.left;
			// int bottom = bounds.right;
			if (rectObject.top <= rectTargetObject.top && rectObject.bottom >= rectTargetObject.top
					&& rectObject.right < rectTargetObject.left) {
				dist = 1;
			} else {
				dist = -1;
			}

		} while (dist < 0);

		found = targetObject;
		return found;
	}

	/**
	 * 相对元素右方平行区域找到目标元素
	 *
	 * @param bySelector
	 * @param instance
	 * @param targetBySelector
	 * @return
	 */
	public WebElement includeElementToRight(By bySelector, int instance, By targetBySelector) {
		int dist;
		WebElement targetObject;
		WebElement found = null;
		WebElement object = (WebElement) driver.findElements(bySelector).get(instance);

		List targetObject2List = driver.findElements(targetBySelector);
		Iterator var8 = targetObject2List.iterator();
		do {
			if (!var8.hasNext()) {
				return found;
			}
			targetObject = (WebElement) var8.next();

			Rect rectObject = getVisibleBounds(object);
//			LOG.info("参照元素范围: " + rectObject);
			Rect rectTargetObject = getVisibleBounds(targetObject);
//			LOG.info("目标元素范围: " + rectTargetObject);
			// Rect bounds = this.intersect(rectObject, rectTargetObject);
			// System.out.println("相交："+bounds.left+","+bounds.right+","+bounds.top+","+bounds.bottom);
			//
			// int top = bounds.left;
			// int bottom = bounds.right;
			if (rectObject.top <= rectTargetObject.top && rectObject.bottom >= rectTargetObject.top
					&& rectObject.left < rectTargetObject.left) {
				dist = 1;
			} else {
				dist = -1;
			}

			// dist = top < bottom ? rectTargetObject.left - rectObject.right :
			// -1;
		} while (dist < 0);

		found = targetObject;
		return found;
	}

	public WebElement findElementToRight(By bySelector, int instance, By targetBySelector) {
		int dist;
		WebElement targetObject;
		WebElement found = null;
		WebElement object = (WebElement) driver.findElements(bySelector).get(instance);

		List targetObject2List = driver.findElements(targetBySelector);
		Iterator var8 = targetObject2List.iterator();
		do {
			if (!var8.hasNext()) {
				return found;
			}
			targetObject = (WebElement) var8.next();

			Rect rectObject = getVisibleBounds(object);
			LOG.info("参照元素范围: " + rectObject);
			Rect rectTargetObject = getVisibleBounds(targetObject);
			LOG.info("目标元素范围: " + rectTargetObject);
			if (rectObject.bottom <= rectTargetObject.top && rectObject.left < rectTargetObject.left) {
				dist = 1;
			} else {
				dist = -1;
			}

		} while (dist < 0);

		found = targetObject;
		return found;
	}

	public int getInstanceByClass(String classname, String bounds) throws InterruptedException {
		int start = 0;
		int end = 0;
		int instance;
		bounds = bounds.replace(",", "][");
		int[] arr = new int[4];
		int flag = 0;
		for (int i = 0; i < bounds.length(); i++) {
			if (bounds.charAt(i) == '[') {
				start = i;
			} else if (bounds.charAt(i) == ']') {
				end = i;
				arr[flag] = Integer.parseInt(bounds.substring(start + 1, end));
				flag++;
			}
		}
		Thread.sleep(1000);
		String Rec = "Rect(" + arr[0] + ", " + arr[1] + " - " + arr[2] + ", " + arr[3] + ")";
		int cnt = driver.findElements(By.className(classname)).size();

		for (instance = 0; instance < cnt; instance++) {
			WebElement el = (WebElement) driver.findElements(By.className(classname)).get(instance);
			if (getVisibleBounds(el).toString().equals(Rec)) {
				break;
			}
		}
		return instance;
	}

	public int getInstanceByClass(String classname, Rect Rec) throws InterruptedException {
		int instance;

		int cnt = driver.findElements(By.className(classname)).size();

		for (instance = 0; instance < cnt; instance++) {
			WebElement el = (WebElement) driver.findElements(By.className(classname)).get(instance);
			if (getVisibleBounds(el).toString().equals(Rec.toString())) {
				break;
			}
		}
		return instance;
	}

	public Rect getVisibleBounds(WebElement el) {
		Rect rect = new Rect();
		int left = el.getLocation().x;
		int right = el.getLocation().x + el.getSize().width;
		int top = el.getLocation().y;
		int bottom = el.getLocation().y + el.getSize().height;
		// System.out.println("left right top bottom is "+left+" "+right+"
		// "+top+" "+bottom);
		// System.out.println("x y width height is "+el.getLocation().x+"
		// "+el.getLocation().y+" "+el.getSize().width+" "+el.getSize().height+"
		// "+el.getSize());
		rect.set(left, top, right, bottom);
		return rect;
	}

	/**
	 * click
	 *
	 * @param by
	 */
	public void clickByElement(By by) {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(by).click();
	}

	/**
	 * click in List
	 *
	 * @param by
	 * @param instance
	 */
	public void clickByElementInList(By by, int instance) {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		List<WebElement> els = driver.findElements(by);
		els.get(instance).click();
	}

	/**
	 * click in Class
	 *
	 * @param by
	 * @param bound
	 * @throws InterruptedException
	 */
	public void clickElementByClass(By by, String bound) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		int instance = getInstanceByClass(by.toString(), bound);
		List<WebElement> els = driver.findElements(by);
		els.get(instance).click();
	}

	public WebElement findElementInList(By by, int instance) {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		return (WebElement) driver.findElements(by).get(instance);
	}

	/**
	 * 判断元素存在
	 *
	 * @param driver
	 * @param by
	 * @param waitTime
	 * @return
	 */
	public boolean isExistBySelector(WebDriver driver, By by, int waitTime) {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		boolean result = false;
		for (int attempt = 0; attempt < waitTime; attempt++) {
			try {
				driver.findElement(by);
				result = true;
				break;
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			}
		}
		return result;
	}

	public boolean isExistBySelector(WebElement element, By by, int waitTime) {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		boolean result = false;
		for (int attempt = 0; attempt < waitTime; attempt++) {
			try {
				element.findElement(by);
				result = true;
				break;
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			}
		}
		return result;
	}

	/**
	 * 返回text
	 *
	 * @param by
	 * @return
	 */
	public String getTextByElement(By by) {
		return driver.findElement(by).getText();
	}

	/**
	 * List 返回text
	 *
	 * @param by
	 * @param instance
	 * @return
	 */
	public String getTextByElements(By by, int instance) {
		return ((WebElement) driver.findElements(by).get(instance)).getText();
	}

	/**
	 * 输入text
	 *
	 * @param by
	 * @param content
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void setTextByElement(By by, String content) throws InterruptedException {
		Thread.sleep(1000);
		try {
			Runtime.getRuntime()
					.exec("/Users/xiaoq/android-sdk-macosx/platform-tools/adb shell ime set com.google.android.inputmethod.pinyin/.PinyinIME")
					.waitFor();
			driver.findElement(by).sendKeys(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Thread.sleep(2000);
		}

	}

	/**
	 * 
	 * @param times
	 *            触控次数
	 * @param x
	 *            触控点的x坐标值
	 * @param y
	 *            触控点的y坐标值
	 * @param during
	 *            触控持续时间
	 * @param wait
	 *            触控后等待时长，单位秒
	 * @throws InterruptedException
	 */
	public void tap(int times, int x, int y, int during, int wait) throws InterruptedException {
		driver.tap(times, x, y, during);
		Thread.sleep(wait * 1000);
	}

	public void setTextByElements(By by, int instance, String content) throws InterruptedException {
		Thread.sleep(1000);
		List<WebElement> els = driver.findElements(by);
		els.get(instance).sendKeys(content);
	}

	public boolean isExistImageElement(String resourceName, int secondsToWait)
			throws InterruptedException, MalformedURLException {
		sleep(2000);
		String prefix = String.valueOf(driver.getSize().getWidth()) + "x_";
		resourceName = prefix.replace(".0", "") + resourceName + ".png";
		URL resource = this.getClass().getClassLoader().getResource(resourceName);
		System.out.println(resource);
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
		return image != null;
	}

	public ImageElement waitForImageElement(String resourceName, int secondsToWait)
			throws InterruptedException, MalformedURLException {
		sleep(2000);
		String prefix = String.valueOf(driver.getSize().getWidth()) + "x_";
		resourceName = prefix.replace(".0", "") + resourceName + ".png";
		URL resource = this.getClass().getClassLoader().getResource(resourceName);
		System.out.println(resource);
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
		assertTrue(image != null,
				String.format("Cannot find image %s in %s seconds", resourceName, String.valueOf(secondsToWait)));
		return image;
	}

	public boolean findElementInDeskHome(By by) throws InterruptedException {
		boolean found = false;
		this.pressHome();
		this.pressHome();
		long time = System.currentTimeMillis();
		while (System.currentTimeMillis() - time < 30000) {
			if (waitForVisible(driver, by, 5)) {
				found = true;
				break;
			}
			this.swipeLeft(); // 左滑
		}
		if (!found) {
			time = System.currentTimeMillis();
			this.pressHome();
			while (System.currentTimeMillis() - time < 30000) {
				if (waitForVisible(driver, by, 5)) {
					found = true;
					break;
				}
				this.swipeRight(); // 右滑
			}
		}
		return found;
	}

    public String getH5AtricleTitle() throws InterruptedException {
		String articleTitle="";
		webH5Helper = WebH5Helper.getInstance(driver);
        Thread.sleep(5000L);
        LOG.info(driver.getContextHandles());
        if(((Map) driver.getCapabilities().getCapability("desired")).get("platformVersion").equals("5.0")){
			webH5Helper.switchContext("WEBVIEW_com.codoon.gps");
			articleTitle = driver.findElement(By.id("title")).getText();
			webH5Helper.switchContext("NATIVE_APP");
		} else{
//        	LOG.info(driver.getPageSource());
//            webH5Helper.switchContext("WEBVIEW_com.codoon.gps");
            WebElement webElement;
            webElement = driver.findElementByXPath("//*[@resource-id='title']");
            articleTitle = webElement.getAttribute("name");
		}

        return articleTitle;
    }

	public void setWifiConnection() {
		driver.setNetworkConnection(new NetworkConnectionSetting(false, true, false));
	}
}
