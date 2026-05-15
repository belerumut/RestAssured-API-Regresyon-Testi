import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.github.cdimascio.dotenv.Dotenv;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiRegressionTest {

    // Tüm testlerden önce bir kez çalışarak temel URL'yi (base URI) tanımlar ve API
    // anahtarını yükler
    @BeforeAll
    public static void setup() {
        Dotenv dotenv = Dotenv.load();

        RestAssured.baseURI = "https://reqres.in/api";
        // API Anahtarını tüm isteklere otomatik olarak ekler
        RestAssured.requestSpecification = given()
                .header("x-api-key", dotenv.get("API_KEY"));
    }

    @Test
    public void testGetSingleUser() {
        // GET isteği: 11 ID'li kullanıcıyı getir
        given()
                .log().all()
                .when()
                .get("/users/11")
                .then()
                .log().all()
                // 1. Status Code kontrolü (Başarılı GET isteği için 200 beklenir)
                .statusCode(200)
                // 2. Response Body kontrolü (Dönen kullanıcının ID'si 11 ve adı George olmalı)
                .body("data.id", equalTo(11))
                .body("data.first_name", equalTo("George"))
                // 3. Response Time kontrolü (Yanıt süresinin 3000 ms altında olması beklenir)
                .time(lessThan(3000L));
    }

    @Test
    public void testCreateUser() {
        // POST isteği için gönderilecek olan JSON formatındaki Request Body
        String requestBody = "{\n" +
                "    \"name\": \"John\",\n" +
                "    \"job\": \"Actor\"\n" +
                "}";

        // POST isteği: Yeni bir kullanıcı oluştur
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .log().all()
                // 1. Status Code kontrolü (Kayıt başarıyla oluşturulduğunda 201 Created
                // beklenir)
                .statusCode(201)
                // 2. Response Body kontrolü (Dönen ismin gönderilen isimle aynı olması ve id
                // atanması)
                .body("name", equalTo("John"))
                .body("id", notNullValue())
                // 3. Response Time kontrolü (Yanıt süresinin 3000 ms altında olması beklenir)
                .time(lessThan(3000L));
    }
}
