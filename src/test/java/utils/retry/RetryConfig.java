package utils.retry;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RetryConfig {
    int maxRetry() default  1;
    int retryDelay() default 0;
}
