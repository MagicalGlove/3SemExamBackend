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

    public long getWalkerCount() {
        EntityManager em = getEntityManager();
        try {
            long walkerCounter = (long) em.createQuery("SELECT COUNT(w) FROM Walker w").getSingleResult();
            return walkerCounter;
        } finally {
            em.close();
        }
    }

    public List<Walker> getAllWalkersFromDogId(long dogId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Walker> query = em.createQuery("SELECT w FROM Walker w JOIN w.dogs d WHERE d.id = :dogId", Walker.class);
        query.setParameter("dogId", dogId);

        List<Walker> walkers = query.getResultList();
        for (int i = 0; i < walkers.size(); i++) {
            walkers.get(i).setDogs(null);
        }

        return walkers;
    }

    public ArrayList<Walker> getAllWalkers() {
        EntityManager em = emf.createEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Walker> cq = cb.createQuery(Walker.class);
        Root<Walker> root = cq.from(Walker.class);
        cq.multiselect(root.get("id"), root.get("name"), root.get("address"), root.get("phone"));
        TypedQuery<Walker> query = em.createQuery(cq);
        List<Walker> walkerList = query.getResultList();

        ArrayList<Walker> walkerArrayList = new ArrayList<>(walkerList);

        return walkerArrayList;
    }


    public static void main(String[] args) {
    }


}
