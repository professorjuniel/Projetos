
package carnepagcontas.com.example.xmlns._1466775928160;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "erro", targetNamespace = "http://www.tibco.com/schemas/bws_registro_cbr/Recursos/XSD/Schema.xsd")
public class Erro
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private carnepagcontas.com.tibco.schemas.bws_registro_cbr.recursos.xsd.schema.Erro faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public Erro(String message, carnepagcontas.com.tibco.schemas.bws_registro_cbr.recursos.xsd.schema.Erro faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public Erro(String message, carnepagcontas.com.tibco.schemas.bws_registro_cbr.recursos.xsd.schema.Erro faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.tibco.schemas.bws_registro_cbr.recursos.xsd.schema.Erro
     */
    public carnepagcontas.com.tibco.schemas.bws_registro_cbr.recursos.xsd.schema.Erro getFaultInfo() {
        return faultInfo;
    }

}
