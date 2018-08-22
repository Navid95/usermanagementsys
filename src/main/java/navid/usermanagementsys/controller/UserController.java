package navid.usermanagementsys.controller;

import navid.usermanagementsys.domain.User;
import navid.usermanagementsys.service.JpaService.UserService;
import navid.usermanagementsys.service.Security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/User")
public class UserController {

    private UserService userService;

    private AuthenticationService authenticationService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "listAll" , method = RequestMethod.GET)
    public String listAll(Model model , Pageable pageable){
        if (authenticationService.hasAuthority("READ_USER")) {
            model.addAttribute("users" , userService.listAllByPage(pageable));
            model.addAttribute("pageable", pageable);
            return "users";
        }
        else {
            return  "redirect:/";
        }
    }

    @RequestMapping(value = "show/{id}" , method = RequestMethod.GET)
    public String show(@PathVariable Integer id , Model model){
        if (authenticationService.hasAuthority("READ_USER")) {
            model.addAttribute("user" , userService.getById(id));
            return "userShow";
        }
        else {
            return  "redirect:/";
        }
    }

    @RequestMapping(value = "edit/{id}" , method = RequestMethod.GET)
    public String edit(@PathVariable Integer id , Model model){
        if (authenticationService.hasAuthority("UPDATE_USER")) {
            model.addAttribute("user" , userService.getById(id));
            return "userForm";
        }
        else {
            return  "redirect:/";
        }
    }

    @RequestMapping(value = "create" , method = RequestMethod.GET)
    public String create(Model model){
        if (authenticationService.hasAuthority("CREATE_USER")) {
            model.addAttribute("user" , new User());
            return "userForm";
        }
        else {
            return  "redirect:/";
        }
    }

    @RequestMapping(value = "update" , method = RequestMethod.POST)
    public String update(User user){
        System.out.println("NORMAL USER UPDATE");

        if (authenticationService.hasAuthority("UPDATE_USER") ||
                authenticationService.hasAuthority("CREATE_USER")) {
            userService.saveOrUpdate(user);
            return "redirect:/User/show/" + user.getId();
        }
        else {
            return  "redirect:/";
        }
    }

    @RequestMapping(value = "update" , method = RequestMethod.POST , produces = "application/json")
    public User updateJson(@RequestBody User user){
        System.out.println("JSON USER UPDATE");
        if (authenticationService.hasAuthority("UPDATE_USER") ||
                authenticationService.hasAuthority("CREATE_USER")) {
            return userService.saveOrUpdate(user);
        }
        else {
            return  null;
        }
    }
}
