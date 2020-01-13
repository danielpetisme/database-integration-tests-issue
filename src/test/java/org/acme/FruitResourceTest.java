package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class FruitResourceTest {

    private final Logger log = LoggerFactory.getLogger(FruitResourceTest.class);



    @Test
    @Transactional
    public void testHelloEndpoint() {
        Fruit apple = new Fruit();
        apple.name = "apple";

        Fruit.persist(apple);
        log.info("Fruits Count: {}", Fruit.count());

        List<Fruit> fruits = given()
                .when().get("/fruits")
                .then()
                .statusCode(200)
                .extract().body().as(getFruitTypeRef());
        assertThat(fruits.size(), is(1));
    }

    private TypeRef<List<Fruit>> getFruitTypeRef() {
        return new TypeRef<List<Fruit>>() {
            // Kept empty on purpose
        };
    }

}