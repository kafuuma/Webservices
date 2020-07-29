package org.henry.webservice.rest.client;

import org.henry.webservice.models.SalutationRequest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

public class RestSyncClient {

    public static void main(String[] args) {
        syncClientCall();
    }


    private static void syncClientCall(){
        Client client  = ClientBuilder.newClient();
        SalutationRequest request = new SalutationRequest();
        request.setSalutation("His Royal Codeness");

        Entity<SalutationRequest> entity = Entity.entity(request, MediaType.APPLICATION_JSON);
        String targetResource = "http://localhost:8080/java-ee-webservices/webapi/myresource/guest/{guest}/salute";
        Response response = client.target(targetResource)
                .resolveTemplate("guest", "kafuuma")
                .request(MediaType.APPLICATION_JSON)
                .post(entity);

        Logger.getAnonymousLogger().info("Request from server:  "+ response.readEntity(String.class));
    }
}
