package com.codoon.test;

import com.codoon.common.model.HomePage;
import com.codoon.common.model.MinePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.SessionId;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class MineTest extends BaseTest {
    private final static Logger LOG = Logger.getLogger(MineTest.class);
    private static HomePage homePage;
    private static SessionId sessionId;
    private static MinePage minePage;
    private  int crashCout = 0;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        sessionId = driver.getSessionId();
        homePage = HomePage.getInstance(driver);
        LOG.info("进入我的tab页");
        if(mHelper.isExistBySelector(driver,homePage.advPopupBy)){
            homePage.advrCloseBtn.click();
        }
        homePage.mineTab.click(); // 点击运动圈tab,进入运动圈首页
        minePage = MinePage.getInstance(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        LOG.info("当前case执行完成，返回到App一级界面");
        mHelper.pressBackToHomePage(5);
    }

    @Test(groups = { "profile" })
    /**
     * 编辑--个人资料
     */
    public void test001() throws InterruptedException {
        minePage.headIcon.click();
        minePage.editProfileBtn.click();
        minePage.editHeadBtn.click();
        mHelper.pressBack();
        minePage.editNickBtn.click();
        minePage.cancelEditBtn.click();
        minePage.editInfoBtn.click();
        minePage.cancelEditBtn.click();
        minePage.editInterBtn.click();
        minePage.cancelEditBtn.click();
        minePage.editAgeBtn.click();
        minePage.cancelEditBtn.click();
        minePage.editHeighBtn.click();
        minePage.cancelEditBtn.click();
        minePage.editWeightBtn.click();
        minePage.cancelEditBtn.click();
        Assert.assertTrue(mHelper.isExistBySelector(driver,minePage.editTitleBy));
    }

    @Test(groups = { "profile" })
    public void test002() throws IOException, InterruptedException {
        minePage.headIcon.click();
        imageMatch("head_small",null,0);
        driver.findImageElement("head_small.png").tap();
        double a = imageMatch("head_large",null,0);
        Assert.assertTrue(a>=0.999,"头像大图显示失败");
    }

    @Test(groups = { "profile" })
    public void test003() throws IOException, InterruptedException {
        minePage.headIcon.click();
    }

    @Test(groups = { "profile" })
    public void test004() throws MalformedURLException {
        minePage.headIcon.click();
        minePage.caredLabel.click();
        Assert.assertTrue(mHelper.isExistBySelector(driver,minePage.caredTitleBy));
        driver.findImageElement("back.png").tap();
        minePage.fansLabel.click();
        Assert.assertTrue(mHelper.isExistBySelector(driver,minePage.fansTitleBy));
        driver.findImageElement("back.png").tap();
    }

    @Test(groups = { "profile" })
    public void test005() throws IOException, InterruptedException {
        minePage.headIcon.click();
        double run = imageMatch("run_level",null,0);
        if(run>=0.99){
            minePage.runLevelLabel.click();
            double a = imageMatch("run_level_detail",null,0);
            mHelper.pressBack();
            Assert.assertTrue(a>=0.99,"跑步等级显示错误");
        }
        double walk = imageMatch("walk_level",null,0);
        if(walk>=0.99){
            minePage.walkLevelLabel.click();
            double a = imageMatch("walk_level_detail",null,0);
            mHelper.pressBack();
            Assert.assertTrue(a>=0.99,"健走等级显示错误");
        }
        double ride = imageMatch("riding_level",null,0);
        if (ride>=0.99){
            minePage.rideLevelLabel.click();
            double a = imageMatch("riding_level_detail",null,0);
            mHelper.pressBack();
            Assert.assertTrue(a>=0.99,"骑行等级显示错误");
        }
    }

    @Test(groups = { "profile" })
    public void test006() throws IOException, InterruptedException {
        minePage.headIcon.click();
        float totalSport = Float.parseFloat(minePage.sportsTotalLabel.getText());
        totalSport =  (float)(Math.ceil(totalSport*100))/100;
        float currentSport = Float.parseFloat(minePage.sportsMonthLabel.getText());
        currentSport =  (float)(Math.ceil(currentSport*100))/100;
        mHelper.pressBackToHomePage(3);
        minePage.historyViewBtn.click();
        minePage.statChartBtn.click();
//        minePage.sportsTypeLabel.click();
        List<Float> walkList = minePage.getSportsData("walk");
        List<Float> runList = minePage.getSportsData("run");
        List<Float> rideList = minePage.getSportsData("ride");
        float currentSum =  walkList.get(0) + runList.get(0) + rideList.get(0);
        currentSum =  (float)(Math.ceil(currentSum*100))/100;
        float totalSum =  walkList.get(1) + runList.get(1) + rideList.get(1);
        totalSum =  (float)(Math.ceil(totalSum*100))/100;
        LOG.info(totalSport);
        LOG.info(totalSum);
        Assert.assertTrue(totalSport==totalSum,"运动档案数据与统计不一致");
    }

    @Test(groups = { "profile" })
    public void test007() throws MalformedURLException, InterruptedException {
        minePage.headIcon.click();
        mHelper.findElementToDown(minePage.runLevelBy,0,minePage.honnorWallBy).click();
        boolean a = mHelper.isExistBySelector(driver,minePage.honnorWallTitleBy);
        Assert.assertTrue(a,"进入奖章墙失败");
        boolean share = minePage.shareChecker();
        Assert.assertTrue(share,"分享途径错误");
    }

    @Test(groups = { "profile" })
    public void test008() throws MalformedURLException, InterruptedException {
        minePage.headIcon.click();
        mHelper.findElementToDown(minePage.runLevelBy,0,minePage.honnorWallBy).click();
        boolean detail = minePage.shareMedalChecker();
        Assert.assertTrue(detail,"奖章详情页显示错误");
    }

    @Test(groups = { "profile" })
    public void test009() throws MalformedURLException, InterruptedException {
        minePage.headIcon.click();
        boolean found = mHelper.searchBy(minePage.groupBy,5);
        if (found){
            minePage.groupCard.click();
            boolean group = mHelper.isExistBySelector(driver,minePage.groupTitleBy);
            Assert.assertTrue(group,"进入运动团主页失败");
        }else {
            LOG.info("没有找到运动团tag标签");
        }
    }

    @Test(groups = { "profile" })
    public void test010() throws MalformedURLException, InterruptedException {
        minePage.headIcon.click();
        boolean found = mHelper.searchBy(minePage.myfeedBy, 10);
        if (found) {
            boolean feed = mHelper.swipeUpLoadMore(minePage.feedTimeLabel, 1);
            Assert.assertTrue(feed, "我的动态加载更多失败");
        } else {
            LOG.info("没有找到我的动态tag标签");
        }
    }

    @Test(groups = { "profile" })
    public void test011(){
        minePage.headIcon.click();
        minePage.fansLabel.click();
        String name = minePage.fansListNickItem.getText();
        minePage.fansListViewItem.click();
        String nick = minePage.nickLabel.getText();
        Assert.assertEquals(name,nick,"个人资料客人态信息不符");
    }

    @Test(groups = { "profile" })
    public void test012(){
        minePage.headIcon.click();
        minePage.fansLabel.click();
        minePage.fansListViewItem.click();
        minePage.hisFansLabel.click();
        boolean b = mHelper.isExistBySelector(driver,minePage.fansTitleBy);
        Assert.assertTrue(b,"个人资料客人态进入粉丝列表失败");

    }

    @Test(groups = { "profile" })
    public void test013(){
        minePage.headIcon.click();
        minePage.fansLabel.click();
        minePage.fansListViewItem.click();
        minePage.hisCaredLabel.click();
        boolean b = mHelper.isExistBySelector(driver,minePage.caredTitleBy);
        Assert.assertTrue(b,"个人资料客人态进入关注列表失败");

    }

    @Test(groups = { "profile" })
    public void test014(){
        boolean care = false;
        minePage.headIcon.click();
        minePage.fansLabel.click();
        minePage.fansListViewItem.click();
        if (minePage.profileFollowBtn.getText().equals("关注")){
            minePage.profileFollowBtn.click();
            care = minePage.profileFollowBtn.getText().equals("已关注");
        }else{
            minePage.profileFollowBtn.click();
            minePage.cancelFollowedBtn.click();
            care = minePage.profileFollowBtn.getText().equals("关注");
        }
        Assert.assertTrue(care,"个人资料关注按钮无效");
    }

    @Test(groups = { "profile" })
    public void test015(){
        boolean care = false;
        minePage.headIcon.click();
        minePage.fansLabel.click();
        minePage.fansListViewItem.click();
        if (minePage.profileFollowBtn.getText().equals("关注")){
            minePage.profileFollowBtn.click();
            care = minePage.profileFollowBtn.getText().equals("已关注");
        }else{
            minePage.profileFollowBtn.click();
            minePage.cancelFollowedBtn.click();
            care = minePage.profileFollowBtn.getText().equals("关注");
        }
        Assert.assertTrue(care,"个人资料关注按钮无效");
    }

    @Test(groups = { "profile" })
    public void test016() throws IOException, InterruptedException {
        minePage.headIcon.click();
        minePage.fansLabel.click();
        minePage.fansListViewItem.click();
        minePage.profileSendBtn.click();
        boolean a = isImageExist("send_message.png",2);
        Assert.assertTrue(a,"个人资料发送消息按钮无效");
    }

    @Test(groups = { "profile" })
    public void test017() throws IOException, InterruptedException {
        minePage.headIcon.click();
        minePage.fansLabel.click();
        minePage.fansListViewItem.click();
        if (minePage.profileFollowBtn.getText().equals("已关注")){
            minePage.profileFollowBtn.click();
            minePage.cancelFollowedBtn.click();
        }
        mHelper.swipeUpQuickly(10);
        boolean a = isImageExist("discared_feed.png",2);
        Assert.assertTrue(a,"Feed底部关注按钮未显示");
    }

    @Test(groups = { "profile" })
    public void test018() throws InterruptedException, MalformedURLException {
        minePage.headIcon.click();
        minePage.fansLabel.click();
        minePage.fansListViewItem.click();
        mHelper.searchBy(minePage.hisfeedBy,10);
        mHelper.moveToHalfScreen(minePage.hisfeedTag);
        if (mHelper.isExistImageElement("feed_liked",2)){
            driver.findImageElement("feed_liked.png").tap();
            boolean a = mHelper.isExistImageElement("feed_unlike",2);
            boolean b = !mHelper.isExistImageElement("feed_liked",2);
            Assert.assertTrue(a&&b,"个人资料feed取消点赞失败");
        } else{
            driver.findImageElement("feed_unlike.png").tap();
            boolean a = !mHelper.isExistImageElement("feed_unlike",2);
            boolean b = mHelper.isExistImageElement("feed_liked",2);
            Assert.assertTrue(a&&b,"个人资料feed点赞失败");
        }
    }

    @Test(groups = { "profile" })
    public void test019() throws InterruptedException {
        minePage.headIcon.click();
        minePage.fansLabel.click();
        minePage.fansListViewItem.click();
        mHelper.searchBy(minePage.hisfeedBy,10);
        mHelper.moveToHalfScreen(minePage.hisfeedTag);
        minePage.profileFeedMoreBtn.click();
        minePage.profileReportBtn.click();
        boolean report = mHelper.waitForVisible(driver, minePage.reportReason, 10);
        Assert.assertTrue(report, "feed举报 失败");
    }

    @Test(groups = { "profile" })
    public void test020() throws InterruptedException {
        minePage.headIcon.click();
        minePage.fansLabel.click();
        minePage.fansListViewItem.click();
        if (minePage.profileFollowBtn.getText().equals("关注")){
            minePage.profileFollowBtn.click();
        }
        mHelper.searchBy(minePage.hisfeedBy,10);
        mHelper.moveToHalfScreen(minePage.hisfeedTag);
        boolean a = mHelper.swipeUpLoadMore(minePage.feedContentLabel,5);
        Assert.assertTrue(a,"个人资料feed更多加载失败");
    }

    @Test(groups = { "profile" })
    public void test021() throws InterruptedException {
        minePage.headIcon.click();
        minePage.fansLabel.click();
        minePage.fansListViewItem.click();
        mHelper.searchBy(minePage.hisfeedBy,10);
        mHelper.moveToHalfScreen(minePage.hisfeedTag);
        minePage.profileMoreBtn.click();
        minePage.profileReportBtn.click();
        boolean report = mHelper.waitForVisible(driver, minePage.reportReason, 10);
        Assert.assertTrue(report, "举报用户 失败");
    }

    @Test(groups = { "mine" })
    public void test022() throws MalformedURLException, InterruptedException {
        boolean a = !minePage.sportsCountLabel.getText().equals("");
        boolean b = !minePage.sportsLastLabel.getText().equals("");
        minePage.historyViewBtn.click();
        boolean c = mHelper.isExistImageElement("history_detail",2);
        Assert.assertTrue(a&&b&&c, "我的历史记录图例显示有误");
    }
}
