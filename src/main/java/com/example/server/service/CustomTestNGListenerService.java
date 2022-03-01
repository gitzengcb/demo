package com.example.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Created by KentZhong on 2018/11/2.
 */
    public class CustomTestNGListenerService extends TestListenerAdapter{
    private static final Logger logger = LoggerFactory.getLogger(CustomTestNGListenerService.class);

    @Override
    public void onTestFailure(ITestResult tr) {
        logger.info("******[TestCase] ===> [" + tr.getName()+ "] ======>[Failure]******\n" );
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        logger.info("******[TestCase]: ===> " + tr.getName()+ "] ======>[Skipped]******\n");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        logger.info("******[TestCase]: ===> " + tr.getName()+ "] ======>[Success]******\n");
    }



}
