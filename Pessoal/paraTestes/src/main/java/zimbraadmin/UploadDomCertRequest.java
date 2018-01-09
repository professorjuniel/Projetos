
package zimbraadmin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de uploadDomCertRequest complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="uploadDomCertRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="cert.aid" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="cert.filename" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="key.aid" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="key.filename" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uploadDomCertRequest")
public class UploadDomCertRequest {

    @XmlAttribute(name = "cert.aid", required = true)
    protected String certAid;
    @XmlAttribute(name = "cert.filename", required = true)
    protected String certFilename;
    @XmlAttribute(name = "key.aid", required = true)
    protected String keyAid;
    @XmlAttribute(name = "key.filename", required = true)
    protected String keyFilename;

    /**
     * Obtém o valor da propriedade certAid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertAid() {
        return certAid;
    }

    /**
     * Define o valor da propriedade certAid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertAid(String value) {
        this.certAid = value;
    }

    /**
     * Obtém o valor da propriedade certFilename.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertFilename() {
        return certFilename;
    }

    /**
     * Define o valor da propriedade certFilename.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertFilename(String value) {
        this.certFilename = value;
    }

    /**
     * Obtém o valor da propriedade keyAid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeyAid() {
        return keyAid;
    }

    /**
     * Define o valor da propriedade keyAid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeyAid(String value) {
        this.keyAid = value;
    }

    /**
     * Obtém o valor da propriedade keyFilename.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeyFilename() {
        return keyFilename;
    }

    /**
     * Define o valor da propriedade keyFilename.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeyFilename(String value) {
        this.keyFilename = value;
    }

}
