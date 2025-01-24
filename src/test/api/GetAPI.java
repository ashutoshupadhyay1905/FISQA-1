package test.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import main.apiUI.constant.Constant;
import main.apiUI.endpoint.Endpoint;
import main.automationframework.APIBase;
import main.automationframework.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GetAPI {
    private static APIBase base;

    @BeforeClass
    public static void setup() {
        String baseUrl = Config.getProperty("baseUrl");
        base = new APIBase(baseUrl);
    }

    @Test
    public void testGetUsers() throws JsonProcessingException {
        Response response = base.get(Endpoint.endpoint, null, null);
        Assert.assertEquals(String.valueOf(response.getStatusCode()), Constant.successTwoHundredCode,"status match");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response.getBody().asString());

        JsonNode bpiNode = rootNode.path("bpi");


        Iterator<String> keys = bpiNode.fieldNames();

        System.out.println("Keys under 'bpi':");
        List<String> listOfBPI= new ArrayList<>();

        while (keys.hasNext()) {
            String key = keys.next();
            System.out.println(key);
            listOfBPI.add(key);

        }
        List<String> list = Arrays.asList("USD","GBP","EUR");
        Assert.assertEquals(list,listOfBPI);
        String GBPDescription= rootNode.path("bpi").get("GBP").get("description").asText();
        Assert.assertEquals(GBPDescription,Constant.GBPDescription,"description match");

    }

}
