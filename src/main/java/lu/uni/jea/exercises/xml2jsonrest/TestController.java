package lu.uni.jea.exercises.xml2jsonrest;

import com.fasterxml.jackson.core.JsonProcessingException;
import lu.uni.jea.exercises.xml2jsonrest.dtos.RootElement;
import lu.uni.jea.exercises.xml2jsonrest.services.IXML2JSONService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    private static final Logger logger = Logger.getLogger ( TestController.class );

    private RootElement createdRootElement;

    private String returnJSON;
    private String filter;
    private String searchedYears;

    private List<String> searchedYearsList;

    @Autowired
    private IXML2JSONService XML2JSONService;

    @RequestMapping(value="/")
    public String index() {
        return "Greetings from Spring Boot, Java version!";
    }

    @RequestMapping(value={"/exercise5", "/exercise5/{filter}"}, produces="application/json")
    public String getReturnFullJSON(@PathVariable(required = false) String filter)
            throws JsonProcessingException {

        RootElement deserializedData = new RootElement();

        // Deserialize XML file
        deserializedData = XML2JSONService.deserializeFromXML();

        // Process filter

        if(filter == null) {
            returnJSON = XML2JSONService.returnJson(deserializedData);
        } else {

            searchedYearsList = new ArrayList<String>();

            this.setSearchedYears(filter);

            // Check if dash separated
            String strDash[] = searchedYears.split("-");
            logger.info("Dash separated : " + strDash.length);

            if (strDash.length > 1) {
                logger.info("Dash separated");
                int start = Integer.parseInt(strDash[0]);
                int end = Integer.parseInt(strDash[1]);

                for (int i = start; i <= end; i++ ) {
                    logger.info("Year : " + i);
                    searchedYearsList.add(String.valueOf(i));
                }

            } else {
                logger.info("Not dash separated");
                searchedYearsList.add(searchedYears);
            }

            createdRootElement = XML2JSONService.createRootElement(deserializedData, searchedYearsList);
            returnJSON = XML2JSONService.returnJson(createdRootElement);

        }

        return returnJSON;
    }

    // Getters and setters

    public String getSearchedYears() {
        return searchedYears;
    }

    public void setSearchedYears(String searchedYears) {
        this.searchedYears = searchedYears;
    }
}
