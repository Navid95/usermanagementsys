package navid.usermanagementsys.controller;

import navid.usermanagementsys.domain.Company;
import navid.usermanagementsys.service.JpaService.CompanyService;
import navid.usermanagementsys.service.Security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/company")
public class CompanyController  {

    private CompanyService companyService;

    private AuthenticationService authenticationService;

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "listAll" , method = RequestMethod.GET)
    public String listAll(Model model , Pageable pageable){
        if (authenticationService.hasAuthority("READ_COMPANY")) {
            model.addAttribute("companies", companyService.listAllByPage(pageable));
            model.addAttribute("pageable", pageable);
            return "companies";
        }
        else {
            return  "redirect:/";
        }
    }

    @RequestMapping(value = "listAll" , method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public Page<Company> listAll(Pageable pageable){
        return companyService.listAllByPage(pageable);
    }


    @RequestMapping(value = "show/{id}" , method = RequestMethod.GET)
    public String show(@PathVariable Integer id , Model model){
        if (authenticationService.hasAuthority("READ_COMPANY")) {
            model.addAttribute("company", companyService.getById(id));
            return "organizationInfo";
        }
        else {
            return  "redirect:/";
        }
    }

    @RequestMapping(value = "show/{id}" , method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public Company show(@PathVariable Integer id){
        return companyService.getById(id);
    }

    @RequestMapping(value = "update" , method = RequestMethod.POST)
    public String update(Company company){
        if (authenticationService.hasAuthority("UPDATE_COMPANY")) {
            companyService.saveOrUpdate(company);
            return "redirect:/company/show/" + company.getId();
        }
        else {
            return  "redirect:/";
        }
    }

    @RequestMapping(value = "update" , method = RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public Company updateJson(@RequestBody Company company){
        return companyService.saveOrUpdate(company);
    }

    @RequestMapping(value = "edit/{id}" , method = RequestMethod.GET)
    public String edit(@PathVariable Integer id , Model model){
        if (authenticationService.hasAuthority("UPDATE_COMPANY")) {
            model.addAttribute("company" , companyService.getById(id));
            return "organizationInfo";
        }
        else {
            return  "redirect:/";
        }
    }

    @RequestMapping(value = "edit" , method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public Company edit(@RequestBody Company company){
        if (authenticationService.hasAuthority("UPDATE_COMPANY")) {
//            company.setId(id);
            return companyService.saveOrUpdate(company);
        }
        else {
            return  null;
        }
    }

    @RequestMapping(value = "create" , method = RequestMethod.GET)
    public String create(Model model){
        if (authenticationService.hasAuthority("CREATE_COMPANY")) {
            model.addAttribute("company" , new Company());
            return "organizationInfo";
        }
        else {
            return  "redirect:/";
        }
    }

    @RequestMapping(value = "create" , method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public Company create(@RequestBody Company company){
        if (authenticationService.hasAuthority("CREATE_COMPANY")) {
            return companyService.saveOrUpdate(company);
        }
        else {
            return  null;
        }
    }

    @RequestMapping(value = "delete/{id}" , method = RequestMethod.GET)
    public String delete(@PathVariable Integer id){
        companyService.delete(id);
        return "companies";
    }

    @RequestMapping(value = "delete/{id}" , method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public void deleteJson(@PathVariable Integer id){
        companyService.delete(id);
    }
}