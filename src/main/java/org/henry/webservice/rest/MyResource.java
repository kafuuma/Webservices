package org.henry.webservice.rest;

import org.henry.webservice.models.SalutationRequest;
import org.henry.webservice.models.SalutationResponse;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("UnresolvedRestParam")
//@Singleton
@Path("myresource")
public class MyResource {

    //class level params
//    @PathParam("guest")
//    String guestName;
//
//    @DefaultValue("Anonymous")
//    @QueryParam("guestFromQuery")
//    String queryParamGuest;

    private final List<String> firstNames = new ArrayList<>();
    private final Map<String, String> firstNameLastName = new HashMap<>();

    @PostConstruct
    public void  initialize(){
        firstNames.add("Tayo");
        firstNames.add("Vaibhav");
        firstNames.add("Dillon");
        firstNames.add("Erika");
        firstNames.add("Manmayee");
        firstNames.add("Adan");
        firstNames.add("Michael");
        firstNameLastName.put("Tayo","Koleoso");
        firstNameLastName.put("Vaibhav","Goodman");
        firstNameLastName.put("Dillon","Yousob");
        firstNameLastName.put("Erika","Hills");
        firstNameLastName.put("Manmayee","Brightman");
        firstNameLastName.put("Adan","Mortez");
        firstNameLastName.put("Michael","Menace");
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@Context UriInfo urlInformation) {
        return "Got it!" + urlInformation.getAbsolutePath();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/firstnames")
    public List<String> getFirstNames() {
        return firstNames;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/guest/{firstname}/lastname")
    public String getLastNames(@PathParam("firstname")String firstName) {
        return firstNameLastName.get(firstName);
    }

    @Path("/guest/{guest:[a-zA-Z][a-zA-Z_0-9]*}/goodbye")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response goodbye(SalutationRequest request, @PathParam("guest") String guest, @QueryParam("makeItWait")boolean makeItWait) {
        SalutationResponse response = new SalutationResponse();
        if(makeItWait) {
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return Response.serverError().entity("Something bad happened "+ e.getMessage()).build();
            }
        }
        response.setSalutationResponse("Goodbye, "+request.getSalutation()+" "+guest);
        Response responseWrapper = Response.ok(response).build();
        return responseWrapper;
    }

    @POST
    @Path("/guest/{guest:[a-zA-Z][a-zA-Z_0-9]*}/salute")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salute(SalutationRequest request , @PathParam("guest") String guest, @QueryParam("makeItWait")boolean makeItWait){
        SalutationResponse response= new SalutationResponse();
        if(makeItWait){
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        response.setSalutationResponse("Hello, " + request.getSalutation() + " " + guest);
        return Response.ok(response).build();
    }

    @POST
    @Path("/guest/salute")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response saluteWithForm(@NotBlank @FormParam("salutation") String salutation  , @FormParam("guest") String guest){
        SalutationResponse response= new SalutationResponse();

        response.setSalutationResponse("Hello, " +  salutation + " " + guest);

        return Response.ok(response).build();
    }
}
