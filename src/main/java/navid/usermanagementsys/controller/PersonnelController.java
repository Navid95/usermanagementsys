package navid.usermanagementsys.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import navid.usermanagementsys.domain.Company;
import navid.usermanagementsys.domain.Personnel;
import navid.usermanagementsys.domain.User;
import navid.usermanagementsys.service.JpaService.CompanyService;
import navid.usermanagementsys.service.JpaService.PersonnelService;
import navid.usermanagementsys.service.Security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping(value = "/Personnel")
public class PersonnelController {

    private PersonnelService personnelService;

    private CompanyService companyService;

    private AuthenticationService authenticationService;

    @Autowired
    public void setPersonnelService(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    public String listAll(Model model, Pageable pageable) {
        if (authenticationService.hasAuthority("READ_PERSONNEL")) {
            model.addAttribute("personnels", personnelService.listAllByPage(pageable));
            model.addAttribute("pageable", pageable);
            return "personnels";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "listAll/company/{id}", method = RequestMethod.GET)
    public String listAllByCompany(Model model, @PathVariable Integer id) {
        if (authenticationService.hasAuthority("READ_PERSONNEL")) {
            model.addAttribute("personnels", personnelService.findAllByCompany_Id(id));
            model.addAttribute("company_id", id);
            return "personals";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "listAll/company/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Personnel> listAllByCompany(@PathVariable Integer id) {
        if (authenticationService.hasAuthority("READ_PERSONNEL")) {
            return personnelService.findAllByCompany_Id(id);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Integer id, Model model) {
        if (authenticationService.hasAuthority("READ_PERSONNEL")) {
            model.addAttribute("personnel", personnelService.getById(id));
            return "personnelShow";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "show/{id}", method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public Personnel show(@PathVariable Integer id) {
        if (authenticationService.hasAuthority("READ_PERSONNEL")) {
            return personnelService.getById(id);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model) {
        if (authenticationService.hasAuthority("UPDATE_PERSONNEL")) {
            model.addAttribute("personnel", personnelService.getById(id));
            return "personnelForm";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        if (authenticationService.hasAuthority("CREATE_PERSONNEL")) {
            model.addAttribute("personnel", new Personnel());
            return "personnelForm";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(Model model, Personnel personnel , BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            model.addAttribute("errors" , bindingResult.getAllErrors());
            return "error";
        }
        System.out.println("update personnel");
        if (authenticationService.hasAuthority("UPDATE_PERSONNEL") ||
                authenticationService.hasAuthority("CREATE_PERSONNEL")) {
            personnelService.saveOrUpdate(personnel);
            return "redirect:/Personnel/show/" + personnel.getId();
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Personnel updateJson(@RequestBody Personnel personnel) {
        if (authenticationService.hasAuthority("UPDATE_PERSONNEL") ||
                authenticationService.hasAuthority("CREATE_PERSONNEL")) {
            System.out.println(personnel);
            System.out.println(personnel.getCompany().getPersonnels());
            System.out.println(personnel.getCompany());
//            personnel = personnelService.saveOrUpdate(personnel);
            personnel = personnelService.saveOrUpdate(personnel);
            Company company = companyService.saveOrUpdate(personnel.getCompany());
            return personnel;
        } else {
            return null;
        }
    }
}
