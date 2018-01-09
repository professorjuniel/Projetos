
package zimbramail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de getMsgMetadataRequest complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="getMsgMetadataRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="m" type="{urn:zimbraMail}idsAttr"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMsgMetadataRequest", propOrder = {
    "m"
})
public class GetMsgMetadataRequest {

    @XmlElement(required = true)
    protected IdsAttr m;

    /**
     * Obtém o valor da propriedade m.
     * 
     * @return
     *     possible object is
     *     {@link IdsAttr }
     *     
     */
    public IdsAttr getM() {
        return m;
    }

    /**
     * Define o valor da propriedade m.
     * 
     * @param value
     *     allowed object is
     *     {@link IdsAttr }
     *     
     */
    public void setM(IdsAttr value) {
        this.m = value;
    }

}
