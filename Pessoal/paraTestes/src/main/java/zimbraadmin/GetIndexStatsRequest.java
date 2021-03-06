
package zimbraadmin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de getIndexStatsRequest complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="getIndexStatsRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mbox" type="{urn:zimbraAdmin}mailboxByAccountIdSelector"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getIndexStatsRequest", propOrder = {
    "mbox"
})
public class GetIndexStatsRequest {

    @XmlElement(required = true)
    protected MailboxByAccountIdSelector mbox;

    /**
     * Obtém o valor da propriedade mbox.
     * 
     * @return
     *     possible object is
     *     {@link MailboxByAccountIdSelector }
     *     
     */
    public MailboxByAccountIdSelector getMbox() {
        return mbox;
    }

    /**
     * Define o valor da propriedade mbox.
     * 
     * @param value
     *     allowed object is
     *     {@link MailboxByAccountIdSelector }
     *     
     */
    public void setMbox(MailboxByAccountIdSelector value) {
        this.mbox = value;
    }

}
