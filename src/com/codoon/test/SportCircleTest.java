package com.codoon.test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.driver.CapabilitiesFactory;
import io.driver.SikuppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.codoon.common.model.HomePage;
import com.codoon.common.model.SportcirclePage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaoq 2017年9月4日
 */
public class SportCircleTest extends BaseTest {
    private final static Logger LOG = Logger.getLogger(SportCircleTest.class);
    private static HomePage homePage;
    private static SessionId sessionId;
    private  int crashCout = 1;

    private static SportcirclePage sportcirclePage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        sessionId = driver.getSessionId();

        homePage = HomePage.getInstance(driver);
        LOG.info("进入运动圈tab首页");
        homePage.sportcircleTab.click(); // 点击运动圈tab,进入运动圈首页
        sportcirclePage = SportcirclePage.getInstance(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        if(driver.getSessionId() != sessionId){
            System.out.printf("crash: 发生了%d次！",crashCout++);
            homePage = HomePage.getInstance(driver);
        }else if(driver == null){
            driver = new SikuppiumDriver(
                    new URL("http://127.0.0.1:4723/wd/hub"),
                    CapabilitiesFactory.getCapabilities()
            );
            Thread.sleep(10000);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.setDriver(driver);
            driver.setSimilarityScore(0.95);
            driver.setWaitSecondsAfterClick(2);
            PageFactory.initElements(new AppiumFieldDecorator(driver, 1, TimeUnit.SECONDS), this);
        }
        LOG.info("当前case执行完成，返回到App一级界面");
        mHelper.pressBackToHomePage(5);
    }

    /**
     * 测试点: 发feed可以调起手机相册
     *
     * @throws InterruptedException
     */
    @Test(groups = { "other" })
    public void test001() throws InterruptedException {
        LOG.info("用例1:点击运动圈发feed按钮，点击跳转到相册");
        mHelper.tap(1, sportcirclePage.addFeedBtn.x, sportcirclePage.addFeedBtn.y, 5, 1);
        mHelper.tap(1, sportcirclePage.dynamicFeedBtn.x, sportcirclePage.dynamicFeedBtn.y, 5, 1);
        // 点击“相册”
        sportcirclePage.photoButton.click();
        boolean photo = mHelper.isExistBySelector(driver, sportcirclePage.photoTitleTextBy, 5); // 判断页面title是“最新照片”，超时时间5s
        LOG.info("开始断言，判断当前页面title为“最新照片”");
        Assert.assertTrue(photo, "Error when into newPhoto"); // 断言，若页面title是“最新照片”则通过，反之失败
    }

    /**
     * 测试点: 发feed可以调起手机相机拍照
     *
     * @throws InterruptedException
     */
    @Test(groups = { "other" })
    public void test002() throws InterruptedException {
        LOG.info("用例2:点击运动圈发feed按钮，点击跳转到相机");
        mHelper.tap(1, sportcirclePage.addFeedBtn.x, sportcirclePage.addFeedBtn.y, 5, 1);
        mHelper.tap(1, sportcirclePage.dynamicFeedBtn.x, sportcirclePage.dynamicFeedBtn.y, 5, 1);
        sportcirclePage.cameraButton.click(); // 点击“拍照”按钮
        boolean photo = driver.currentActivity().contains("camera") || driver.currentActivity().contains("Camera"); // 判断页面名是否包含“camera”
        LOG.info("开始断言，判断已打开照相机");
        Assert.assertTrue(photo, "Error when into camera"); // 断言，若页面名包含“camera”则通过，反之失败
    }

