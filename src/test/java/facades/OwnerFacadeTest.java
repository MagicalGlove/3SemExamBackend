package facades;

import entities.Dog;
import entities.Owner;
import entities.Walker;
import utils.EMF_Creator;
import entities.RenameMe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class OwnerFacadeTest {

    private static EntityManagerFactory emf;
    private static OwnerFacade facade;

    Owner o1, o2, o3, o4, o5;
    Dog d1, d2, d3, d4, d5;
    Walker w1, w2, w3, w4, w5;


    public OwnerFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = OwnerFacade.getOwnerFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();


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

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void getAmountOfOwners() throws Exception {
        assertEquals(2, facade.getOwnerCount(), "Expects two owners in the database");
    }

    @Test
    public void getAllOwners(){
        System.out.println("Testing get all owners");
        List<Owner> allOwners = facade.getAllOwners();
        assert(allOwners.contains(o1) && allOwners.contains(o2));
    }


}
