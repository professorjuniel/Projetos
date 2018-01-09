
package zimbramail;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de dismissCalendarItemAlarmResponse complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="dismissCalendarItemAlarmResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="appt" type="{urn:zimbraMail}updatedAppointmentAlarmInfo"/>
 *           &lt;element name="task" type="{urn:zimbraMail}updatedTaskAlarmInfo"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dismissCalendarItemAlarmResponse", propOrder = {
    "apptOrTask"
})
public class DismissCalendarItemAlarmResponse {

    @XmlElements({
        @XmlElement(name = "appt", type = UpdatedAppointmentAlarmInfo.class),
        @XmlElement(name = "task", type = UpdatedTaskAlarmInfo.class)
    })
    protected List<UpdatedAlarmInfo> apptOrTask;

    /**
     * Gets the value of the apptOrTask property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the apptOrTask property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApptOrTask().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UpdatedAppointmentAlarmInfo }
     * {@link UpdatedTaskAlarmInfo }
     * 
     * 
     */
    public List<UpdatedAlarmInfo> getApptOrTask() {
        if (apptOrTask == null) {
            apptOrTask = new ArrayList<UpdatedAlarmInfo>();
        }
        return this.apptOrTask;
    }

}