    @Test(groups = { "搜索" })
    public void test003() throws InterruptedException {
        String searchContext = "猴子";
        sportcirclePage.searchSubmitButton.click();
        sportcirclePage.searchInputEdit.sendKeys(searchContext);
        sportcirclePage.searchSubmitButton.click();
        boolean all = mHelper.isExistBySelector(driver, sportcirclePage.textContainsBy(searchContext), 5);
        sportcirclePage.userTabInSearch.click();
        boolean user = mHelper.isExistBySelector(driver, sportcirclePage.textContainsBy(searchContext),
                3);
        sportcirclePage.groupTabInSearch.click();
        boolean group = mHelper.isExistBySelector(driver, sportcirclePage.textContainsBy(searchContext),
                3);
        sportcirclePage.atricleTabInSearch.click();
        boolean txt = mHelper.isExistBySelector(driver, sportcirclePage.textContainsBy(searchContext), 5);
        sportcirclePage.dynamicTabInSearch.click();
        boolean dynamic = mHelper.isExistBySelector(driver, sportcirclePage.textContainsBy(searchContext),
                3);
        sportcirclePage.goodsTabInSearch.click();
        boolean goods = mHelper.isExistBySelector(driver, sportcirclePage.textContainsBy(searchContext),
                3);
        sportcirclePage.matchTabInSearch.click();
        boolean match = mHelper.isExistBySelector(driver, sportcirclePage.textContainsBy(searchContext),
                3);
        Assert.assertTrue(all && user && group && txt && dynamic, "搜索内容错误");
    }

    @Test(groups = { "搜索" })
    public void test004() throws InterruptedException {
        sportcirclePage.searchSubmitButton.click();
        sportcirclePage.searchInputEdit.sendKeys("猴子");
        sportcirclePage.searchSubmitButton.click();
        sportcirclePage.groupTabInSearch.click();
        String groupName = mHelper.getElementText(sportcirclePage.searchGrouListBy, 5);
        sportcirclePage.searchGroupListItem.click();
        LOG.info(mHelper.isExistBySelector(driver, MobileBy.AndroidUIAutomator("text(\""+groupName+"\")"), 3));
        boolean group = mHelper.isExistBySelector(driver, MobileBy.AndroidUIAutomator("text(\""+groupName+"\")"), 3);
        LOG.info(group);
        mHelper.pressBack();
        //*** 以上为 搜索运动团 跳转是否正确
        sportcirclePage.atricleTabInSearch.click();
        String txtName = mHelper.getElementText(sportcirclePage.searchAtriListBy, 3);
        LOG.info(txtName);
        sportcirclePage.searchAtriListItem.click();
        String artiTxt = mHelper.getH5AtricleTitle();
        LOG.info(artiTxt);
        boolean txt = txtName.equals(artiTxt);
        LOG.info(txt);;
        mHelper.pressBack();
        //*** 以上为 搜索文章 跳转是否正确
        sportcirclePage.userTabInSearch.click();
        String userName = mHelper.getElementText(sportcirclePage.searchUserListBy, 5);
        sportcirclePage.searchUserListItem.click();
        boolean user = mHelper.isExistBySelector(driver, MobileBy.AndroidUIAutomator("text(\""+userName+"\")"), 5);
        //*** 以上为 搜索用户 跳转是否正确
        LOG.info(user);
        Assert.assertTrue(group&&txt&&user, "搜索结果 tab栏跳转失败");
    }

    /**
     * 测试点: 广告banner正确显示且可以跳转
     *
     * @throws InterruptedException
     */
    @Test(groups = { "热门" })
    public void test005() throws InterruptedException {
        sportcirclePage.hotTabIcon.click(); // 点击“热门”tab
        sportcirclePage.advScollView.click(); // 点击广告banner

        boolean change = !mHelper.isExistBySelector(driver, sportcirclePage.addFeedBtnBy, 5); // 断言，页面是否包含发feed按钮来判断是否发生跳转
        LOG.info("result passed is: " + change);
        Assert.assertTrue(change, "点击广告跳转失败");
    }

