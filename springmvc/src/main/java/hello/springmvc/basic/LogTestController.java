package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // return String as http message body instead of viewName
@Slf4j
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());   // replaced by @Slf4j

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        //You must follow the appointed log format "{logLevel} log={}"
        log.trace("trace log={}", name);    // application.properties configuration needed, root default "info"
        log.debug("debug log={}", name);    // application.properties configuration needed
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        return "OK";    // message Body
    }
}
