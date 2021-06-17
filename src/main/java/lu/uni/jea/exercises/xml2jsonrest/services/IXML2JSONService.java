package lu.uni.jea.exercises.xml2jsonrest.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import lu.uni.jea.exercises.xml2jsonrest.dtos.RootElement;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 5 - XML2JSON REST API
 *
 */

public interface IXML2JSONService {
    public RootElement deserializeFromXML();
    public String returnJson(RootElement deserializedData) throws JsonProcessingException;
    public void debug(RootElement deserializedData);
    public RootElement createRootElement(RootElement deserializedData, List<String> searchedYearsList);
    public HashMap<String, String> cellHeaders();
}