    /**
     * 测试点: 1.热门话题正确显示，点击更多进入话题列表页 2.话题列表页点击话题进入话题详情页
     *
     * @throws InterruptedException
     */
    @Test(groups = { "热门" })
    public void test006() throws InterruptedException {
        sportcirclePage.hotTabIcon.click();
        sportcirclePage.clickHotpageMore(sportcirclePage.hotTopicBy); // 点击热门话题更多
        boolean more = !mHelper.isExistBySelector(driver, sportcirclePage.moreBtnBy, 5); // 判断是否跳转话题列表页
        Assert.assertTrue(more, "点击跳转更多话题失败"); // 断言，跳转则通过
        String topicName = sportcirclePage.clickTopicListView(); // 点击话题列表第一项
        LOG.info(topicName);
        mHelper.waitForVisible(driver, sportcirclePage.imageViewClassBy, 10);
        String topicTitle = mHelper.getElementText(sportcirclePage.pageTitleBy, 3); // 获取当前页面title
        LOG.info(topicTitle);
        Assert.assertEquals(topicName, topicTitle.replaceAll(" ", ""), "点击进入话题详情失败"); // 列表页点击的话题title与话题详情页title相同则通过
    }
//
    /**
     * 测试点: 讨论专区，点击更多，进入讨论列表
     * @throws InterruptedException
     */
    @Test(groups = { "热门" })
    public void test007() throws InterruptedException {
        sportcirclePage.hotTabIcon.click(); // 点击“热门”tab
        sportcirclePage.clickHotpageMore(sportcirclePage.discussColumnBy);
        boolean more = mHelper.isExistBySelector(driver, sportcirclePage.discussTitleBy, 5); // 判断是否跳转话题列表页
        LOG.info("result passed is: " + more);
        Assert.assertTrue(more, "点击跳转 讨论专区更多 失败"); // 断言，跳转则通过
    }
//
    //讨论
    @Test(groups = { "讨论" })
    public void test008() throws InterruptedException, IOException {
        sportcirclePage.hotTabIcon.click(); // 点击“热门”tab
        sportcirclePage.clickHotpageMore(sportcirclePage.discussColumnBy);

        String title = mHelper.getElementText(sportcirclePage.discussItemTitleBy, 5);
        sportcirclePage.discussList.click();
        Assert.assertTrue(mHelper.isExistBySelector(driver, MobileBy.AndroidUIAutomator("text(\""+title+"\")"), 5),"进入帖子详情失败");
        mHelper.changePhoneIME("com.samsung.inputmethod/.SamsungIME");
        sportcirclePage.infoInputEdit.sendKeys("Nice");
        sportcirclePage.infoSendBtn.click();
        Thread.sleep(1000L);
        while(!mHelper.isExistBySelector(driver, sportcirclePage.textContainsBy("Nice"), 2)){
            mHelper.swipeUp();
        }
        boolean a = mHelper.waitText("Nice", 2);
        Assert.assertTrue(a,"回复帖子失败");
    }
//
//    @Test(groups = { "讨论" })
//    public void test009() throws InterruptedException, IOException {
//        sportcirclePage.hotTabIcon.click(); // 点击“热门”tab
//        sportcirclePage.clickHotpageMore(sportcirclePage.discussColumnBy);
//        Thread.sleep(5000L);
//        LOG.info(driver.getPageSource());
//        sportcirclePage.discussList.click();
////        mHelper.changePhoneIME("com.samsung.inputmethod/.SamsungIME");
////        mHelper.searchBy(sportcirclePage.replyCommentBy, 20);
////        sportcirclePage.replyCommentIcon.click();
////        Thread.sleep(1000L);
////        sportcirclePage.infoInputEdit.sendKeys("Nice");
////        sportcirclePage.infoSendBtn.click();
//        Thread.sleep(3000L);

//        boolean comment = mHelper.isExistBySelector(driver,sportcirclePage.textContainsBy("Nice"), 10);
//        boolean comment = mHelper.waitText("Nice",5);
//        LOG.info(driver.getPageSource());
//        Assert.assertTrue(comment,"回复评论失败");

//    }
//
    /**
     * 测试点: 推荐达人 关注/取消关注
     *
     * @throws InterruptedException
     */
    @Test(groups = { "热门" })
    public void test010() throws InterruptedException {
//        sportcirclePage.gotoHot(); // 点击“热门”tab
        sportcirclePage.hotTabIcon.click(); // 点击“热门”tab
        mHelper.searchBy(sportcirclePage.talentBy, 30000);
        mHelper.moveToHalfScreen(sportcirclePage.talentTag);
        List<WebElement> careSet = mHelper.findElementsToDown(sportcirclePage.talentBy, 0, sportcirclePage.followBtnBy);
        List<WebElement> caredSet = mHelper.findElementsToDown(sportcirclePage.talentBy, 0, sportcirclePage.followedBtnBy);
        boolean status = sportcirclePage.followStatusChanged(careSet, caredSet);
        Assert.assertTrue(status, "关注/取消关注 失败"); // 断言，跳转则通过
    }
//
    /**
     * 测试点: 推荐达人 关注/取消关注
     *
     * @throws InterruptedException
     */
    @Test(groups = { "热门" })
    public void test011() throws InterruptedException {
//        sportcirclePage.gotoHot(); // 点击“热门”tab
        sportcirclePage.hotTabIcon.click(); // 点击“热门”tab
        mHelper.searchBy(sportcirclePage.talentBy, 30000);
        mHelper.moveToHalfScreen(sportcirclePage.talentTag);
        List<WebElement> careSet = mHelper.findElementsToDown(sportcirclePage.talentBy, 0, sportcirclePage.followBtnBy);
        List<WebElement> caredSet = mHelper.findElementsToDown(sportcirclePage.talentBy, 0, sportcirclePage.followedBtnBy);
        boolean status = sportcirclePage.followStatusChanged(careSet, caredSet);
        Assert.assertTrue(status, "关注/取消关注 失败"); // 断言，跳转则通过
    }
//
    /**
     * 测试点: 推荐达人点击更多 进入热门用户页面
     *
     * @throws InterruptedException
     */
    @Test(groups = { "热门" })
    public void test012() throws InterruptedException {
//        sportcirclePage.gotoHot(); // 点击“热门”tab
        sportcirclePage.hotTabIcon.click(); // 点击“热门”tab
        mHelper.searchBy(sportcirclePage.talentBy, 30000);
        mHelper.moveToHalfScreen(sportcirclePage.talentTag);
        sportcirclePage.clickHotpageMore(sportcirclePage.talentBy);
        boolean more = mHelper.isExistBySelector(driver, sportcirclePage.userAtiveBy, 5); // 判断是否跳转话题列表页
        LOG.info("result passed is: " + more);
        Assert.assertTrue(more, "点击跳转更多推荐用户失败"); // 断言，跳转则通过

    }
//
    /**
     * 测试点: 精选动态跳转feed详情
     *
     * @throws InterruptedException
     */
    @Test(groups = { "热门" })
    public void test013() throws InterruptedException {
//        sportcirclePage.gotoHot(); // 点击“热门”tab
        sportcirclePage.hotTabIcon.click(); // 点击“热门”tab
        mHelper.searchBy(sportcirclePage.dynamicBy, 30000);
        mHelper.moveToHalfScreen(sportcirclePage.dynamicTag);
//        mHelper.findElementToDown(By.name(HOTPAGE_DYNAMIC_TAG_NAME), 0, By.className("android.widget.ImageView")).click();
        mHelper.findElementToDown(sportcirclePage.dynamicBy, 0, sportcirclePage.imageViewClassBy).click();
        boolean title = mHelper.isExistBySelector(driver, MobileBy.AndroidUIAutomator("text(\"详情\")"), 5);
        Assert.assertTrue(title, "精选动态跳转详情页失败");
    }
//
    /**
     * 测试点: 精选动态加载更多
     *
     * @throws InterruptedException
     */
    @Test(groups = { "热门" })
    public void test014() throws InterruptedException {
//        sportcirclePage.gotoHot();
        sportcirclePage.hotTabIcon.click();
        mHelper.searchBy(sportcirclePage.dynamicBy, 10);
        boolean load = mHelper.swipeUpLoadMore(sportcirclePage.dynamicText, 5);
        Assert.assertTrue(load, "精选动态加载更多失败");
    }
//
    /**
     * 测试点: 发布一张图+文字feed+话题+定位
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(groups = { "发布" })
    public void test015() throws InterruptedException, IOException {
//        sportcirclePage.gotoHot();
        sportcirclePage.hotTabIcon.click();
        mHelper.changeAppiumIME();
        mHelper.tap(1, sportcirclePage.addFeedBtn.x, sportcirclePage.addFeedBtn.y, 5, 1);
        mHelper.tap(1, sportcirclePage.dynamicFeedBtn.x, sportcirclePage.dynamicFeedBtn.y, 5, 1);
        sportcirclePage.photoButton.click(); // 点击“相册”按钮
        sportcirclePage.sendFeed(1, true, true);

        boolean addFeed = mHelper.waitText("this is a joke", 15); // 等待10s，判断feed文字是否显示
        // mHelper.moveToViewTop(sportsCircleModule.feedContext);
        double a = imageMatch("sportcircle_feed1", null, 20); // 匹配，图片是否显示正确
        Assert.assertTrue(addFeed && a > 0.95, "Error in add feed"); // 断言，文字和图片都显示则通过，反之失败
    }
//
    /**
     * 测试点: 发布9张图+文字feed+话题+定位
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(groups = { "发布" })
    public void test016() throws InterruptedException, IOException {
//        sportcirclePage.gotoHot();
        sportcirclePage.hotTabIcon.click();
        mHelper.tap(1, sportcirclePage.addFeedBtn.x, sportcirclePage.addFeedBtn.y, 5, 1);
        mHelper.tap(1, sportcirclePage.dynamicFeedBtn.x, sportcirclePage.dynamicFeedBtn.y, 5, 1);
        sportcirclePage.photoButton.click(); // 点击“相册”按钮
        sportcirclePage.sendFeed(9, true, true);
        boolean addFeed = mHelper.waitText("this is a joke", 10 * 1000); // 等待10s，判断feed文字是否显示
//		mHelper.moveToViewTop(sportsCircleModule.feedContext);
        double a = imageMatch("sportcircle_feed9", null, 20); // 匹配，图片是否显示正确
        Assert.assertTrue( a > 0.95, "Error in add feed"); // 断言，文字和图片都显示则通过，反之失败
//        Assert.assertTrue(addFeed && a >= 0.99, "Error in add feed"); // 断言，文字和图片都显示则通过，反之失败
    }
//
    /**
     * 测试点: 查看feed大图
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(groups = { "关注" })
    public void test017() throws InterruptedException, IOException {
        sportcirclePage.caredTabIcon.click();
        mHelper.searchDownBy(sportcirclePage.myNickTextBy, 10);
        mHelper.findElementToDown(sportcirclePage.feedContentBy, 0, sportcirclePage.imageViewClassBy).click();
        double a = imageMatch("large_picture", null, 20); // 匹配，图片是否显示正确
        Assert.assertTrue(a >= 0.99, "查看feed大图显示失败");
    }
//
    /**
     * 测试点: 推荐用户 关注/取消关注
     *
     * @throws InterruptedException
     */
    @Test(groups = { "关注" })
    public void test018() throws InterruptedException, IOException {
        sportcirclePage.caredTabIcon.click();
        mHelper.searchBy(sportcirclePage.suggestUserBy, 10);
        mHelper.moveToHalfScreen(sportcirclePage.suggestUserTag);
        List<WebElement> careSet = mHelper.findElementsToDown(sportcirclePage.suggestUserBy, 0, sportcirclePage.followBtnBy);
        List<WebElement> caredSet = mHelper.findElementsToDown(sportcirclePage.suggestUserBy, 0, sportcirclePage.followedBtnBy);
        boolean status = sportcirclePage.followStatusChanged(careSet, caredSet);
        Assert.assertTrue(status, "关注/取消关注 失败"); // 断言，跳转则通过
    }
//
    /**
     * 测试点: 推荐用户 关注/取消关注
     *
     * @throws InterruptedException
     */
    @Test(groups = { "关注" })
    public void test019() throws InterruptedException, IOException {
//        sportcirclePage.gotoCare();
        sportcirclePage.caredTabIcon.click();
        mHelper.searchBy(sportcirclePage.suggestUserBy, 30);
        mHelper.moveToHalfScreen(sportcirclePage.suggestUserTag);
        List<WebElement> careSet = mHelper.findElementsToDown(sportcirclePage.suggestUserBy, 0,
                sportcirclePage.followBtnBy);
        List<WebElement> caredSet = mHelper.findElementsToDown(sportcirclePage.suggestUserBy, 0,
                sportcirclePage.followedBtnBy);
        boolean status = sportcirclePage.followStatusChanged(careSet, caredSet);
        Assert.assertTrue(status, "关注/取消关注 失败"); // 断言，跳转则通过
    }
//
    /**
     * 测试点: 推荐用户 换一批
     *
     * @throws InterruptedException
     */
    @Test(groups = { "关注" })
    public void test020() throws InterruptedException {
//        sportcirclePage.gotoCare();
        sportcirclePage.caredTabIcon.click();
        mHelper.searchBy(sportcirclePage.suggestUserBy, 20 * 1000);
        mHelper.moveToHalfScreen(sportcirclePage.suggestUserTag);
        String change = mHelper.getElementsText(sportcirclePage.userNickLabel);
        LOG.info(change);
        sportcirclePage.changeOtherBtn.click();
        String changed = mHelper.getElementsText(sportcirclePage.userNickLabel);
        LOG.info(changed);
        Assert.assertTrue(!changed.equals(change), "推荐用户换一批 失败"); // 断言，跳转则通过
    }
//
    /**
     * 测试点: 附近动态 点击更多进入附近动态页面
     *
     * @throws InterruptedException
     */
    @Test(groups = { "关注" })
    public void test021() throws InterruptedException {
//        sportcirclePage.gotoCare();
        sportcirclePage.caredTabIcon.click();
        while (!mHelper.isExistBySelector(driver, sportcirclePage.feedNearbyTagBy, 2)) {
            mHelper.swipeUp();
        }
        mHelper.moveToHalfScreen(sportcirclePage.nearbyFeed);
        sportcirclePage.clickHotpageMore(sportcirclePage.feedNearbyLabelBy);
        boolean more = mHelper.isExistBySelector(driver, sportcirclePage.feedNearbyMoreBy, 5); // 判断是否跳转话题列表页
        LOG.info("result passed is: " + more);
        boolean load = mHelper.swipeUpLoadMore(sportcirclePage.feedNickLabel, 10);
        Assert.assertTrue(load, "附近动态加载更多 失败");
    }
//
    /**
     * 测试点: feed点赞，取消点赞
     *
     * @throws InterruptedException
     */
    @Test(groups = { "关注" })
    public void test022() throws InterruptedException {
//        sportcirclePage.gotoCare();
        sportcirclePage.caredTabIcon.click();
        mHelper.swipeUp(3);
        sportcirclePage.fixPositionOnFeed();
        boolean like = sportcirclePage.feedTakeLikeOrDislike();
        Assert.assertTrue(like, "feed点赞／取消点赞 失败");
        boolean again = sportcirclePage.feedTakeLikeOrDislike();
        Assert.assertTrue(again, "feed点赞／取消点赞 失败");
    }
//
    /**
     * 测试点: feed评论
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(groups = { "关注" })
    public void test023() throws InterruptedException, IOException {
//        sportcirclePage.gotoCare();
        sportcirclePage.caredTabIcon.click();
        mHelper.swipeUp(5);
        sportcirclePage.fixPositionOnFeed();
        sportcirclePage.feedComment();
        boolean comment = mHelper.searchBy(MobileBy.AndroidUIAutomator("text(\""+sportcirclePage.COMMENT_TEXT+"\")"), 10);
        Assert.assertTrue(comment, "feed评论 失败");
    }
//
    /**
     * 测试点: feed收藏
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(groups = { "关注" })
    public void test024() throws InterruptedException, IOException {
//        sportcirclePage.gotoCare();
        sportcirclePage.caredTabIcon.click();
        mHelper.swipeUp(5);
        mHelper.searchBy(sportcirclePage.feedContentBy, 10);
        String feedSummry = mHelper.getElementText(sportcirclePage.feedContentBy, 5);
        sportcirclePage.feedContext.click();
        sportcirclePage.detailCollectButton.click();
        boolean collect = sportcirclePage.checkCollectionInMine(feedSummry);
        Assert.assertTrue(collect, "feed收藏 失败");

    }
//
    /**
     * 测试点: feed举报
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(groups = { "关注" })
    public void test025() throws InterruptedException, IOException {
//        sportcirclePage.gotoCare();
        sportcirclePage.caredTabIcon.click();
        do {
            mHelper.swipeUp(3);
        } while (!mHelper.searchBy(sportcirclePage.feedContentBy, 10));

        sportcirclePage.feedContext.click();
        sportcirclePage.feedMore.click();
        sportcirclePage.detailReportButton.click();
        boolean report = mHelper.waitForVisible(driver, sportcirclePage.reportReason, 10);
        Assert.assertTrue(report, "feed举报 失败");
    }
//
    /**
     * 测试点: 1.feed回收评论 2.点赞评论
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(groups = { "关注" })
    public void test026() throws InterruptedException, IOException {
        sportcirclePage.caredTabIcon.click();
        sportcirclePage.findAllComment();

        boolean like = sportcirclePage.feedDetailClickLike();
        Assert.assertTrue(like, "评论点赞失败");
        boolean comment = sportcirclePage.feedDetailReplyInfo("Nice");
        Assert.assertTrue(comment, "feed回复评论 失败");
    }

}

