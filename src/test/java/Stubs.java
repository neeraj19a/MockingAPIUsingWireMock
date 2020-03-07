
import com.github.tomakehurst.wiremock.WireMockServer;
import utils.JsonUtil;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class Stubs {
    private JsonUtil jsonUtil;
    public WireMockServer wireMockServer;

    public Stubs setUp() {
        wireMockServer = new WireMockServer(3467);
        wireMockServer.start();
        jsonUtil = new JsonUtil();
        return this;
    }

    public Stubs resetServer() {
        wireMockServer.resetAll();
        return this;
    }

    public Stubs stubForCreateCart(String responseFileName) {
        wireMockServer.stubFor(post("/carts")
                .withHeader("Content-Type", equalToIgnoreCase("application/json"))
                .withHeader("Accept", equalToIgnoreCase("application/json"))
                .withHeader("Version", equalToIgnoreCase("v1"))
                .withHeader("Client", equalToIgnoreCase("Android"))
                .withHeader("Authorization", equalToIgnoreCase("Bearer MySecretToken"))
                .withRequestBody(matchingJsonPath("$.customer.firstName", equalTo("Neeraj")))
                .withRequestBody(matchingJsonPath("$.customer.lastName", equalTo("Bakhtani")))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/" + responseFileName)));
        return this;
    }


    public Stubs stubForGetCartSingle(String responseFileName) {
        wireMockServer.stubFor(get("/carts/quil514c-9678-4a4b-b32d-550b7fc3cfb2?productCount=1")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/" + responseFileName)));
        return this;
    }


    public Stubs status() {
        System.out.println("Stubs Started!");
        return this;
    }
}