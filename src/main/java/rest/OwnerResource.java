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
        if (ownerId == 0) {
            List<Dog> allDogs = FACADE.getAllDogs();
            String json = GSON.toJson(allDogs);
            return Response.ok().entity(json).build();
        }

        List<Dog> dogs = FACADE.getAllDogsFromOwnerId(ownerId);

        String json = GSON.toJson(dogs);
        return Response.ok().entity(json).build();
    }

}