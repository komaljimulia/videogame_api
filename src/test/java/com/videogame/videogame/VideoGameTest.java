package com.videogame.videogame;

import com.videogame.VideoGamePojo;
import com.videogame.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class VideoGameTest extends TestBase {
    public Random randomGenerator = new Random();
    public int randomInt = randomGenerator.nextInt(1000);

    @Test
    //Get
    public void getAllVideoGameInfo() {
        Response response =
                given()
                        .accept( "application/json" )
                        .when()
                        .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    //GET with ID
    public void getSingleProductInfo() {
        Response response =

                given()
                        .accept( "application/json" )
                        .pathParam("id",3)
                        .when()
                        .get("/{id}");
        response.prettyPrint();

    }
    @Test
    //Post
    public void createNewData(){
        VideoGamePojo videoGamePojo=new VideoGamePojo();
        videoGamePojo.setId(randomInt);
        videoGamePojo.setName("Wembley stadium");
        videoGamePojo.setReleaseDate("2021-07-14T19:24:01.924Z");
        videoGamePojo.setReviewScore(80);
        videoGamePojo.setCategory("Adult");
        videoGamePojo.setRating("Good");
       // videoGamePojo.setPrice(20);
        Response response=
                given()
                        .header("Content-Type","application/json")
                        .body(videoGamePojo).accept( "application/json" )
                        .when()
                        .post();
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    //put
    public void updateData(){
        VideoGamePojo videoGamePojo = new VideoGamePojo();
        videoGamePojo.setId(7);
        videoGamePojo.setName("CARD GAME");
        videoGamePojo.setReleaseDate("2021-07-14T19:24:01.924Z");
        videoGamePojo.setReviewScore(780);
        videoGamePojo.setCategory("kids");
        videoGamePojo.setRating("5");

        Response response =
                given()
                        .accept( "application/json" )
                        .header("Content-Type","application/json")
                        .body(videoGamePojo)
                        .when()
                        .patch("/7");
        response.then().statusCode(200);
        response.prettyPrint();

    }
    @Test
    //Delete
    public void deleteData(){
        Response response=
                given()
                        .accept( "application/json" )
                        .pathParam("id",40)
                        .when()
                        .delete("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();


    }

}
