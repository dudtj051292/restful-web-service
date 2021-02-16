package com.suh.restfulwebservice.ctrl;

import com.suh.restfulwebservice.bean.HelloWorldBean;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {
     //Get
    // /hello-world - > endpoint
    //@RequestMapping(path = "/hello-world",method = RequestMethod.GET)
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }


    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

}
