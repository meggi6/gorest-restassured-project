package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class PostsAssertionTest extends TestBase {
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

    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("size()", equalTo(25));
    }

    //2. Verify the if the title of id = 93941 is equal to ”Amissio demens cena degusto vigor bellicus brevis.”
    @Test
    public void test002() {
        response.body("find{it.id ==93941 }.title", equalTo("Amissio demens cena degusto vigor bellicus brevis."));

    }

    //3. Check the single user_id in the Array list (5914156)
    @Test
    public void test003() {
        response.body("[5].user_id", equalTo(5914156));
    }

    //4. Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004() {
        response.body("id", hasItems(93821, 93822, 93823));
    }

    //5. Verify the body of userid = 5914197 is equal “Desidero vorax adsum. Non confero clarus.
    //Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa usus
    //vos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptio
    //tumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus
    //acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”
    @Test
    public void test005() {
        response.body("find{it.user_id == 5914056}.body", equalTo("Audacia voluptas demum. Surculus illum adsum. Itaque ab cimentarius. Thymbra curvo quos. Ubi ara charisma. Tener taceo quam. Desino advoco vero. Nulla fugiat cras. Unus adduco accusamus. Cinis soluta aveho. Verecundia tandem bibo. Tergo quaerat arca. Usitas cornu aut. Temperantia quia tardus. Surgo certo voco. Demergo voro vulgaris. Aspernatur capillus adipisci. Adficio ullam consuasor. Aegre aveho aliquam."));
    }
}
