package org.henry.webservice.soap;

import org.henry.webservice.models.SalutationRequest;
import org.henry.webservice.models.SalutationResponse;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
public class MySOAPEndpoint {


    @WebMethod
    public String getIt() {
        return "Got it!";
    }

    @WebMethod
    public SalutationResponse salute(SalutationRequest request, String guest) {
        SalutationResponse response = new SalutationResponse();
        response.setSalutationResponse("Hello, " + request.getSalutation() + " " + guest);
        return response;
    }

}




