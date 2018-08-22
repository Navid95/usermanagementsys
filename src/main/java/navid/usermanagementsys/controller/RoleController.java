package navid.usermanagementsys.controller;

import navid.usermanagementsys.domain.Role;
import navid.usermanagementsys.service.JpaService.RoleService;
import navid.usermanagementsys.service.Security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoleController {

    private RoleService roleService;

    private AuthenticationService authenticationService;

    @Autowired
    public void setAuthentication(AuthenticationService authentication) {
        this.authenticationService = authentication;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "rolelistAll" , method = RequestMethod.GET)
    public String listAll(Model model){
        if (authenticationService.hasAuthority("READ_ROLE")){
        model.addAttribute("roles" , roleService.listAll());
        return "roles";
        }
        return  "redirect:/";
    }

    @RequestMapping(value = "rolelistAll" , method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public List<Role> listAll(){
        if (authenticationService.hasAuthority("READ_ROLE")){
        return (List<Role>) roleService.listAll();
        }
        return  null;
    }


    @RequestMapping(value = "roleShow{id}")
    public String show(@PathVariable Integer id, Model model){
        if (authenticationService.hasAuthority("READ_ROLE")) {
            model.addAttribute("role", roleService.getById(id));
            return "roleShow";
        }
        else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "roleShow{id}" , method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public Role show(@PathVariable Integer id){
        if (authenticationService.hasAuthority("READ_ROLE")) {
            return roleService.getById(id);
        }
        else {
            return null;
        }
    }

    @RequestMapping(value = "roleUpdate" , method = RequestMethod.POST)
    public String update(Role role){
        if (authenticationService.hasAuthority("UPDATE_ROLE") ||
                authenticationService.hasAuthority("CREATE_ROLE")) {
            roleService.saveOrUpdate(role);
            return "redirect:/roleShow" + role.getId();
        }
        else {
            return  "redirect:/";
        }
    }

    @RequestMapping(value = "roleUpdate" , method = RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public Role updateJson(@RequestBody Role role){
        if (authenticationService.hasAuthority("UPDATE_ROLE") ||
                authenticationService.hasAuthority("CREATE_ROLE")) {
            role = roleService.saveOrUpdate(role);
            return role;
        }
        else {
            return  null;
        }
    }

    @RequestMapping(value = "roleNew" , method = RequestMethod.GET)
    public String createNewRole(Model model){
        if (authenticationService.hasAuthority("CREATE_ROLE")){
            model.addAttribute("role" ,new Role());
            return "roleForm";
        }
        else {
            return  "redirect:/";
        }
    }

    @RequestMapping(value = "roleNew" , method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public Role createNewRole(@RequestBody Role role){
        if (authenticationService.hasAuthority("CREATE_ROLE")){
            return updateJson(role);
        }
        else {
            return  null;
        }
    }

    @RequestMapping(value = "roleEdit{id}" , method = RequestMethod.GET)
    public String editRole(@PathVariable Integer id , Model model){
        if (authenticationService.hasAuthority("UPDATE_ROLE")) {
            model.addAttribute("role", roleService.getById(id));
            return "roleForm";
        }
        else {
            return  "redirect:/";
        }
    }

    @RequestMapping(value = "roleEdit{id}" , method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public Role editRole(@PathVariable Integer id , @RequestBody Role role){
        if (authenticationService.hasAuthority("UPDATE_ROLE")) {
            role.setId(id);
            return updateJson(role);
        }
        else {
            return  null;
        }
    }

    @RequestMapping(value = "roleDelete{id}")
    public String deleteRole(@PathVariable Integer id){
        if (authenticationService.hasAuthority("DELETE_ROLE")){
            roleService.delete(id);
            return "redirect:/rolelistAll";
        }
        else {
            return  "redirect:/";
        }
    }

    @RequestMapping(value = "roleDelete{id}" , produces = "application/json")
    @ResponseBody
    public boolean deleteRoleJson(@PathVariable Integer id){
        if (authenticationService.hasAuthority("DELETE_ROLE")){
            roleService.delete(id);
            return true;
        }
        else {
            return  false;
        }
    }

}
