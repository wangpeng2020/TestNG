package service;

import static io.restassured.RestAssured.given;

public class Work {
    private static final String corId = "wwa664a53b85c09ea0";
    private static final String corpSecret = "iEOYzbJ48K-7XgCTuj9WdFT6RSOjEgsi2UMiBgZUFmU";
    private static Work work;
    private String access_token;

    public static Work getInstance() {
        if (work == null) {
            work = new Work();
        }
        return work;
    }

    public String getToken() {
        if (access_token == null) {
            access_token =
                    given()
                            .param("corpid", corId)
                            .param("corpsecret",corpSecret)
                    .when()
                            .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                    .then().log().all()
                            .extract().body().path("access_token");
            System.out.println(access_token);
        }
        return access_token;
    }
}
