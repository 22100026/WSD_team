package org.example.hw3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/view/list")
    public String viewList(Model model){
        return "redirect:/list";
    }

    @RequestMapping("/edit/list")
    public String editList(Model model){
        return "redirect:/list";
    }

}


