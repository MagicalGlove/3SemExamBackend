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
        List<Dog> wDogs6 = new ArrayList<>();

        List<Dog> oDogs1 = new ArrayList<>();
        List<Dog> oDogs2 = new ArrayList<>();
        List<Dog> oDogs3 = new ArrayList<>();
        List<Dog> oDogs4 = new ArrayList<>();
        List<Dog> oDogs5 = new ArrayList<>();
        List<Dog> oDogs6 = new ArrayList<>();

        List<Walker> walkers1 = new ArrayList<>();
        List<Walker> walkers2 = new ArrayList<>();
        List<Walker> walkers3 = new ArrayList<>();
        List<Walker> walkers4 = new ArrayList<>();
        List<Walker> walkers5 = new ArrayList<>();
        List<Walker> walkers6 = new ArrayList<>();

        Walker w1 = new Walker("John Smith", "123 Main St", "70548963", wDogs2);
        Walker w2 = new Walker("Jane Doe", "456 Park Ave", "64917253", wDogs3);
        Walker w3 = new Walker("Bob Johnson", "789 Elm St", "12357894", wDogs1);
        Walker w4 = new Walker("Emily Davis", "321 Oak St", "98765432", wDogs4);
        Walker w5 = new Walker("Michael Brown", "654 Pine St", "555-555-5559", wDogs2);

        Dog d1 = new Dog("Max", "Golden Retriever", "image.jpg", "Male", "01/01/2010", walkers1);
        Dog d2 = new Dog("Bella", "Labrador Retriever", "image2.jpg", "Female", "02/14/2012", walkers2);
        Dog d3 = new Dog("Charlie", "Poodle", "image3.jpg", "Male", "03/01/2015", walkers3);
        Dog d4 = new Dog("Lucy", "Beagle", "image4.jpg", "Female", "04/20/2018", walkers1);
        Dog d5 = new Dog("Rocky", "German Shepherd", "image5.jpg", "Male", "05/10/2020", walkers2);

        Owner o1 = new Owner("John Smith", "123 Main St", "32154879", oDogs1);
        Owner o2 = new Owner("Jane Doe", "456 Park Ave", "45678912", oDogs2);
        Owner o3 = new Owner("Bob Johnson", "789 Elm St", "78945612", oDogs3);
        Owner o4 = new Owner("Emily Davis", "321 Oak St", "14785236", oDogs4);
        Owner o5 = new Owner("Michael Brown", "654 Pine St", "96325874", oDogs1);

        u1.getBoats().add(b1);
        u2.getBoats().add(b1);

        u3.getBoats().add(b2);
        u3.getBoats().add(b3);
        u4.getBoats().add(b4);
        u5.getBoats().add(b5);
        u6.getBoats().add(b6);

        Harbor h1 = new Harbor("VandHavn", "WaterWay", 500, H1boats);
        Harbor h2 = new Harbor("Marina del Rey", "13500 Fiji Way", 500, H2boats);
        Harbor h3 = new Harbor("Newport Beach", "201 Irvine Ave", 1000, H3boats);
        Harbor h4 = new Harbor("Long Beach", "211 Marina Dr", 800, H4boats);
        Harbor h5 = new Harbor("Redondo Beach", "123 N Harbor Dr", 700, H5boats);
        Harbor h6 = new Harbor("Dana Point", "24200 Dana Point Harbor Dr", 650, H6boats);

        b1.addHarbor(h1);
        b2.addHarbor(h2);
        b3.addHarbor(h3);
        b4.addHarbor(h4);
        b5.addHarbor(h3);
        b6.addHarbor(h2);


        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            em.persist(userRole);
            em.persist(adminRole);

            em.persist(u1);
            em.persist(a1);


            em.persist(u1);
            em.persist(u2);
            em.persist(u3);
            em.persist(u4);
            em.persist(u5);
            em.persist(u6);

            em.persist(b1);
            em.persist(b2);
            em.persist(b3);
            em.persist(b4);
            em.persist(b5);
            em.persist(b6);

            em.persist(h1);
            em.persist(h2);
            em.persist(h3);
            em.persist(h4);
            em.persist(h5);
            em.persist(h6);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
    
    public static void main(String[] args) {
        populate();
    }
}
