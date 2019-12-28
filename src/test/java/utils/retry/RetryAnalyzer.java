package utils.retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer  {
    private int currentRetry = 0;

    @Override
    public boolean retry(ITestResult result) {
        RetryConfig retryConfig = result.getMethod()
                .getConstructorOrMethod()
                .getMethod()
                .getAnnotation(RetryConfig.class);
        int maxRetry = (retryConfig==null)?1:retryConfig.maxRetry();
        int retryDelay = (retryConfig==null)?0:retryConfig.retryDelay();
        if (++currentRetry > maxRetry) {
            currentRetry = 0;
            return false;
        }
        else {
            if (retryDelay != 0) {
                try {
                    Thread.sleep(retryDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    }
}