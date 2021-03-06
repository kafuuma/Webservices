
package org.henry.webservice.soap.stubs;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.0-M3
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MySOAPEndpoint", targetNamespace = "http://soap.webservice.henry.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MySOAPEndpoint {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns org.henry.webservice.soap.stubs.SalutationResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "salute", targetNamespace = "http://soap.webservice.henry.org/", className = "org.henry.webservice.soap.stubs.Salute")
    @ResponseWrapper(localName = "saluteResponse", targetNamespace = "http://soap.webservice.henry.org/", className = "org.henry.webservice.soap.stubs.SaluteResponse")
    @Action(input = "http://soap.webservice.henry.org/MySOAPEndpoint/saluteRequest", output = "http://soap.webservice.henry.org/MySOAPEndpoint/saluteResponse")
    public SalutationResponse salute(
        @WebParam(name = "arg0", targetNamespace = "")
        SalutationRequest arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getIt", targetNamespace = "http://soap.webservice.henry.org/", className = "org.henry.webservice.soap.stubs.GetIt")
    @ResponseWrapper(localName = "getItResponse", targetNamespace = "http://soap.webservice.henry.org/", className = "org.henry.webservice.soap.stubs.GetItResponse")
    @Action(input = "http://soap.webservice.henry.org/MySOAPEndpoint/getItRequest", output = "http://soap.webservice.henry.org/MySOAPEndpoint/getItResponse")
    public String getIt();

}
