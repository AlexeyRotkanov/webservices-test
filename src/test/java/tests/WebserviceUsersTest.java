package tests;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.ResponseBody;
import objects.User;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tools.GetProperty;

public class WebserviceUsersTest {

    private final String USERS_URI = "/users";
    private final String EXPECTED_CONTENT_TYPE_VALUE = "application/json; charset=utf-8";
    private final int EXPECTED_NUMBER_OF_USERS = 10;

    @BeforeTest
    public void testPreparing() {

        RestAssured.baseURI = GetProperty.getValueByKey("baseURI");
    }

    @Test(description = "Checking that http status code is 200")
    public void verifyHttpStatusCode() {

        int actualResultCode = RestAssured
                .when()
                .get(USERS_URI)
                .getStatusCode();

        Assert.assertEquals(actualResultCode, 200, "Actual http status code is not equal to expected!");
    }

    @Test(description = "Checking that headers contain content-type header with correct value")
    public void verifyHttpResponseHeader() {

        Headers actualHeaders = RestAssured
                .when()
                .get(USERS_URI)
                .getHeaders();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(actualHeaders.hasHeaderWithName("Content-Type"),
                "Headers do not contain content-type header");

        String actualContentTypeHeaderValue = actualHeaders.getValue("Content-Type");
        softAssert.assertEquals(actualContentTypeHeaderValue, EXPECTED_CONTENT_TYPE_VALUE,
                "Incorrect content-type header!");

        softAssert.assertAll();
    }

    @Test(description = "Checking the response body")
    public void verifyHttpResponseBody() {

        ResponseBody<?> actualBody = RestAssured
                .when()
                .get(USERS_URI)
                .body();

        User[] actualUsersList = actualBody.as(User[].class);

        Assert.assertEquals(actualUsersList.length, EXPECTED_NUMBER_OF_USERS,
                "Number of users is not as expected!");
    }
}
