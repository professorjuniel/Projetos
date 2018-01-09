
package zimbraadmin;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de queueAction.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="queueAction">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="hold"/>
 *     &lt;enumeration value="release"/>
 *     &lt;enumeration value="delete"/>
 *     &lt;enumeration value="requeue"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "queueAction")
@XmlEnum
public enum QueueAction {

    @XmlEnumValue("hold")
    HOLD("hold"),
    @XmlEnumValue("release")
    RELEASE("release"),
    @XmlEnumValue("delete")
    DELETE("delete"),
    @XmlEnumValue("requeue")
    REQUEUE("requeue");
    private final String value;

    QueueAction(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static QueueAction fromValue(String v) {
        for (QueueAction c: QueueAction.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
