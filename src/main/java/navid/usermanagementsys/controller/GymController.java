package navid.usermanagementsys.controller;

import navid.usermanagementsys.domain.Gym;
import navid.usermanagementsys.domain.Saloon;
import navid.usermanagementsys.service.JpaService.GymService;
import navid.usermanagementsys.service.Security.AuthenticationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping(value = "gym")
public class GymController extends CRUD_REST_Controller<Gym> {

    private GymService gymService;

    private AuthenticationService authenticationService;

    private Logger logger = LogManager.getLogger(GymController.class);

    private RestTemplate restTemplate;

    @Autowired
    public GymController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Autowired
    public void setGymService(GymService gymService) {
        this.gymService = gymService;
    }

    @RequestMapping(value = "gymNew")
    public String create(Model model){
        model.addAttribute("gym" , new Gym());
        return "gymForm";
    }

    @RequestMapping(value = "gymlistAll" , method = RequestMethod.GET)
    public String listAll(Model model){
        model.addAttribute("gyms" , gymService.listAll());
        return "gyms";
    }

    @RequestMapping(value = "gymShow{id}" , method = RequestMethod.GET)
    public String show(@PathVariable Integer id , Model model){
        model.addAttribute("gym" , gymService.getById(id));
        return "gymShow";
    }

    @RequestMapping(value = "gymUpdate" , method = RequestMethod.POST)
    public String update(Gym gym){
        gymService.saveOrUpdate(gym);
        return "redirect:/gymShow"+ gym.getId();
    }

    @RequestMapping(value = "gymEdit{id}")
    public String edit(@PathVariable Integer id , Model model){
        model.addAttribute("gym" , gymService.getById(id));
        return "gymForm";
    }

    @RequestMapping(value = "gymDelete{id}")
    public String delete(@PathVariable Integer id){
        gymService.delete(id);
        return "redirect:/gymlistAll";
    }

//    ****************************************** REST METHODS **************************************

//    @RequestMapping(value = "addSaloon/{id}" , method = RequestMethod.POST , produces = "application/json")
//    public Gym addSaloon(@PathVariable Integer id , @RequestBody Saloon saloon){
//
//        String createSaloonURL = "http://localhost:8080/saloon/show/99?format=json";
//        String updateSaloonURL = "http://localhost:8080/saloon/update?format=json";
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//
//        httpHeaders.add("Authorization" , String.valueOf(authenticationService.getAuthentication()));
//        logger.info("Authentication : "+String.valueOf(authenticationService.getAuthentication()));
//
//        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
//        ResponseEntity<Saloon>  responseEntity = restTemplate.exchange(createSaloonURL , HttpMethod.GET , httpEntity , Saloon.class);
//
//        Saloon saloon1 = responseEntity.getBody();
//
//        logger.info("new Salood added to gym with id : "+id+"\n"+saloon1);
//        return gymService.getById(id);
//    }
}
