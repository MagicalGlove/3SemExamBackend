package facades;

import dtos.OwnerDto;
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

    public List<Owner> getAllOwners() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Owner> query = em.createQuery("SELECT o FROM Owner o", Owner.class);
        List<Owner> owners = query.getResultList();

        return owners;
    }


    public static void main(String[] args) {
    }


}
