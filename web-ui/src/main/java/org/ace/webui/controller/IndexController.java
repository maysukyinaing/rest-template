package org.ace.webui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"/","/index","/home"})
    public String index() {
        return "home";
    }
}
