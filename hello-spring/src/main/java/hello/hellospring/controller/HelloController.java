package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/* @ is a prefix of annotation */
@Controller    // declare as a controller
public class HelloController {

    @GetMapping("hello")    // receive GET Request for "hello"
    public String hello(Model model){
        model.addAttribute("data", "spring!!");
        return "hello";    // templates/hello.html
    }

    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam("name") String name, Model model) {    // default : required = true
        model.addAttribute("name", name);
        return "hello-template";
    }
}