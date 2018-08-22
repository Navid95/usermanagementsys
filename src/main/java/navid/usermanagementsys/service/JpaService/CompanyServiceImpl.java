package navid.usermanagementsys.service.JpaService;

import navid.usermanagementsys.domain.Company;
import navid.usermanagementsys.domain.Personnel;
import navid.usermanagementsys.domain.User;
import navid.usermanagementsys.repository.CompanyRepository;
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
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    private PersonnelService personnelService;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPersonnelService(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company findByName(String s) {
        return companyRepository.findByName(s);
    }

    @Override
    public Page<Company> listAllByPage(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @Override
    public List<?> listAll() {
        List<Company> companies = new ArrayList<>();
        companyRepository.findAll().forEach(companies :: add);
        return companies;
    }

    @Override
    public Company getById(Integer id) {
        return companyRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Company saveOrUpdate(Company domainObject) {

        List<Personnel> personnels = new ArrayList<>();
        domainObject.getPersonnels().forEach(personel -> {
            Personnel personnel1 = personnelService.findByUsername(personel.getUsername());
            if (personnel1 == null){
                System.out.println("personel not persisted yet");
                personnel1 = new Personnel();
                personnel1.setUsername(personel.getUsername());
                personnel1.setPassword(personel.getPassword());
                personnel1.setNationalId(personel.getNationalId());
                personnel1.setFirstName(personel.getFirstName());
                personnel1.setLastName(personel.getLastName());
                personnel1.setBirthDate(personel.getBirthDate());
                personnel1.setCompany(personel.getCompany());
            }
            personnels.add(personnel1);
        });

        if (domainObject.getAdmin() != null) {
            User admin = userService.findByUsername(domainObject.getAdmin().getUsername());

            if (admin == null) {
                admin = new User();
                admin.setUsername(domainObject.getAdmin().getUsername());
                admin.setPassword(domainObject.getAdmin().getPassword());
            }
            admin = userService.saveOrUpdate(admin);
            domainObject.setAdmin(admin);
        }
        else {
//            ToDo there should be an error page
        }
        domainObject.setPersonnels(personnels);
        return companyRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        companyRepository.deleteById(id);
    }
}
