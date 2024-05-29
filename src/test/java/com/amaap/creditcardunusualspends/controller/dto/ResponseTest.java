package com.amaap.creditcardunusualspends.controller.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResponseTest {

    @Test
    void shouldBeAbleToInstantiateResponse() {
        // arrange
        Http httpStatus = Http.OK;
        String message = "Customer Created";

        // act
        Response response = new Response(httpStatus, message);

        // assert
        assertNotNull(response);
    }

    @Test
    void shouldBeAbleToCheckEquality() {
        // arrange
        Http httpStatus = Http.OK;
        String message = "Customer Created";
        Response response1 = new Response(httpStatus, message);
        Response response2 = new Response(httpStatus, message);

        // act & assert
        assertEquals(response1, response2, "Responses with the same status and message should be equal");
    }

    @Test
    void shouldBeAbleToGenerateConsistentHashCode() {
        // arrange
        Http httpStatus = Http.OK;
        String message = "Customer Created";
        Response response1 = new Response(httpStatus, message);
        Response response2 = new Response(httpStatus, message);

        // act
        int hashCode1 = response1.hashCode();
        int hashCode2 = response2.hashCode();

        // assert
        assertEquals(hashCode1, hashCode2, "Responses with the same status and message should have the same hash code");
    }

    @Test
    void shouldBeAbleToCheckInequalityForDifferentResponses() {
        // arrange
        Response response1 = new Response(Http.OK, "Customer Created");
        Response response2 = new Response(Http.BAD_REQUEST, "User Not Created");

        // act & assert
        assertNotEquals(response1, response2, "Responses with different status or message should not be equal");
    }

    @Test
    void shouldBeAbleToHandleNullEquality() {
        // arrange
        Response response = new Response(Http.OK, "Customer Created");

        // act & assert
        assertNotEquals(response, null, "Response should not be equal to null");
    }

    @Test
    void shouldBeAbleToGenerateCorrectStringRepresentation() {
        // arrange
        Http httpStatus = Http.OK;
        String message = "Customer Created";
        Response response = new Response(httpStatus, message);
        String expectedString = "Response{http=OK, message='Customer Created'}";

        // act
        String actualString = response.toString();

        // assert
        assertEquals(expectedString, actualString, "Response toString should match the expected format");
    }
}
