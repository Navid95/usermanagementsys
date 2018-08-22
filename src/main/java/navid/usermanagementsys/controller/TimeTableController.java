package navid.usermanagementsys.controller;

import navid.usermanagementsys.domain.TimeTable;
import navid.usermanagementsys.service.JpaService.CRUDService;
import navid.usermanagementsys.service.JpaService.TimeTableService;
import navid.usermanagementsys.service.Security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "timeTable")
public class TimeTableController extends CRUD_REST_Controller<TimeTable> {

}
