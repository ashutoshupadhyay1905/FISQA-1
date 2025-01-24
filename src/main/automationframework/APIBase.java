package main.automationframework;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class APIBase {

    /**
     * Initializes the base URI for API requests.
     *
     * @param baseUri The base URI of the API.
     */
    public  APIBase (String baseUri) {
        RestAssured.baseURI = baseUri;
    }

//    /**
//     * Sends an API request using RestAssured.
//     *
//     * @param endpoint   The API endpoint (relative to the base URI).
//     * @param method     The HTTP method (GET, POST, PUT, DELETE).
//     * @param headers    A map of request headers.
//     * @param body       The request body for POST and PUT methods (can be null for GET and DELETE).
//     * @return           The API response as a RestAssured Response object.
//     */
//    public static Response sendRequest(String endpoint, String method, Map<String, String> headers, String body) {
//        RequestSpecification request = RestAssured.given();
//
//        // Set headers
//        if (headers != null) {
//            request.headers(headers);
//        }
//
//        // Set body if applicable
//        if (body != null && (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT"))) {
//            request.body(body);
//        }
//
//        // Send the request based on the method
//        switch (method.toUpperCase()) {
//            case "GET":
//                return request.get(endpoint);
//            case "POST":
//                return request.post(endpoint);
//            case "PUT":
//                return request.put(endpoint);
//            case "DELETE":
//                return request.delete(endpoint);
//            default:
//                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
//        }
//    }
    // Method to create a request with headers and query params
    protected RequestSpecification createRequest(Map<String, String> headers, Map<String, String> queryParams) {
        RequestSpecification request = RestAssured.given();

        // Add headers
        if (headers != null) {
            request.headers(headers);
        }

        // Add query params
        if (queryParams != null) {
            request.queryParams(queryParams);
        }

        return request;
    }

    // Generic GET method
    public Response get(String endpoint, Map<String, String> headers, Map<String, String> queryParams) {
        return createRequest(headers, queryParams).get(endpoint);
    }

    // Generic POST method
    public Response post(String endpoint, Map<String, String> headers, Object body) {
        return createRequest(headers, null).body(body).post(endpoint);
    }

    // Generic PUT method
    public Response put(String endpoint, Map<String, String> headers, Object body) {
        return createRequest(headers, null).body(body).put(endpoint);
    }

    // Generic DELETE method
    public Response delete(String endpoint, Map<String, String> headers) {
        return createRequest(headers, null).delete(endpoint);
    }
}
