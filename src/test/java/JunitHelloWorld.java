
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;


public class JunitHelloWorld {

    @Test
    public void testSum1() {
        Assert.assertThat(40, greaterThan(30));
    }

    @Test
    public void testSum2() {
        Assert.assertThat("12345", containsString("234"));
        Assert.assertThat(Arrays.asList("foo", "bar"), containsInAnyOrder("bar", "foo"));
    }
}

