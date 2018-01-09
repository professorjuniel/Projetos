
package zimbraadmin;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de testResultInfo complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="testResultInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="completed" type="{urn:zimbraAdmin}completedTestInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="failure" type="{urn:zimbraAdmin}failedTestInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "testResultInfo", propOrder = {
    "completed",
    "failure"
})
public class TestResultInfo {

    protected List<CompletedTestInfo> completed;
    protected List<FailedTestInfo> failure;

    /**
     * Gets the value of the completed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the completed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompleted().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CompletedTestInfo }
     * 
     * 
     */
    public List<CompletedTestInfo> getCompleted() {
        if (completed == null) {
            completed = new ArrayList<CompletedTestInfo>();
        }
        return this.completed;
    }

    /**
     * Gets the value of the failure property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the failure property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFailure().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FailedTestInfo }
     * 
     * 
     */
    public List<FailedTestInfo> getFailure() {
        if (failure == null) {
            failure = new ArrayList<FailedTestInfo>();
        }
        return this.failure;
    }

}
