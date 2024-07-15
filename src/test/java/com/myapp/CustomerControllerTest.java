package com.myapp;

import static io.restassured.RestAssured.given;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.myapp.model.Customer;
import com.myapp.repository.CustomerRepository;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// activate automatic startup and stop of containers
@Testcontainers
// JPA drop and create table, good for testing
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
public class CustomerControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    CustomerRepository customerRepository;

    // static, all tests share this postgres container
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    @BeforeEach
    void setUp() {
    	System.out.println(port);
        RestAssured.baseURI = "http://localhost:" + port;
        customerRepository.deleteAll();

        Customer b1 = new Customer("Customer A", 10, LocalDate.of(2023, 8, 31));
        Customer b2 = new Customer("Customer B", 20, LocalDate.of(2023, 7, 31));
        Customer b3 = new Customer("Customer C", 30, LocalDate.of(2023, 6, 10));
        Customer b4 = new Customer("Customer D", 40, LocalDate.of(2023, 5, 5));

        customerRepository.saveAll(List.of(b1, b2, b3, b4));
    }

    @Test
    void testFindAll() {

        given()
                .contentType(ContentType.JSON)
                .when()
                    .get("/customers")
                .then()
                    .statusCode(200)    // expecting HTTP 200 OK
                    .contentType(ContentType.JSON) // expecting JSON response content
                    .body(".", hasSize(4));

    }

    @Test
    void testFindByTitle() {

        String title = "Customer C";

        given()
                //Returning floats and doubles as BigDecimal
                .config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL)))
                .contentType(ContentType.JSON)
                .pathParam("title", title)
                .when()
                    .get("/customers/find/title/{title}")
                .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body(
                        ".", hasSize(1),
                        "[0].title", equalTo("Customer C"),
                        "[0].price", is(new BigDecimal("29.99")),
                        "[0].publishDate", equalTo("2023-06-10")
                );
    }

    @Test
    void testFindByPublishedDateAfter() {

        String date = "2023-07-01";

        Response result = given()
                //Returning floats and doubles as BigDecimal
                .config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL)))
                .contentType(ContentType.JSON)
                .pathParam("date", date)
                .when()
                    .get("/customers/find/date-after/{date}")
                .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body(
                        ".", hasSize(2),
                        "title", hasItems("Customer A", "Customer B"),
                        "price", hasItems(new BigDecimal("9.99"), new BigDecimal("19.99")),
                        "publishDate", hasItems("2023-08-31", "2023-07-31")
                    )
                .extract().response();

        // get the response and print it out
        System.out.println(result.asString());

    }


    @Test
    public void testDeleteById() {
        Long id = 1L; // replace with a valid ID
        given()
                .pathParam("id", id)
                .when()
                    .delete("/customers/{id}")
                .then()
                    .statusCode(204); // expecting HTTP 204 No Content
    }

    @Test
    public void testCreate() {

        given()
                .contentType(ContentType.JSON)
                .body("{ \"title\": \"Customer E\", \"price\": \"9.99\", \"publishDate\": \"2023-09-14\" }")
                .when()
                    .post("/customers")
                .then()
                    .statusCode(201) // expecting HTTP 201 Created
                    .contentType(ContentType.JSON); // expecting JSON response content

        // find the new saved Customer
        given()
                //Returning floats and doubles as BigDecimal
                .config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL)))
                .contentType(ContentType.JSON)
                .pathParam("title", "Customer E")
                .when()
                    .get("/customers/find/title/{title}")
                .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body(
                        ".", hasSize(1),
                        "[0].title", equalTo("Customer E"),
                        "[0].price", is(new BigDecimal("9.99")),
                        "[0].publishDate", equalTo("2023-09-14")
                    );
    }

    /**
     * Customer b4 = new Customer("Customer D",
     * BigDecimal.valueOf(39.99),
     * LocalDate.of(2023, 5, 5));
     */
    @Test
    public void testUpdate() {

    	Customer customerD = customerRepository.findByName("Customer D").get(0);
        System.out.println(customerD);

        Long id = customerD.getId();

        customerD.setName("Customer E");
        customerD.setOrderAmount(199);
        customerD.setOrderDate(LocalDate.of(2024, 1, 31));

        given()
                .contentType(ContentType.JSON)
                .body(customerD)
                .when()
                    .put("/customers")
                .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON);

        // get the updated Customer
        Customer updatedCustomer = customerRepository.findById(id).orElseThrow();
        System.out.println(updatedCustomer);

        assertEquals(id, updatedCustomer.getId());
        assertEquals("Customer E", updatedCustomer.getName());
        assertEquals(new BigDecimal("199.99"), updatedCustomer.getOrderAmount());
        assertEquals(LocalDate.of(2024, 1, 31), updatedCustomer.getOrderDate());
        
    }


}