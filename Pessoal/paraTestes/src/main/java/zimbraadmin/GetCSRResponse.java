
package zimbraadmin;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de getCSRResponse complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="getCSRResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="C" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ST" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="L" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="O" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubjectAltName" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="csr_exists" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="isComm" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="server" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCSRResponse", propOrder = {
    "c",
    "st",
    "l",
    "o",
    "ou",
    "cn",
    "subjectAltName"
})
public class GetCSRResponse {

    @XmlElement(name = "C")
    protected String c;
    @XmlElement(name = "ST")
    protected String st;
    @XmlElement(name = "L")
    protected String l;
    @XmlElement(name = "O")
    protected String o;
    @XmlElement(name = "OU")
    protected String ou;
    @XmlElement(name = "CN")
    protected String cn;
    @XmlElement(name = "SubjectAltName")
    protected List<String> subjectAltName;
    @XmlAttribute(name = "csr_exists", required = true)
    protected String csrExists;
    @XmlAttribute(name = "isComm", required = true)
    protected String isComm;
    @XmlAttribute(name = "server", required = true)
    protected String server;

    /**
     * Obtém o valor da propriedade c.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getC() {
        return c;
    }

    /**
     * Define o valor da propriedade c.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setC(String value) {
        this.c = value;
    }

    /**
     * Obtém o valor da propriedade st.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getST() {
        return st;
    }

    /**
     * Define o valor da propriedade st.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setST(String value) {
        this.st = value;
    }

    /**
     * Obtém o valor da propriedade l.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getL() {
        return l;
    }

    /**
     * Define o valor da propriedade l.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setL(String value) {
        this.l = value;
    }

    /**
     * Obtém o valor da propriedade o.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getO() {
        return o;
    }

    /**
     * Define o valor da propriedade o.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setO(String value) {
        this.o = value;
    }

    /**
     * Obtém o valor da propriedade ou.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOU() {
        return ou;
    }

    /**
     * Define o valor da propriedade ou.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOU(String value) {
        this.ou = value;
    }

    /**
     * Obtém o valor da propriedade cn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCN() {
        return cn;
    }

    /**
     * Define o valor da propriedade cn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCN(String value) {
        this.cn = value;
    }

    /**
     * Gets the value of the subjectAltName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subjectAltName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubjectAltName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSubjectAltName() {
        if (subjectAltName == null) {
            subjectAltName = new ArrayList<String>();
        }
        return this.subjectAltName;
    }

    /**
     * Obtém o valor da propriedade csrExists.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCsrExists() {
        return csrExists;
    }

    /**
     * Define o valor da propriedade csrExists.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCsrExists(String value) {
        this.csrExists = value;
    }

    /**
     * Obtém o valor da propriedade isComm.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsComm() {
        return isComm;
    }

    /**
     * Define o valor da propriedade isComm.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsComm(String value) {
        this.isComm = value;
    }

    /**
     * Obtém o valor da propriedade server.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServer() {
        return server;
    }

    /**
     * Define o valor da propriedade server.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServer(String value) {
        this.server = value;
    }

}
