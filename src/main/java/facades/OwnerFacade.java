package facades;

import entities.Dog;
import entities.Owner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.*;

public class OwnerFacade {

    private static OwnerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private OwnerFacade() {
    }


    /**
     * @param _emf
     * @return an instance of this facade class.
     */

    public static OwnerFacade getOwnerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OwnerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public long getOwnerCount(){
        EntityManager em = getEntityManager();
        try{
            long ownerCount = (long)em.createQuery("SELECT COUNT(o) FROM Owner o").getSingleResult();
            return ownerCount;
        }finally{
            em.close();
        }
    }

    public Owner getById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Owner owner = em.find(Owner.class, id);
            System.out.println(owner);
            owner.setDogs(null);
            System.out.println("Owner in OwnerFacade: " + owner);
            return owner;
        } finally {
            em.close();
        }
    }




    public List<Dog> getAllDogsFromOwnerId(long ownerId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Dog> query = em.createQuery("SELECT d FROM Dog d JOIN d.owner o WHERE o.id = :ownerId", Dog.class);
        query.setParameter("ownerId", ownerId);

        List<Dog> dogs = query.getResultList();
        for (int i = 0; i < dogs.size(); i++) {
            dogs.get(i).setOwner(null);
            dogs.get(i).setWalkers(null);
        }

        return dogs;
    }

    public static void main(String[] args) {
    }

}
