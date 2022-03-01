package tests;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener {
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        System.out.printf("Test started: %s, \n", result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        System.out.printf("Test Success: %s, \n", result.getName());
    }

    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        System.out.printf("Test failed: %s, \n", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        System.out.printf("Test skipped: %s, \n", result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
        System.out.printf("Test FailedButWithinSuccessPercentage: %s, \n", result.getName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
        System.out.printf("Test FailedWithTimeout: %s, \n", result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}