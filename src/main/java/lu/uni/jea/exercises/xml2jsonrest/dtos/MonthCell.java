package lu.uni.jea.exercises.xml2jsonrest.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.apache.log4j.Logger;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 5 - XML2JSON REST API
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
//@JacksonXmlRootElement(localName = "C") // We consider C as the root element
public class MonthCell {

    private static final Logger logger = Logger.getLogger ( MonthCell.class );

    // Define XML property for attribute lang
    @JacksonXmlProperty(localName = "headers", isAttribute = true)
    //@JsonProperty("month_data_type")
    private String cellHeader;

    @JacksonXmlProperty(localName = "v", isAttribute = true)
    //@JsonProperty("month_data_value")
    private double cellValue;

    @JsonProperty("variation")
    private double variation;

    // Empty constructor

    public MonthCell() {}

    // Constructor

    public MonthCell(String cellHeader, double cellValue) {
        this.setCellHeader(cellHeader);
        this.setCellValue(cellValue);
    }

    public MonthCell(String cellHeader, double cellValue, double variation) {
        this.setCellHeader(cellHeader);
        this.setCellValue(cellValue);
        this.setVariation(variation);
    }

    public void reset() {
        this.setCellHeader(null);
        this.setCellValue(0);
    }

    // Getters and Setters

    public String getCellHeader() {
        return cellHeader;
    }

    public void setCellHeader(String cellHeader) {
        this.cellHeader = cellHeader;
    }

    public double getCellValue() {
        return cellValue;
    }

    public void setCellValue(double cellValue) {
        this.cellValue = cellValue;
    }

    public double getVariation() {
        return variation;
    }

    public void setVariation(double variation) {
        this.variation = variation;
    }
}
