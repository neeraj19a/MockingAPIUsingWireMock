public class StubServerMain {

    public static Stubs stubs = new Stubs();

    public static void main(String[] args) {
        stubs.setUp()
                .stubForCreateCart("CreateCartSuccessResponse.json")
                .stubForGetCartSingle("CreateCartSuccessResponse.json")
                .status();
    }
}