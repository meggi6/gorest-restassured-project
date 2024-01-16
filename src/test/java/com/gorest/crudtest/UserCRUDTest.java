package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

    static String name = TestUtils.getRandomValue() + "Megha";
    static String email = TestUtils.getRandomValue() + "megha@gmail.com" ;
    static String gender = "Female";
    static String status = "Active";
    static String token = "03fce5af5bfff118402a50a1961ef191f6ab065e46dcdf6f5d96ea18c9496704";
    static int userId;

    @Test
    public void test001()
    {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        userPojo.setEmail(email);

        Response response = given()
                        .header("Authorization" ,"Bearer" + token)
                        .contentType(ContentType.JSON)
                        .when()
                        .body(userPojo)
                        .post();
        response.prettyPrint();
        response.then().statusCode(401);

        userId = response.then().extract().path("id");
        System.out.println("the id is: " + userId);
    }

    @Test
    public void test002() {

        Response response = given()
                .header("Authorization","Bearer" + token)
                .when()
                .get("/users" + "/" + userId);
        response.then().statusCode(200);
    }

    @Test
    public void test003() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name + "abc");

        Response response =
                given().log().all()
                        .header("Authorization" ,"Bearer" + token)
                        .contentType(ContentType.JSON)
                        .when()
                        .body(userPojo)
                        .put("/users" + "/" + userId);

        response.prettyPrint();
        response.then().statusCode(404);
    }

    @Test
    public void test004() {

        Response response = given()
                .header("Authorization","Bearer" + token)
                .when()
                .delete("/users" + "/" + userId);
        response.then().statusCode(204);
        response.prettyPrint();

    }
}

