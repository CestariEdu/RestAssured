package testCases.general;

import static com.data.suite.TestTags.POKEMON;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import testCases.BaseAPI;
import com.config.ConfigurationManager;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class pokemonTest extends BaseAPI {

    @BeforeEach
    public void setup() {
        configuration = ConfigurationManager.getConfiguration();
    }

    @AfterEach
    void tearDown() {
        basePath = configuration.basePath();
    }

    @Test
    @Tag(POKEMON)
    @DisplayName("Should get a list of pokemon items")
    void shouldGetPokemonItems() {
        given().
            contentType(ContentType.JSON).
        when().
            get("/item").
        then().
            statusCode(200).
            body(matchesJsonSchemaInClasspath("schemas/pokemonList.json")).
            body("count", not(0));
    }
}
