
package zimbraadmin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de serverMailQueueDetails complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="serverMailQueueDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="queue" type="{urn:zimbraAdmin}mailQueueDetails"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serverMailQueueDetails", propOrder = {
    "queue"
})
public class ServerMailQueueDetails {

    @XmlElement(required = true)
    protected MailQueueDetails queue;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * Obtém o valor da propriedade queue.
     * 
     * @return
     *     possible object is
     *     {@link MailQueueDetails }
     *     
     */
    public MailQueueDetails getQueue() {
        return queue;
    }

    /**
     * Define o valor da propriedade queue.
     * 
     * @param value
     *     allowed object is
     *     {@link MailQueueDetails }
     *     
     */
    public void setQueue(MailQueueDetails value) {
        this.queue = value;
    }

    /**
     * Obtém o valor da propriedade name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Define o valor da propriedade name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
