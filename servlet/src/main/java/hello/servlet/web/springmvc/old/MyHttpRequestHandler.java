package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//0 = RequestMappingHandlerMapping : 애노테이션 기반의 컨트롤러인 @RequestMapping 사용
@Component("/springmvc/request-handler")    //1 = BeanNameUrlHandlerMapping : 스프링 빈의 이름으로 핸들러를 찾는다.
public class MyHttpRequestHandler implements HttpRequestHandler {
    //0 = RequestMappingHandlerAdapter
    @Override   //1 = HttpRequestHandlerAdapter
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequestHandler.handleRequest");
    }
}
