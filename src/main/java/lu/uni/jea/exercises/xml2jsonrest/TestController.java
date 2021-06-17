package lu.uni.jea.exercises.xml2jsonrest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot, Java version!";
    }
}
