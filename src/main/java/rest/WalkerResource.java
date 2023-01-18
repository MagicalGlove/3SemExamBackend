package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Owner;
import entities.Walker;
import facades.OwnerFacade;
import facades.WalkerFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("walker")
public class WalkerResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final WalkerFacade FACADE = WalkerFacade.getWalkerFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }


    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllWalkers() {
        List<Walker> walkers = FACADE.getAllWalkers();
        System.out.println("Im here now");
        System.out.println("indeed");
        System.out.println("walkers: " + walkers);
        String json = GSON.toJson(walkers);
        System.out.println("now Im here");
        System.out.println("json: " + json);
        return Response.ok().entity(json).build();
    }

}