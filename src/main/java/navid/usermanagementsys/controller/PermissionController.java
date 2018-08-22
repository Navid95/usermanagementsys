package navid.usermanagementsys.controller;

import navid.usermanagementsys.domain.Permission;
import navid.usermanagementsys.service.JpaService.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PermissionController {

    private PermissionService permissionService;

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @RequestMapping(value = "permissionlistAll" , method = RequestMethod.GET)
    public String listAll(Model model){
        model.addAttribute("permissions" , permissionService.listAll());
        return "permissions";
    }

    @RequestMapping(value = "permissionlistAll" , method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public List<Permission> listAll() {
        return (List<Permission>) permissionService.listAll();
    }

    @RequestMapping(value = "permissionShow{id}" , method = RequestMethod.GET)
    public String Show(@PathVariable Integer id , Model model){
        model.addAttribute("permission" , permissionService.getById(id));
        return "permissionShow";
    }
}
