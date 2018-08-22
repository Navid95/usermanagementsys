package navid.usermanagementsys.service.JpaService;

import navid.usermanagementsys.domain.Company;
import navid.usermanagementsys.domain.Personnel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonnelService extends CRUDService<Personnel> {

    Personnel findByUsername(String s);
    List<Personnel> findAllByCompany_Id(Integer id);

}
