
package zimbraadmin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de uploadProxyCAResponse complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="uploadProxyCAResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="cert_content" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uploadProxyCAResponse")
public class UploadProxyCAResponse {

    @XmlAttribute(name = "cert_content")
    protected String certContent;

    /**
     * Obtém o valor da propriedade certContent.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertContent() {
        return certContent;
    }

    /**
     * Define o valor da propriedade certContent.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertContent(String value) {
        this.certContent = value;
    }

}
