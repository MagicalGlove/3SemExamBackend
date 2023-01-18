/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RenameMeDTO;
import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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



        List<Dog> dogs = new ArrayList<>();

        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        User u1 = new User("UserTime", "123");
        User a1 = new User("AdminTime", "123");

        u1.addRole(userRole);
        a1.addRole(adminRole);

        List<Dog> wDogs1 = new ArrayList<>();
        List<Dog> wDogs2 = new ArrayList<>();
        List<Dog> wDogs3 = new ArrayList<>();
        List<Dog> wDogs4 = new ArrayList<>();
        List<Dog> wDogs5 = new ArrayList<>();

        List<Dog> oDogs1 = new ArrayList<>();
        List<Dog> oDogs2 = new ArrayList<>();
        List<Dog> oDogs3 = new ArrayList<>();
        List<Dog> oDogs4 = new ArrayList<>();
        List<Dog> oDogs5 = new ArrayList<>();

        List<Walker> walkers1 = new ArrayList<>();
        List<Walker> walkers2 = new ArrayList<>();
        List<Walker> walkers3 = new ArrayList<>();
        List<Walker> walkers4 = new ArrayList<>();
        List<Walker> walkers5 = new ArrayList<>();

        Walker w1 = new Walker("John Smith", "123 Main St", "70548963", wDogs1);
        Walker w2 = new Walker("Jane Doe", "456 Park Ave", "64917253", wDogs2);
        Walker w3 = new Walker("Bob Johnson", "789 Elm St", "12357894", wDogs3);
        Walker w4 = new Walker("Emily Davis", "321 Oak St", "98765432", wDogs4);
        Walker w5 = new Walker("Michael Brown", "654 Pine St", "73845928", wDogs5);

        Dog d1 = new Dog("Max", "Golden Retriever", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg?crop=0.752xw:1.00xh;0.175xw,0&resize=1200:*", "Male", "01/01/2010", walkers1);
        Dog d2 = new Dog("Bella", "Labrador Retriever", "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_4x3.jpg", "Female", "02/14/2012", walkers2);
        Dog d3 = new Dog("Charlie", "Poodle", "https://cdn-prod.medicalnewstoday.com/content/images/articles/322/322868/golden-retriever-puppy.jpg", "Male", "03/01/2015", walkers3);
        Dog d4 = new Dog("Lucy", "Beagle", "https://dk.bellfor.info/image/catalog/Blog/blog_pictures_multilang/beagledog.jpg", "Female", "04/20/2018", walkers4);
        Dog d5 = new Dog("Rocky", "German Shepherd", "https://www.akc.org/wp-content/uploads/2016/06/German-Shepherd-Dog-laying-down-in-the-backyard-500x487.jpeg", "Male", "05/10/2020", walkers5);

        Owner o1 = new Owner("John Smith", "123 Main St", "32154879", oDogs1);
        Owner o2 = new Owner("Jane Doe", "456 Park Ave", "45678912", oDogs2);
        Owner o3 = new Owner("Bob Johnson", "789 Elm St", "78945612", oDogs3);
        Owner o4 = new Owner("Emily Davis", "321 Oak St", "14785236", oDogs4);
        Owner o5 = new Owner("Michael Brown", "654 Pine St", "96325874", oDogs5);

        w1.getDogs().add(d1);
        w2.getDogs().add(d1);

        w3.getDogs().add(d2);
        w3.getDogs().add(d3);
        w4.getDogs().add(d4);
        w5.getDogs().add(d5);

        d1.setOwner(o1);
        d2.setOwner(o2);
        d3.setOwner(o3);
        d4.setOwner(o4);
        d5.setOwner(o5);



        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            em.persist(userRole);
            em.persist(adminRole);

            em.persist(u1);
            em.persist(a1);


            em.persist(w1);
            em.persist(w2);
            em.persist(w3);
            em.persist(w4);
            em.persist(w5);

            em.persist(d1);
            em.persist(d2);
            em.persist(d3);
            em.persist(d4);
            em.persist(d5);

            em.persist(o1);
            em.persist(o2);
            em.persist(o3);
            em.persist(o4);
            em.persist(o5);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
    
    public static void main(String[] args) {
        populate();
    }
}
