package org.henry.webservice.rest;

import org.henry.webservice.models.SalutationRequest;
import org.henry.webservice.models.SalutationResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @POST
    @Path("/guest/{guest}/salute")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salute(SalutationRequest request , @PathParam("guest") String guest){
        SalutationResponse response= new SalutationResponse();
        response.setSalutationResponse("Hello, " + request.getSalutation() + " " + guest);
        return Response.ok(response).build();
    }
}
