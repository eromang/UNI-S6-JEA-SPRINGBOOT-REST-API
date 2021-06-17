package lu.uni.jea.exercises.xml2jsonrest.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import org.apache.log4j.Logger;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 5 - XML2JSON REST API
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
//@JacksonXmlRootElement(localName = "RowLabel")
public class MonthLabel {

    private static final Logger logger = Logger.getLogger ( MonthLabel.class );

    @JacksonXmlProperty(isAttribute = true)
    @JsonProperty("month_id")
    private String id;

    @JacksonXmlText
    @JsonProperty("month_date")
    private String monthLabelValue;

    // Empty constructor

    public MonthLabel() {
        reset();
    }

    // Constructor

    public MonthLabel(String id, String monthLabelValue) {
        this.setId(id);
        this.setMonthLabelValue(monthLabelValue);
    }

    public void reset() {
        this.setId(null);
        this.setMonthLabelValue(null);
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public String getMonthLabelValue() {
        return monthLabelValue;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMonthLabelValue(String monthLabelValue) {
        this.monthLabelValue = monthLabelValue;
    }
}