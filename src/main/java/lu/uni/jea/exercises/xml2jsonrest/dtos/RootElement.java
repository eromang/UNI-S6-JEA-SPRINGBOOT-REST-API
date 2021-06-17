package lu.uni.jea.exercises.xml2jsonrest.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.apache.log4j.Logger;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 5 - XML2JSON REST API
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true) // Ignore properties not defined in the POJO
@JacksonXmlRootElement(localName = "CubeView") // We consider CubeView as the root element
public class RootElement {

    private static final Logger logger = Logger.getLogger ( RootElement.class );

    // Define XML property for attribute lang
    @JacksonXmlProperty(isAttribute = true)
    @JsonProperty("language")
    private String lang;

    // Define XML property for element Data
    @JacksonXmlProperty(localName = "Data")
    @JsonProperty("months_data")
    private MonthsData monthsData;

    // Empty Constructor

    public RootElement() {
        reset();
    }

    // Constructor

    public RootElement(String lang, MonthsData monthsData) {
        this.setLang(lang);
        this.setMonthsData(monthsData);
    }

    public void reset() {
        this.setLang(null);
        this.setMonthsData(null);
    }

    //Other getters and setters

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public MonthsData getMonthsData() {
        return monthsData;
    }

    public void setMonthsData(MonthsData monthsData) {
        this.monthsData = monthsData;
    }

}