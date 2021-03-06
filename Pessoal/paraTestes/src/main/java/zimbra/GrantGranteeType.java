
package zimbra;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de grantGranteeType.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="grantGranteeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="usr"/>
 *     &lt;enumeration value="grp"/>
 *     &lt;enumeration value="cos"/>
 *     &lt;enumeration value="pub"/>
 *     &lt;enumeration value="all"/>
 *     &lt;enumeration value="dom"/>
 *     &lt;enumeration value="guest"/>
 *     &lt;enumeration value="key"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "grantGranteeType")
@XmlEnum
public enum GrantGranteeType {

    @XmlEnumValue("usr")
    USR("usr"),
    @XmlEnumValue("grp")
    GRP("grp"),
    @XmlEnumValue("cos")
    COS("cos"),
    @XmlEnumValue("pub")
    PUB("pub"),
    @XmlEnumValue("all")
    ALL("all"),
    @XmlEnumValue("dom")
    DOM("dom"),
    @XmlEnumValue("guest")
    GUEST("guest"),
    @XmlEnumValue("key")
    KEY("key");
    private final String value;

    GrantGranteeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GrantGranteeType fromValue(String v) {
        for (GrantGranteeType c: GrantGranteeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
