package com.codoon.test;

import com.codoon.common.model.DrycargoPage;
import com.codoon.common.model.HomePage;
import com.codoon.common.util.WebH5Helper;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.driver.CapabilitiesFactory;
import io.driver.SikuppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.codoon.common.model.drycargo.Running.*;
import static com.codoon.common.model.drycargo.Ride.*;
import static com.codoon.common.model.drycargo.Personality.*;
import static com.codoon.common.model.drycargo.Fitness.*;

public class DrycargoTest extends BaseTest {
    private final static Logger LOG = Logger.getLogger(SportCircleTest.class);
    private static HomePage homePage;
    private  int crashCout = 0;
    private static WebH5Helper webH5Helper;
    private static DrycargoPage drycargoPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        homePage = HomePage.getInstance(driver);

        LOG.info("进入运动圈tab首页");
        homePage.drycargoTab.click(); // 点击运动圈tab,进入运动圈首页
        drycargoPage = DrycargoPage.getInstance(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        FileOutputStream out = null;
        if(mHelper.isExistBySelector(driver,homePage.startSportsBy)){
            String path = System.getProperty("user.dir");
            crashCout++;
            try{
                out = new FileOutputStream(new File(path+"/CrashCout.log"));
                out.write(crashCout);
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                out.close();
            }
            return;
        }else if (driver == null){
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
        }
        LOG.info("当前case执行完成，返回到App一级界面");
        mHelper.pressBackToHomePage(5);
    }

    @Test(groups = { "推荐tab" })
    public void test001() throws InterruptedException {
//		sportsCircleModule.gotoHot(); // 点击“热门”tab
        drycargoPage.recommendTab.click();
        drycargoPage.advertBanner.click(); // 点击广告bannerce

        boolean change = !mHelper.isExistBySelector(driver, drycargoPage.dailyTagTextBy, 5); // 断言，页面是否包含发feed按钮来判断是否发生跳转
        LOG.info("result passed is: " + change);
        Assert.assertTrue(change, "点击广告跳转失败");
    }

    @Test(groups = { "推荐tab" })
    public void test002() throws InterruptedException {
        drycargoPage.recommendTab.click();
        mHelper.moveToViewTop(drycargoPage.dailyHot);
        List<WebElement> list = mHelper.findElementsToDown(drycargoPage.dailyTagTextBy, By.xpath("//android.widget.ImageView/.."),new Dimension(1080,315));
        String title = list.get(0).findElement(drycargoPage.textViewClassBy).getText();
        LOG.info("title-->"+title);
        list.get(0).click();
        Thread.sleep(5000L);
        String title2 = mHelper.getH5AtricleTitle();
        Assert.assertTrue(title.equals(title2),"推荐文章跳转失败");
    }

    @Test(groups = { "推荐tab" })
    public void test003() throws InterruptedException {
        drycargoPage.recommendTab.click();
        mHelper.searchBy(drycargoPage.explanTagTextBy, 20);
        mHelper.moveToHalfScreen(drycargoPage.trainingPlan);
//        WebElement viewer = mHelper.findElementToDown(drycargoPage.explanTagTextBy, 0, drycargoPage.scrollViewClassBy);
        WebElement viewer = mHelper.findElementToDown(drycargoPage.explanTagTextBy, 0, drycargoPage.scrollViewClassBy);
        List<String> training = drycargoPage.swipeLeftTrainingCard(viewer);
        LOG.info(training);
        Assert.assertTrue(training.size()==3,"训练计划卡片个数不等于3个");
    }

    @Test(groups = { "推荐tab" })
    public void test004() throws InterruptedException {
        drycargoPage.recommendTab.click();
        drycargoPage.DrycargoPageClickMore(drycargoPage.explanTagTextBy);
        boolean training = mHelper.isExistBySelector(driver, MobileBy.AndroidUIAutomator("text(\"选择训练计划\")"), 3);
        Assert.assertTrue(training,"跳转训练计划列表");
    }

    @Test(groups = { "推荐tab" })
    public void test005() throws InterruptedException {
        drycargoPage.recommendTab.click();
        mHelper.searchBy(drycargoPage.explanTagTextBy, 20);
        mHelper.moveToHalfScreen(drycargoPage.trainingPlan);
        WebElement viewer = mHelper.findElementToDown(drycargoPage.explanTagTextBy, 0, drycargoPage.scrollViewClassBy);
        boolean detail = drycargoPage.trainingLinkDetails(viewer);
        Assert.assertTrue(detail,"跳转对应训练计划详情页失败");
    }

