
package zimbraadmin;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de domainBy.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="domainBy">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="id"/>
 *     &lt;enumeration value="name"/>
 *     &lt;enumeration value="virtualHostname"/>
 *     &lt;enumeration value="krb5Realm"/>
 *     &lt;enumeration value="foreignName"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "domainBy")
@XmlEnum
public enum DomainBy {

    @XmlEnumValue("id")
    ID("id"),
    @XmlEnumValue("name")
    NAME("name"),
    @XmlEnumValue("virtualHostname")
    VIRTUAL_HOSTNAME("virtualHostname"),
    @XmlEnumValue("krb5Realm")
    KRB_5_REALM("krb5Realm"),
    @XmlEnumValue("foreignName")
    FOREIGN_NAME("foreignName");
    private final String value;

    DomainBy(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DomainBy fromValue(String v) {
        for (DomainBy c: DomainBy.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
