package org.henry.webservice.soap;

import org.henry.webservice.soap.stubs.MySOAPEndpointService;
import org.henry.webservice.soap.stubs.MySOAPEndpoint;
import org.henry.webservice.soap.stubs.SalutationRequest;
import org.henry.webservice.soap.stubs.SalutationResponse;

import java.util.logging.Logger;



public class SOAPServiceClient {

    public static void main(String[] args) {
        MySOAPEndpointService serviceClient = new MySOAPEndpointService();
        MySOAPEndpoint port = serviceClient.getMySOAPEndpointPort();

        SalutationRequest  request = new SalutationRequest();

        request.setSalutation("My lord");

        SalutationResponse response = port.salute(request, "Henry");

        Logger.getAnonymousLogger().info("The response is "+  response.getSalutationResponse());
    }

}