    @Test(groups = { "推荐tab" })
    public void test006() throws InterruptedException {
        drycargoPage.recommendTab.click();
        mHelper.searchBy(drycargoPage.maybelikeTextBy, 30);
        mHelper.moveToViewTop(drycargoPage.maybeLike);
//        List<WebElement> article = mHelper.findElementsToDown(drycargoPage.maybelikeTextBy, drycargoPage.groupViewClassBy, new Dimension(1080, 315));
        List<WebElement> article = mHelper.findElementsToDown(drycargoPage.maybelikeTextBy, drycargoPage.viewClassBy, new Dimension(1080, 315));
        String txt1 = mHelper.getElementsTextBySelector(article, drycargoPage.textViewClassBy);
        LOG.info(txt1);
        mHelper.swipeDownQuickly(5);
        mHelper.searchBy(drycargoPage.maybelikeTextBy, 20);
        mHelper.moveToViewTop(drycargoPage.maybeLike);
//        List<WebElement> article2 = mHelper.findElementsToDown(drycargoPage.maybelikeTextBy, drycargoPage.groupViewClassBy, new Dimension(1080, 315));
        List<WebElement> article2 = mHelper.findElementsToDown(drycargoPage.maybelikeTextBy, drycargoPage.viewClassBy, new Dimension(1080, 315));
//        List<WebElement> article2 = mHelper.findElementsToDown(drycargoPage.maybelikeTextBy, drycargoPage.groupViewClassBy, new Dimension(1080, 315));
        String txt2 = mHelper.getElementsTextBySelector(article2, drycargoPage.textViewClassBy);
        LOG.info(txt2);
        Assert.assertTrue(!txt1.equals(txt2),"猜你喜欢刷新失败");
    }

    @Test(groups = { "非推荐tab" })
    public void test007() throws InterruptedException {
        drycargoPage.runTab.click();
//        boolean run = mHelper.searchDownBy(By.name(DRYGOODS_RUNNING_KNOWLEDGE_NAME), 5);
        boolean run = mHelper.searchDownBy(drycargoPage.runKnowledgeTextBy, 5);
        LOG.info(run);
        drycargoPage.rideTab.click();
//        boolean ride = mHelper.searchDownBy(By.name(DRYGOODS_RIDE_KNOWLEDGE_NAME), 5);
        boolean ride = mHelper.searchDownBy(drycargoPage.rideKnowledgeTextBy, 5);
        LOG.info(ride);
        drycargoPage.fitnessTab.click();
//        boolean fitness = mHelper.searchDownBy(By.name(DRYGOODS_FITNESS_BASIC_NAME), 5);
        boolean fitness = mHelper.searchDownBy(drycargoPage.basicFitnessTextBy, 5);
        LOG.info(fitness);
        drycargoPage.specialTab.click();
//        boolean outside = mHelper.searchDownBy(By.name(DRYGOODS_SPECAIL_OUTSIDE_NAME), 5);
        boolean outside = mHelper.searchDownBy(drycargoPage.specFashionTextBy, 5);
        LOG.info(outside);
        drycargoPage.recommendTab.click();
        Thread.sleep(3000L);
//        boolean dailyHot = mHelper.searchDownBy(By.name(DRYGOODS_RECOMMEND_DAILYHOT_NAME), 5);
        boolean dailyHot = mHelper.searchDownBy(drycargoPage.dailyTagTextBy, 10);
        LOG.info(dailyHot);

        Assert.assertTrue(run && ride && fitness && outside && dailyHot, "切换tab失败");
    }

