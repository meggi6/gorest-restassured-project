package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void start() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    //1. Extract the title
    @Test
    public void test001() {
        List<String> listOfTitles = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List Of All Titles Are : " + listOfTitles);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total number of record
    @Test
    public void test002() {
        List<Integer> totalNoOfRecords = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of records are : " + totalNoOfRecords.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the body of 15th record
    @Test
    public void test003() {
        String body = response.extract().path("[14].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of 15th record is : " + body);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the user_id of all the records
    @Test
    public void test004() {
        List<Integer> listOfUserIds = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The user_id of all the records are : " + listOfUserIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the title of all the records
    @Test
    public void test005() {
        List<String> listOfTitle = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all the records are : " + listOfTitle);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Extract the title of all records whose user_id = 5914200
    @Test
    public void test006() {
        List<String> titleOfUserId = response.extract().path("findAll{it.user_id = 5914200}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all records whose user_id = 5914058 : " + titleOfUserId);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Extract the body of all records whose id = 93995
    @Test
    public void test007() {
        List<String> allBodyRecords = response.extract().path("findAll{it.id = 93995}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of all records whose id = 93826 : " + allBodyRecords);
        System.out.println("------------------End of Test---------------------------");
    }
}
