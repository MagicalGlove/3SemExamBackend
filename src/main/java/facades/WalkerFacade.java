package facades;

import dtos.OwnerDto;
import entities.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.*;

public class WalkerFacade {

    private static WalkerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private WalkerFacade() {
    }


    /**
     * @param _emf
     * @return an instance of this facade class.
     */

    public static WalkerFacade getWalkerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new WalkerFacade();
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


    public static void main(String[] args) {
    }


}
