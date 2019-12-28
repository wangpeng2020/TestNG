import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.*;
import org.testng.Assert;
import utils.retry.RetryAnalyzerListener;
import utils.data.StaticProvider;

import java.util.*;



public class TestNGHelloWorld {
    private static Comparator<String> cmp = new Comparator<String>(){
        @Override
        public int compare(String o1, String o2) {
            int ret = o1.compareTo(o2);
            return ret==0 ? 0 : ret;
        }
    };
    private Map<String, String> map = new TreeMap<String, String>();

    @BeforeClass
    public void setUp(){
        System.out.println("setUp!");
        map.put("c","12345a");
        map.put("a","12345");
        map.put("b", "123");
        map.put("A", "123");
        map.put("B", "123");
        map.put("D", "123");
        map.put("B", "456");
    }

    @Feature("TreeMap")
    @Story("测试TreeMap排序")
    @Test
    public void helloWorld(){
        System.out.println("My first TestNG testCase!");
        String b = map.get("b");
        Assert.assertEquals("123", b);
        Assert.assertEquals("12345a", map.get("c"));
    }

    @Feature("Split")
    @Story("测试split regex")
    @Description("不包含正则表达式")
    @Test
    public void helloWorld1(){
        String myarr = "http://test.com";
        String[] newarr = myarr.split(",");
        for (String arr :newarr) {
            System.out.println(arr+ newarr);
        }
    }

    @Feature("Split")
    @Story("测试split regex")
    @Description("包含正则表达式")
    @Test
    public void helloWorld2(){
        String myarr = "http://test.com,http://run.com";
        String[] newarr = myarr.split(",");
        for (String arr :newarr) {
            System.out.println(arr);
        }
    }

    @Feature("add")
    @Story("测试参数化加法")
    @Test(groups = "test", dataProvider="sum", dataProviderClass = StaticProvider.class)
    public void testSum(Map<String, String> data) throws InterruptedException {
        Thread.sleep(1000);
        float add1 = Float.parseFloat(data.get("add1"));
        float add2 = Float.parseFloat(data.get("add2"));
        float result = Float.parseFloat(data.get("result"));
        Assert.assertEquals(result, add1+add2);
    }

    @Test(dependsOnMethods = "testSum", retryAnalyzer = RetryAnalyzerListener.class)
    public void testSum2 () {
        Assert.assertEquals(1,2);
    }


    @AfterClass
    public void tearDown(){
        System.out.println("tearDown!");
    }
}