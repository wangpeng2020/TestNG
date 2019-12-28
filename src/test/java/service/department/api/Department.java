package service.department.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import service.Work;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class Department {
    private static Department department = new Department();
    private static Integer departmentId = 1;

    public Response create(String name) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("parentid", departmentId);
        return given().
                queryParam("access_token", Work.getInstance().getToken()).
                body(map).
                contentType(ContentType.JSON).
                when().log().all().
                post("https://qyapi.weixin.qq.com/cgi-bin/department/create").
                then().log().all().
                extract().response();
    }
}
