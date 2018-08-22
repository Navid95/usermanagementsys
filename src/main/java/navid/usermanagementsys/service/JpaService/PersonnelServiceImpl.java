package navid.usermanagementsys.service.JpaService;

import navid.usermanagementsys.domain.Company;
import navid.usermanagementsys.domain.Personnel;
import navid.usermanagementsys.repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class PersonnelServiceImpl implements PersonnelService {

    private PersonnelRepository personnelRepository;

    private UserService userService;

    private CompanyService companyService;

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPersonnelRepository(PersonnelRepository personnelRepository) {
        this.personnelRepository = personnelRepository;
    }

    @Override
    public Personnel findByUsername(String s) {
        return personnelRepository.findByUsername(s);
    }

    @Override
    public List<Personnel> findAllByCompany_Id(Integer id) {
        return personnelRepository.findAllByCompany_Id(id);
    }

    @Override
    public Page<Personnel> listAllByPage(Pageable pageable) {
        return personnelRepository.findAll(pageable);
    }

    @Override
    public List<?> listAll() {
        List<Personnel> personnels = new ArrayList<>();
        personnelRepository.findAll().forEach(personnels:: add);
        return personnels;
    }

    @Override
    public Personnel getById(Integer id) {
        return personnelRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Personnel saveOrUpdate(Personnel domainObject) {
        userService.saveOrUpdate(domainObject);
        return personnelRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        personnelRepository.deleteById(id);
    }
}
