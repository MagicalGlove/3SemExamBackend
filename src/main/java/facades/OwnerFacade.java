package facades;

import dtos.OwnerDto;
import entities.Dog;
import entities.Owner;
import entities.Walker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    public List<Owner> getAllOwners() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Owner> query = em.createQuery("SELECT o FROM Owner o INNER JOIN FETCH o.dogs", Owner.class);

        List<Owner> owners = query.getResultList();
        System.out.println("Owners OWOWOWO: " + owners);

        return owners;
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
