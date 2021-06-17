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
        return "Eric ROMANG - Exercise 5";
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

            // Do we have a dash ?
            if (strDash.length > 1) {

                logger.info("Dash separated");

                String start = strDash[0];
                String end = strDash[1];

                String myStartYear = start.substring(0, 4);
                Integer myStartIntYear = Integer.parseInt(start.substring(0, 4));
                Integer myStartIntMonth = Integer.parseInt(start.substring(4, 6));
                logger.info("myStartYear: " + myStartYear + " " + myStartIntYear + ", myStartIntMonth: " + myStartIntMonth);

                String myEndYear = end.substring(0, 4);
                Integer myEndIntYear = Integer.parseInt(end.substring(0, 4));
                Integer myEndIntMonth = Integer.parseInt(end.substring(4, 6));
                logger.info("myEndYear: " + myEndYear + " " + myEndIntYear + ", myEndIntMonth: " + myEndIntMonth);

                // Loop in the years

                for (Integer y = myStartIntYear; y <= myEndIntYear; y++) {

                    logger.info("--------------------");
                    logger.info("myStartIntYear: " + y + ", myEndIntYear: " + myEndIntYear);

                    // If in the start year, start at the start month
                    if (y.equals(myStartIntYear)) {
                        logger.info("In the start year");
                        for (Integer m = myStartIntMonth; m <= 12; m++) {

                            String myStringMonth = XML2JSONService.MonthUnitToText().get(m);
                            searchedYears = myStringMonth + " " + myStartYear;
                            logger.info("searchedYears in myStartIntYear: " + searchedYears);
                            searchedYearsList.add(searchedYears);
                        }
                    }
                    // If in the end year, end at the end month
                    if (y.equals(myEndIntYear)) {
                        logger.info("In the end year");
                        for (Integer m = 1; m <= myEndIntMonth; m++) {

                            String myStringMonth = XML2JSONService.MonthUnitToText().get(m);
                            searchedYears = myStringMonth + " " + myEndYear;
                            logger.info("searchedYears in myEndIntYear: " + searchedYears);
                            searchedYearsList.add(searchedYears);
                        }
                    }

                    // Other cases
                    if ((y.intValue() != myStartIntYear.intValue()) && (y.intValue() != myEndIntYear.intValue())){

                        logger.info("y: " + y + ", myStartIntYear: " + myStartIntYear + ", myEndIntYear: " + myEndIntYear);

                        for (Integer m = 1; m <= 12; m++) {

                            String myStringMonth = XML2JSONService.MonthUnitToText().get(m);
                            searchedYears = myStringMonth + " " + y;
                            logger.info("searchedYears for in the middle: " + searchedYears);
                            searchedYearsList.add(searchedYears);
                        }
                    }
                }
            } else {
                // Not dash separated format YYYYMM
                if (filter.length() > 4) {
                    logger.info("Not dash separated format YYYYMM");

                    String myYear = filter.substring(0, 4);
                    int myIntMonth = Integer.parseInt(filter.substring(4, 6));

                    String myStringMonth = XML2JSONService.MonthUnitToText().get(myIntMonth);

                    searchedYears = myStringMonth + " " + myYear;

                    searchedYearsList.add(searchedYears);
                } else {
                    // Not dash separated format YYYY
                    logger.info("Not dash separated format YYYY");
                    searchedYearsList.add(searchedYears);
                }
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
