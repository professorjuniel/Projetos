
package br.gov.pi.ati.webservice.process;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "server.webservicesPortType", targetNamespace = "urn:server.webservices")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ServerWebservicesPortType {


    /**
     * 
     * @param processo
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "processo2xml", action = "urn:server.processo2xml#processo2xml")
    @WebResult(partName = "return")
    public String processo2Xml(
        @WebParam(name = "processo", partName = "processo")
        String processo);

}
