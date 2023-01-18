package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Dog;
import entities.Owner;
import facades.OwnerFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("owner")
public class OwnerResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final OwnerFacade FACADE = OwnerFacade.getOwnerFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }


    @Path("dogs/{ownerId}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllDogsFromOwnerId(@PathParam("ownerId") long ownerId) throws EntityNotFoundException {
        List<Dog> dogs = FACADE.getAllDogsFromOwnerId(ownerId);
        String json = GSON.toJson(dogs);
        return Response.ok().entity(json).build();

//        List<Dog> doggos = new ArrayList<>();
//        Dog woof = new Dog(1L, "Woofer", "ShadowRace", "cool.jpg", "male", "02/10/2017");
//        doggos.add(woof);
//        System.out.println("doggos: " + doggos);
//        String json = GSON.toJson(woof);
//        System.out.println("json: " + json);
//        return Response.ok().entity(json).build();
    }

}