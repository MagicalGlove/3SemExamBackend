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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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



        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        User u1 = new User("UserTime", "123");
        User a1 = new User("AdminTime", "123");

        u1.addRole(userRole);
        a1.addRole(adminRole);

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            em.persist(userRole);
            em.persist(adminRole);

            em.persist(u1);
            em.persist(a1);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
    
    public static void main(String[] args) {
        populate();
    }
}
