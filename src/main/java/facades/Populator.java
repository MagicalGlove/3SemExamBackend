/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RenameMeDTO;
import entities.RenameMe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import entities.Role;
import entities.User;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = FacadeExample.getFacadeExample(emf);
//        fe.create(new RenameMeDTO(new RenameMe("First 1", "Last 1")));
//        fe.create(new RenameMeDTO(new RenameMe("First 2", "Last 2")));
//        fe.create(new RenameMeDTO(new RenameMe("First 3", "Last 3")));

        Role role = new Role("user");
        User u1 = new User("UserTime", "123");

        u1.addRole(role);

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            em.persist(role);

            em.persist(u1);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
    
    public static void main(String[] args) {
        populate();
    }
}
