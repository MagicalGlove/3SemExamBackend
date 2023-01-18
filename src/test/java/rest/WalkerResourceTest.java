package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Dog;
import entities.Owner;
import entities.Walker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class WalkerResourceTest {
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    private Owner o1, o2, o3, o4, o5;
    private Dog d1, d2, d3, d4, d5;
    private Walker w1, w2, w3, w4, w5;


    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {

        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {

        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();


//        em.createQuery("delete FROM hobbies_people").executeUpdate();
//        em.create


            em.createNamedQuery("Walker.deleteAllRows").executeUpdate();
            em.createNamedQuery("Dog.deleteAllRows").executeUpdate();
            em.createNamedQuery("Owner.deleteAllRows").executeUpdate();

            List<Dog> wDogs1 = new ArrayList<>();
            List<Dog> wDogs2 = new ArrayList<>();

            List<Dog> oDogs1 = new ArrayList<>();
            List<Dog> oDogs2 = new ArrayList<>();

            List<Walker> walkers1 = new ArrayList<>();
            List<Walker> walkers2 = new ArrayList<>();


            w1 = new Walker("John Smith", "123 Main St", "70548963", wDogs1);
            w2 = new Walker("Jane Doe", "456 Park Ave", "64917253", wDogs2);


            d1 = new Dog("Max", "Golden Retriever", "image.jpg", "Male", "01/01/2010", walkers1);
            d2 = new Dog("Bella", "Labrador Retriever", "image2.jpg", "Female", "02/14/2012", walkers2);


            o1 = new Owner("John Smith", "123 Main St", "32154879", oDogs1);
            o2 = new Owner("Jane Doe", "456 Park Ave", "45678912", oDogs2);

            w1.getDogs().add(d1);
            w2.getDogs().add(d2);

            d1.setOwner(o1);
            d2.setOwner(o2);

//            o1.getDogs().add(d1);
//            o2.getDogs().add(d2);

            em.persist(w1);
            em.persist(w2);

            em.persist(d1);
            em.persist(d2);

            em.persist(o1);
            em.persist(o2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("owner").then().statusCode(200);
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testGetAllWalkers() {
        List<Walker> walkers;

        walkers = given()
                .contentType("application/json")
                .when()
                .get("/walker/all")
                .then()
                .extract().body().jsonPath().getList("", Walker.class);
        List<Walker> walkersActual = new ArrayList<>();

        assertThat(walkersActual, containsInAnyOrder(w1));
    }

}
