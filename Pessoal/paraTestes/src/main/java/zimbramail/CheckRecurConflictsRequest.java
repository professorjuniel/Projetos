
package zimbramail;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de checkRecurConflictsRequest complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="checkRecurConflictsRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tz" type="{urn:zimbraMail}calTZInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="cancel" type="{urn:zimbraMail}expandedRecurrenceCancel"/>
 *           &lt;element name="comp" type="{urn:zimbraMail}expandedRecurrenceInvite"/>
 *           &lt;element name="except" type="{urn:zimbraMail}expandedRecurrenceException"/>
 *         &lt;/choice>
 *         &lt;element name="usr" type="{urn:zimbraMail}freeBusyUserSpec" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="s" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="e" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="all" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="excludeUid" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkRecurConflictsRequest", propOrder = {
    "tz",
    "cancelOrCompOrExcept",
    "usr"
})
public class CheckRecurConflictsRequest {

    protected List<CalTZInfo> tz;
    @XmlElements({
        @XmlElement(name = "cancel", type = ExpandedRecurrenceCancel.class),
        @XmlElement(name = "comp", type = ExpandedRecurrenceInvite.class),
        @XmlElement(name = "except", type = ExpandedRecurrenceException.class)
    })
    protected List<ExpandedRecurrenceComponent> cancelOrCompOrExcept;
    protected List<FreeBusyUserSpec> usr;
    @XmlAttribute(name = "s")
    protected Long s;
    @XmlAttribute(name = "e")
    protected Long e;
    @XmlAttribute(name = "all")
    protected Boolean all;
    @XmlAttribute(name = "excludeUid")
    protected String excludeUid;

    /**
     * Gets the value of the tz property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tz property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTz().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CalTZInfo }
     * 
     * 
     */
    public List<CalTZInfo> getTz() {
        if (tz == null) {
            tz = new ArrayList<CalTZInfo>();
        }
        return this.tz;
    }

    /**
     * Gets the value of the cancelOrCompOrExcept property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cancelOrCompOrExcept property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCancelOrCompOrExcept().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExpandedRecurrenceCancel }
     * {@link ExpandedRecurrenceInvite }
     * {@link ExpandedRecurrenceException }
     * 
     * 
     */
    public List<ExpandedRecurrenceComponent> getCancelOrCompOrExcept() {
        if (cancelOrCompOrExcept == null) {
            cancelOrCompOrExcept = new ArrayList<ExpandedRecurrenceComponent>();
        }
        return this.cancelOrCompOrExcept;
    }

    /**
     * Gets the value of the usr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the usr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsr().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FreeBusyUserSpec }
     * 
     * 
     */
    public List<FreeBusyUserSpec> getUsr() {
        if (usr == null) {
            usr = new ArrayList<FreeBusyUserSpec>();
        }
        return this.usr;
    }

    /**
     * Obtém o valor da propriedade s.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getS() {
        return s;
    }

    /**
     * Define o valor da propriedade s.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setS(Long value) {
        this.s = value;
    }

    /**
     * Obtém o valor da propriedade e.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getE() {
        return e;
    }

    /**
     * Define o valor da propriedade e.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setE(Long value) {
        this.e = value;
    }

    /**
     * Obtém o valor da propriedade all.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAll() {
        return all;
    }

    /**
     * Define o valor da propriedade all.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAll(Boolean value) {
        this.all = value;
    }

    /**
     * Obtém o valor da propriedade excludeUid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExcludeUid() {
        return excludeUid;
    }

    /**
     * Define o valor da propriedade excludeUid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExcludeUid(String value) {
        this.excludeUid = value;
    }

}
