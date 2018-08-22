package navid.usermanagementsys.repository;

import navid.usermanagementsys.domain.Company;
import navid.usermanagementsys.domain.Personnel;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PersonnelRepository extends PagingAndSortingRepository<Personnel, Integer> {
    Personnel findByUsername(String s);
    List<Personnel> findAllByCompany_Id(Integer id);

}
