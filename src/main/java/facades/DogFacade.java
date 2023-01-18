package facades;

import dtos.OwnerDto;
import entities.Dog;
import entities.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.*;

public class DogFacade {

    private static DogFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private DogFacade() {
    }


    /**
     * @param _emf
     * @return an instance of this facade class.
     */

    public static DogFacade getDogFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DogFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Owner> getAllOwners() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Owner> query = em.createQuery("SELECT o FROM Owner o", Owner.class);
        List<Owner> owners = query.getResultList();

        return owners;
    }

    public Dog createDog(Dog dog){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(dog);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return dog;
    }

    public Dog update(Dog dog){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(dog);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return dog;
    }


    public static void main(String[] args) {
    }


}
