import ognl.OgnlException;
import org.junit.Test;
import java.util.Arrays;

public class JunitOgnlTest {

    private JunitOgnl junitOgnl = new JunitOgnl();

    @Test
    public void ognl() throws OgnlException {
        junitOgnl.ognl("assertEquals(1,1)");
    }

    @Test
    public void testOgnl0() throws OgnlException {
        junitOgnl.ognl("assertThat(\"12345\",containsString(\"456\"))");
    }

    @Test
    public void testOgnl1() throws OgnlException {
        junitOgnl.ognl("assertThat(\"12345\",containsString(\"123\"))");
    }

    @Test
    public void testOgnl2() throws OgnlException {
        junitOgnl.ognl("assertThat({\"foo\", \"bar\",\"foo1\",\"bar2\"}, containsInAnyOrder(\"bar2\", \"foo1\",\"foo\",\"bar\"))");
    }

}
