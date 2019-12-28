package service.tag.testcase;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import service.tag.api.Tag;

public class TestTag {
    private static Tag tag = new Tag();

    @Test
    public void list() {
        tag.list().then().statusCode(200);
    }

    @Test
    public void creat() {
        String name = "tag1";
        tag.create(name).then().statusCode(200);
    }

    @Test
    public void delete() {
        String name = "testDelete";
        Integer tagId = tag.create(name).path("tagid");
        System.out.println(tagId);
        tag.delete(tagId).then().statusCode(200);
    }
}
