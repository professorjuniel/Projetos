//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.19 at 08:13:21 PM BRT 
//


package carnepagcontas.bb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.tibco.com/schemas/bws_registro_cbr/Recursos/XSD/Schema.xsd}requisicao"/>
 *         &lt;element ref="{http://www.tibco.com/schemas/bws_registro_cbr/Recursos/XSD/Schema.xsd}resposta"/>
 *         &lt;element ref="{http://www.tibco.com/schemas/bws_registro_cbr/Recursos/XSD/Schema.xsd}erro"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "requisicao",
    "resposta",
    "erro"
})
@XmlRootElement(name = "root")
public class Root {

    @XmlElement(required = true)
    protected Requisicao requisicao;
    @XmlElement(required = true)
    protected Resposta resposta;
    @XmlElement(required = true)
    protected Erro erro;

    /**
     * Gets the value of the requisicao property.
     * 
     * @return
     *     possible object is
     *     {@link Requisicao }
     *     
     */
    public Requisicao getRequisicao() {
        return requisicao;
    }

    /**
     * Sets the value of the requisicao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Requisicao }
     *     
     */
    public void setRequisicao(Requisicao value) {
        this.requisicao = value;
    }

    /**
     * Gets the value of the resposta property.
     * 
     * @return
     *     possible object is
     *     {@link Resposta }
     *     
     */
    public Resposta getResposta() {
        return resposta;
    }

    /**
     * Sets the value of the resposta property.
     * 
     * @param value
     *     allowed object is
     *     {@link Resposta }
     *     
     */
    public void setResposta(Resposta value) {
        this.resposta = value;
    }

    /**
     * Gets the value of the erro property.
     * 
     * @return
     *     possible object is
     *     {@link Erro }
     *     
     */
    public Erro getErro() {
        return erro;
    }

    /**
     * Sets the value of the erro property.
     * 
     * @param value
     *     allowed object is
     *     {@link Erro }
     *     
     */
    public void setErro(Erro value) {
        this.erro = value;
    }

}
