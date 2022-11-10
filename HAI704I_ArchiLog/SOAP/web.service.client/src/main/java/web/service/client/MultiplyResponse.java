
package web.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MultiplyResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "multiplyResult"
})
@XmlRootElement(name = "MultiplyResponse")
public class MultiplyResponse {

    @XmlElement(name = "MultiplyResult")
    protected int multiplyResult;

    /**
     * Obtient la valeur de la propriété multiplyResult.
     * 
     */
    public int getMultiplyResult() {
        return multiplyResult;
    }

    /**
     * Définit la valeur de la propriété multiplyResult.
     * 
     */
    public void setMultiplyResult(int value) {
        this.multiplyResult = value;
    }

}
