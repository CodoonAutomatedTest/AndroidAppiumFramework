package com.codoon.common.util;

import com.codoon.test.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.driver.SikuppiumDriver;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.Iterator;

public class TestngListener extends TestListenerAdapter {
    private final static Logger LOG = Logger.getLogger(TestngListener.class);

    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);

        if(tr.getName().equals("test015") || tr.getName().equals("test016")){
            LOG.info(tr.getName()+"开始删除feed");

        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        super.onTestSkipped(tr);
    }

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);
        Iterator<ITestResult> listOfFailedTests = testContext.getFailedTests().getAllResults().iterator();
        while (listOfFailedTests.hasNext()) {
            ITestResult failedTest = (ITestResult) listOfFailedTests.next();
            ITestNGMethod method = failedTest.getMethod();
            if (testContext.getFailedTests().getResults(method).size() > 1) {
                listOfFailedTests.remove();
            } else {
                if (testContext.getPassedTests().getResults(method).size() > 0) {
                    listOfFailedTests.remove();
                }
            }
        }
//        logger.info("开始发送邮件...");
//        JavaMailDemo resultReport = new JavaMailDemo();
//        try {
//        	Thread.sleep(10*1000);
//			resultReport.sendEmail();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
    }

    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
    }
}
