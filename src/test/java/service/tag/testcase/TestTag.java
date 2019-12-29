package service.tag.testcase;

import org.testng.annotations.*;
import org.testng.annotations.Test;
import service.tag.api.Tag;
import java.util.ArrayList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class TestTag {
    private static Tag tag = new Tag();

    @BeforeTest
    public void clearData() {
        //清理数据
        ArrayList<Integer> tagIds = tag.list()
                .then().extract().body().path("taglist.tagid");
        System.out.println(tagIds);
        tagIds.forEach(tagId -> tag.delete(tagId));
    }
    @Test
    public void list() {
        tag.list().then().body("errmsg", equalTo("ok"));
    }

    @Test
    public void create() {
        String tagName = "forCreate";
        tag.create("forCreate").then().body("errmsg", equalTo("created"));
        tag.list().then().body("taglist.findAll {d->d.tagname=='"+tagName+"'}", hasSize(1));
    }

    @Test
    public void delete() {
        String name = "testDelete";
        Integer tagId = tag.create(name).path("tagid");
        System.out.println(tagId);
        tag.delete(tagId).then().body("errmsg", equalTo("deleted"));
    }
}
