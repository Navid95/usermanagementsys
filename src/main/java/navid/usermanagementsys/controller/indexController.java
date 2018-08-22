package navid.usermanagementsys.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class indexController {

    @RequestMapping("/")
    public String HelloWorld(Model model) {
//        Locale.setDefault(Locale.FRANCE);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name" , authentication.getName());
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "index";
    }
}
