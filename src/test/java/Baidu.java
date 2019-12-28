import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Baidu  {
    WireMockServer wireMockServer;

    //wiremock
    @BeforeMethod
    public void setup () {
        wireMockServer = new WireMockServer(8090);
        wireMockServer.start();
        setupStub();
    }

    @AfterMethod
    public void teardown () {
        wireMockServer.stop();
    }

    public void setupStub() {
        wireMockServer.stubFor(get(urlEqualTo("/an/endpoint"))
                .willReturn(aResponse().withHeader("Content-Type", "text/plain")
                        .withStatus(200)
                        .withBodyFile("glossary.json")));
    }

    @Ignore
    @Test
    public void testGetHtml() {
        given()
                .log().all().get("http://www.baidu.com")
                .then().statusCode(200);
    }

    @Ignore
    @Test
    public void testXueQiu() {
        useRelaxedHTTPSValidation();
        given()
                .queryParam("code","sogo")
                .header("cookie","aliyungf_tc=AQAAAKDn3za8hggAyemAt2gdOvXetwbE; acw_tc=2760823215774918855693810ecca4a9ae764b792548307abacaf341531766; xq_a_token=c9d3b00a3bd89b210c0024ce7a2e049f437d4df3; xq_r_token=8712d4cae3deaa2f3a3d130127db7a20adc86fb2; u=431577491948583; Hm_lvt_1db88642e346389874251b5a1eded6e3=1577491950; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1577491950; device_id=24700f9f1986800ab4fcc880530dd0ed")
                .when().
                get("http://xueqiu.com/stock/search.json")
                .then().
                statusCode(200).
                body("stocks.name", containsString("搜狗"));
    }

    @Test
    public void testStatusCodePositive() {
        given().
        when().
                get("http://localhost:8090/an/endpoint").
        then().log().all().
        assertThat().statusCode(200);
    }

    @Test
    public void testStatusCodeNegative() {
        given().
        when().
                get("http://localhost:8090/another/endpoint").
        then().
                assertThat().statusCode(404);
    }

    @Test
    public void testResponseContents() {
        Response response =  given().when().get("http://localhost:8090/an/endpoint");
        String title = response.jsonPath().get("glossary.title");
        System.out.println(title);
        Assert.assertEquals("example glossary", title);
    }

    @Ignore
    @Test
    public void testLogin() {
        useRelaxedHTTPSValidation();
        given().proxy("127.0.0.1", 8888).
                queryParam("email","pw@arcvideo.com").
                queryParam("password","xxxxxx").
        when().
                get("https://player.stg.danghongyun.com/login").
        then().log().all().
                statusCode(200);
    }

    @Test
    public void testLoginApi() {
        Map<String, String> map = new HashMap<String, String>();
        given().proxy("127.0.0.1", 8888).
                queryParam("action","login").
                formParam("account","pw@arcvideo.com").
                formParam("password","619451").
                when().log().all().
                post("http://api.stg.danghongyun.com/rest").
                then().log().all().
                statusCode(200).
                body("result.contact", (Matcher<?>) equalTo("汪鹏1"));
    }

}
