package lu.uni.jea.exercises.xml2jsonrest.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.apache.log4j.Logger;

import java.util.List;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 5 - XML2JSON REST API
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
//@JacksonXmlRootElement(localName = "Row") // We consider Row as the root element
public class Months {

    private static final Logger logger = Logger.getLogger ( Months.class );

    // Define XML property for element RowLabels
    @JacksonXmlProperty(localName = "RowLabels")
    @JsonProperty("month_labels")
    private MonthLabels monthLabels;

    @JacksonXmlElementWrapper(localName = "Cells")
    @JacksonXmlProperty(localName = "C")
    @JsonProperty("month_data")
    private List<MonthCell> monthCell;

    // Default constructor
    public Months() {
        reset();
    }

    // Constructor
    public Months(MonthLabels monthLabels, List<MonthCell> monthCell) {
        this.setMonthLabels(monthLabels);
        this.setMonthCell(monthCell);
    }

    public void reset() {
        this.setMonthLabels(null);
        this.setMonthCell(null);
    }

    // Getters and Setters

    public MonthLabels getMonthLabels() {
        return monthLabels;
    }

    public void setMonthLabels(MonthLabels monthLabels) {
        this.monthLabels = monthLabels;
    }

    public List<MonthCell> getMonthCell() {
        return monthCell;
    }

    public void setMonthCell(List<MonthCell> monthCell) {
        this.monthCell = monthCell;
    }
}
