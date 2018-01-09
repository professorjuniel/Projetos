
package zimbraadmin;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de authScheme.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="authScheme">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="basic"/>
 *     &lt;enumeration value="form"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "authScheme")
@XmlEnum
public enum AuthScheme {

    @XmlEnumValue("basic")
    BASIC("basic"),
    @XmlEnumValue("form")
    FORM("form");
    private final String value;

    AuthScheme(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AuthScheme fromValue(String v) {
        for (AuthScheme c: AuthScheme.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
