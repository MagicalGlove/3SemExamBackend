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

    public long getDogCount() {
        EntityManager em = getEntityManager();
        try {
            long dogCount = (long) em.createQuery("SELECT COUNT(d) FROM Dog d").getSingleResult();
            return dogCount;
        } finally {
            em.close();
        }
    }

    public List<Dog> getAllDogs() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Dog> query = em.createQuery("SELECT d FROM Dog d", Dog.class);

        List<Dog> dogs = query.getResultList();
        for (int i = 0; i < dogs.size(); i++) {
            dogs.get(i).setOwner(null);
            dogs.get(i).setWalkers(null);
        }

        return dogs;
    }

    public Dog createDog(Dog dog) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(dog);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return dog;
    }

    public Dog update(Dog dog) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(dog);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return dog;
    }

    public Dog getById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Dog dog = em.find(Dog.class, id);
            dog.setOwner(null);
            dog.setWalkers(null);
            return dog;
        } finally {
            em.close();
        }
    }


    public Dog deleteDog(long id) {
        EntityManager em = emf.createEntityManager();

        Dog dog = em.find(Dog.class, id);

        try {
            em.getTransaction().begin();
            em.remove(dog);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return dog;
    }

    public static void main(String[] args) {
    }


}
