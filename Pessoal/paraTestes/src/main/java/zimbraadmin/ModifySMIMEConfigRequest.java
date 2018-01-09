
package zimbraadmin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de modifySMIMEConfigRequest complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="modifySMIMEConfigRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="config" type="{urn:zimbraAdmin}smimeConfigModifications"/>
 *         &lt;element name="domain" type="{urn:zimbraAdmin}domainSelector" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifySMIMEConfigRequest", propOrder = {
    "config",
    "domain"
})
public class ModifySMIMEConfigRequest {

    @XmlElement(required = true)
    protected SmimeConfigModifications config;
    protected DomainSelector domain;

    /**
     * Obtém o valor da propriedade config.
     * 
     * @return
     *     possible object is
     *     {@link SmimeConfigModifications }
     *     
     */
    public SmimeConfigModifications getConfig() {
        return config;
    }

    /**
     * Define o valor da propriedade config.
     * 
     * @param value
     *     allowed object is
     *     {@link SmimeConfigModifications }
     *     
     */
    public void setConfig(SmimeConfigModifications value) {
        this.config = value;
    }

    /**
     * Obtém o valor da propriedade domain.
     * 
     * @return
     *     possible object is
     *     {@link DomainSelector }
     *     
     */
    public DomainSelector getDomain() {
        return domain;
    }

    /**
     * Define o valor da propriedade domain.
     * 
     * @param value
     *     allowed object is
     *     {@link DomainSelector }
     *     
     */
    public void setDomain(DomainSelector value) {
        this.domain = value;
    }

}
