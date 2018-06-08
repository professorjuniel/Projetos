
package br.com.facilpagar.webservices.bancoDoBrasil.homologacao.com.example.xmlns._1466775928160;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import br.com.facilpagar.webservices.bancoDoBrasil.homologacao.com.tibco.schemas.bws_registro_cbr.recursos.xsd.schema.ObjectFactory;
import br.com.facilpagar.webservices.bancoDoBrasil.homologacao.com.tibco.schemas.bws_registro_cbr.recursos.xsd.schema.Requisicao;
import br.com.facilpagar.webservices.bancoDoBrasil.homologacao.com.tibco.schemas.bws_registro_cbr.recursos.xsd.schema.Resposta;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "portType", targetNamespace = "http://xmlns.example.com/1466775928160")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PortType {


    /**
     * 
     * @param parameters
     * @return
     *     returns com.tibco.schemas.bws_registro_cbr.recursos.xsd.schema.Resposta
     * @throws Erro
     */
    @WebMethod(operationName = "RegistroTituloCobranca", action = "registrarBoleto")
    @WebResult(name = "resposta", targetNamespace = "http://www.tibco.com/schemas/bws_registro_cbr/Recursos/XSD/Schema.xsd", partName = "parameters")
    public Resposta registroTituloCobranca(
        @WebParam(name = "requisicao", targetNamespace = "http://www.tibco.com/schemas/bws_registro_cbr/Recursos/XSD/Schema.xsd", partName = "parameters")
        Requisicao parameters)
        throws Erro
    ;

}
