package navid.usermanagementsys.repository;

import navid.usermanagementsys.domain.Company;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CompanyRepository extends PagingAndSortingRepository<Company , Integer> {
    Company findByName(String s);
}
