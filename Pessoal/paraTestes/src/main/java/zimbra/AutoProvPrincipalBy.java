
package zimbra;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de autoProvPrincipalBy.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="autoProvPrincipalBy">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="dn"/>
 *     &lt;enumeration value="name"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "autoProvPrincipalBy")
@XmlEnum
public enum AutoProvPrincipalBy {

    @XmlEnumValue("dn")
    DN("dn"),
    @XmlEnumValue("name")
    NAME("name");
    private final String value;

    AutoProvPrincipalBy(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AutoProvPrincipalBy fromValue(String v) {
        for (AutoProvPrincipalBy c: AutoProvPrincipalBy.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
