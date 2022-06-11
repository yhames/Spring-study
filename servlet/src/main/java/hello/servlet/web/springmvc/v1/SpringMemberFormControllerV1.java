package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@Component
//@RequestMapping
/**
 * @Controller
 * 1. 자동으로 스프링 빈 등록 = @Component -> @Bean 방식으로 대체 가능
 * 2. 애노테이션 기반 컨트롤러로 인식(using RequestMappingHandlerMapping) = @RequestMapping
 */
@Controller
public class SpringMemberFormControllerV1 {
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process(){
        return new ModelAndView("new-form");
    }
}