    @Test(groups = { "非推荐tab" })
    public void     test008() throws InterruptedException {
        drycargoPage.runTab.click();
        mHelper.searchDownBy(drycargoPage.runKnowledgeTextBy, 10);
        boolean run1 = drycargoPage.articleCardForwardingByTag(drycargoPage.runKnowledgeTextBy);
        boolean run2 = drycargoPage.articleCardForwardingByTag(drycargoPage.runTrainTextBy);
        boolean run6 =drycargoPage.articleCardForwardingByTag(drycargoPage.runStoryTextBy);
        boolean run3 = drycargoPage.articleCardForwardingByTag(drycargoPage.runNewsTextBy);
        boolean run4 = drycargoPage.articleCardForwardingByTag(drycargoPage.runTopicTextBy);
        boolean run5 = drycargoPage.articleCardForwardingByTag(drycargoPage.runEquipTextBy);
//        boolean run6 =drycargoPage.articleCardForwardingByTag(drycargoPage.runStoryTextBy);
        Assert.assertTrue(run1&&run2&&run3&&run4&&run5&&run6,"跑步tab文章跳转失败");

        drycargoPage.rideTab.click();
        boolean ride1 = drycargoPage.articleCardForwardingByTag(drycargoPage.rideKnowledgeTextBy);
        boolean ride2 = drycargoPage.articleCardForwardingByTag(drycargoPage.rideTopicTextBy);
        boolean ride3 = drycargoPage.articleCardForwardingByTag(drycargoPage.rideNewsTextBy);
//        boolean ride4 = drycargoPage.articleCardForwardingByTag(drycargoPage.rideTalentTextBy);
        boolean ride5 = drycargoPage.articleCardForwardingByTag(drycargoPage.rideColumnTextBy);
        boolean ride6 = drycargoPage.articleCardForwardingByTag(drycargoPage.rideTravelTextBy);
        Assert.assertTrue(ride1&&ride2&&ride3&&ride5&&ride6,"骑行tab文章跳转失败");

        drycargoPage.fitnessTab.click();
        boolean fitness1 = drycargoPage.articleCardForwardingByTag(drycargoPage.basicFitnessTextBy);
        boolean fitness2 = drycargoPage.articleCardForwardingByTag(drycargoPage.lossWeightTextBy);
        boolean fitness3 = drycargoPage.articleCardForwardingByTag(drycargoPage.foodFitnessTextBy);
        boolean fitness4 = drycargoPage.articleCardForwardingByTag(drycargoPage.talentFitnessTextBy);
        boolean fitness5 = drycargoPage.articleCardForwardingByTag(drycargoPage.powerFitnessTextBy);
        Assert.assertTrue(fitness1&&fitness2&&fitness3&&fitness4&&fitness5,"健身tab文章跳转失败");

        drycargoPage.specialTab.click();
        boolean spec1 = drycargoPage.articleCardForwardingByTag(drycargoPage.specFashionTextBy);
        boolean spec2 = drycargoPage.articleCardForwardingByTag(drycargoPage.specExtreTextBy);
        boolean spec3 = drycargoPage.articleCardForwardingByTag(drycargoPage.specTriathTextBy);
        boolean spec4 = drycargoPage.articleCardForwardingByTag(drycargoPage.specOutsideTextBy);
        boolean spec5 = drycargoPage.articleCardForwardingByTag(drycargoPage.specHotTextBy);
        boolean spec6 = drycargoPage.articleCardForwardingByTag(drycargoPage.specGroupTextBy);
        Assert.assertTrue(spec1&&spec2&&spec3&&spec4&&spec5&&spec6,"个性tab文章跳转失败");

    }

    @Test(groups = { "非推荐tab" })
    public void test009() throws InterruptedException{
        drycargoPage.runTab.click();
//		String aritcle = drycargoPage.cardTitle.getText();
        String author = drycargoPage.cardAuthor.getText();
        drycargoPage.cardTitle.click();
        Thread.sleep(10000L);

        boolean right = drycargoPage.followedAuthorInH5(author);
        Assert.assertTrue(right, "关注／取消关注 失败");
    }

    @Test(groups = { "非推荐tab" })
    public void test010() throws InterruptedException, IOException{
        drycargoPage.rideTab.click();
        drycargoPage.cardTitle.click();
        boolean send = drycargoPage.sendInfoForComment("Good");
        LOG.info(send);
        Assert.assertTrue(send,"干货文章评论 失败");
    }

    @Test(groups = { "非推荐tab" })
    public void test011() throws InterruptedException{
        drycargoPage.fitnessTab.click();
        String aritcle = drycargoPage.cardTitle.getText();
        LOG.info(aritcle);
        drycargoPage.cardTitle.click();
        Thread.sleep(3000L);
        drycargoPage.articleCollectBtn.click();
        boolean success = drycargoPage.checkCollectionInMine(aritcle);
        Assert.assertTrue(success,"文章收藏失败");
    }

    @Test(groups = { "非推荐tab" })
    public void test012() throws InterruptedException{
        drycargoPage.runTab.click();
        String aritcle = drycargoPage.cardTitle.getText();
        LOG.info(aritcle);
        boolean show = drycargoPage.showAllComments();
        Assert.assertTrue(show,"查看全部评论 失败");
    }
}
