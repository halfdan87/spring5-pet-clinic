package guru.springframework.sfgpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping({"", "index", "index.html"})
    public String index() {
        return "index";
    }


    @RequestMapping("/oups")
    public  String oups() {
        return "notimplemented";
    }
}