package navid.usermanagementsys.service.JpaService;

import navid.usermanagementsys.domain.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService extends CRUDService<Company> {
    Company findByName(String s);
    Page<Company> listAllByPage(Pageable pageable);
}
