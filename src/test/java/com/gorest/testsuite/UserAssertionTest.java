package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void start() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200);
    }
    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("size()", equalTo(20));
    }

    //2. Verify the if the name of id = 5914068 is equal to ”Bhilangana Dhawan”
    @Test
    public void test002() {
        response.body("find{it.id == 5914068}.name", equalTo("Goswami Prajapat"));
    }

    //3. Check the single ‘Name’ in the Array list (Tara Panicker)
    @Test
    public void test003() {
        response.body("name", hasItem("Tara Panicker"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Heema Kaniyar, Dwaipayan Trivedi, Rahul Iyengar )
    @Test
    public void test004() {
        response.body("name", hasItems("Heema Kaniyar", "Dwaipayan Trivedi", "Rahul Iyengar"));
    }

    //5. Verify the email of userid = 5914064 is equal “deshpande_bhagvan_pres@terry-miller.test”
    @Test
    public void test005() {
        response.body("find{it.id == 5914064}.email", equalTo("deshpande_bhagvan_pres@terry-miller.test"));
        //response.body("find{it.id == 5914064}.email", equalTo("ramaa_banerjee@roob.example"));
    }

    //6. Verify the status is “Active” of user name is “Mani Banerjee”
    @Test
    public void test006() {
        response.body("find{it.name == 'Mani Banerjee'}.status", equalTo("active"));
    }

    //7. Verify the Gender = male
    @Test
    public void test007() {
        response.body("find{it.name == 'Heema Kaniyar'}.gender", equalTo("male"));
    }
}
