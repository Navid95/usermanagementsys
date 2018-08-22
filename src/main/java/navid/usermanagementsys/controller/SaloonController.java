package navid.usermanagementsys.controller;

import navid.usermanagementsys.domain.Saloon;
import navid.usermanagementsys.service.JpaService.SaloonService;
import navid.usermanagementsys.service.Security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "saloon")
public class SaloonController extends CRUD_REST_Controller<Saloon> {


}
