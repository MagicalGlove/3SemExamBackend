package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Dog;
import facades.DogFacade;
import facades.WalkerFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("dog")
public class DogResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final DogFacade FACADE = DogFacade.getDogFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @PUT
    @Path("updateDog/{id}")
    @RolesAllowed("admin")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") Long id, String content) throws EntityNotFoundException {
        Dog dog = GSON.fromJson(content, Dog.class);
        dog.setId(id);
        Dog updatedDog = FACADE.update(dog);
        return Response.ok().entity(GSON.toJson(updatedDog)).build();
    }

}


