
package zimbra;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de searchSortBy.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="searchSortBy">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="dateDesc"/>
 *     &lt;enumeration value="dateAsc"/>
 *     &lt;enumeration value="subjDesc"/>
 *     &lt;enumeration value="subjAsc"/>
 *     &lt;enumeration value="nameDesc"/>
 *     &lt;enumeration value="nameAsc"/>
 *     &lt;enumeration value="durDesc"/>
 *     &lt;enumeration value="durAsc"/>
 *     &lt;enumeration value="none"/>
 *     &lt;enumeration value="taskDueAsc"/>
 *     &lt;enumeration value="taskDueDesc"/>
 *     &lt;enumeration value="taskStatusAsc"/>
 *     &lt;enumeration value="taskStatusDesc"/>
 *     &lt;enumeration value="taskPercCompletedAsc"/>
 *     &lt;enumeration value="taskPercCompletedDesc"/>
 *     &lt;enumeration value="rcptAsc"/>
 *     &lt;enumeration value="rcptDesc"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "searchSortBy")
@XmlEnum
public enum SearchSortBy {

    @XmlEnumValue("dateDesc")
    DATE_DESC("dateDesc"),
    @XmlEnumValue("dateAsc")
    DATE_ASC("dateAsc"),
    @XmlEnumValue("subjDesc")
    SUBJ_DESC("subjDesc"),
    @XmlEnumValue("subjAsc")
    SUBJ_ASC("subjAsc"),
    @XmlEnumValue("nameDesc")
    NAME_DESC("nameDesc"),
    @XmlEnumValue("nameAsc")
    NAME_ASC("nameAsc"),
    @XmlEnumValue("durDesc")
    DUR_DESC("durDesc"),
    @XmlEnumValue("durAsc")
    DUR_ASC("durAsc"),
    @XmlEnumValue("none")
    NONE("none"),
    @XmlEnumValue("taskDueAsc")
    TASK_DUE_ASC("taskDueAsc"),
    @XmlEnumValue("taskDueDesc")
    TASK_DUE_DESC("taskDueDesc"),
    @XmlEnumValue("taskStatusAsc")
    TASK_STATUS_ASC("taskStatusAsc"),
    @XmlEnumValue("taskStatusDesc")
    TASK_STATUS_DESC("taskStatusDesc"),
    @XmlEnumValue("taskPercCompletedAsc")
    TASK_PERC_COMPLETED_ASC("taskPercCompletedAsc"),
    @XmlEnumValue("taskPercCompletedDesc")
    TASK_PERC_COMPLETED_DESC("taskPercCompletedDesc"),
    @XmlEnumValue("rcptAsc")
    RCPT_ASC("rcptAsc"),
    @XmlEnumValue("rcptDesc")
    RCPT_DESC("rcptDesc");
    private final String value;

    SearchSortBy(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SearchSortBy fromValue(String v) {
        for (SearchSortBy c: SearchSortBy.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
