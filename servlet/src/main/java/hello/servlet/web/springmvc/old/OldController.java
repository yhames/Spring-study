package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//0 = RequestMappingHandlerMapping : 애노테이션 기반의 컨트롤러인 @RequestMapping 사용
@Component("/springmvc/old-controller") //1 = BeanNameUrlHandlerMapping : 스프링 빈의 이름으로 핸들러를 찾는다.
public class OldController implements Controller {
    //0 = RequestMappingHandlerAdapter
    //1 = HttpRequestHandlerAdapter
    @Override   //2 = SimpleControllerHandlerAdapter
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        //2 = InternalResourceViewResolver : view resolved with prefix and suffix in application.properties
        return new ModelAndView("new-form");
    }
}
