package navid.usermanagementsys.service.JpaService;

import navid.usermanagementsys.domain.Gym;
import navid.usermanagementsys.domain.Saloon;
import navid.usermanagementsys.domain.User;
import navid.usermanagementsys.repository.GymRepository;
import navid.usermanagementsys.repository.UserRepository;
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
public class GymServiceImpl implements GymService {

    private GymRepository gymRepository;

    private UserService userService;

    private SaloonService saloonService;

    @Autowired
    public void setSaloonService(SaloonService saloonService) {
        this.saloonService = saloonService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setGymRepository(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    @Override
    public List<?> listAll() {
        List<Gym> gyms = new ArrayList<>();
        gymRepository.findAll().forEach(gyms ::add);
        return gyms;
    }

    @Override
    public Gym getById(Integer id) {
        return gymRepository.findById(id).get();
    }


    @Override
    public Page<Gym> listAllByPage(Pageable pageable) {
        return gymRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Gym saveOrUpdate(Gym domainObject) {

        List<Saloon> saloons = new ArrayList<>();
        User admin = userService.findByUsername(domainObject.getAdmin().getUsername());
        if (admin == null){
            admin = new User();
            admin.setUsername(domainObject.getAdmin().getUsername());
            admin.setPassword(domainObject.getAdmin().getPassword());
        }
        admin = userService.saveOrUpdate(admin);

        if (domainObject.getSaloons() != null)
        domainObject.getSaloons().forEach(saloon -> {
            Saloon saloon1 = saloonService.findBySaloonNameAndGym(saloon.getSaloonName() , domainObject);
            if (saloon1 == null){
                System.err.println("Saloon not persisted yet !!!");
                saloon1 = new Saloon();
                saloon1.setTimeTables(saloon.getTimeTables());
                saloon1.setGym(domainObject);
                saloon1.setSaloonName(saloon.getSaloonName());
                saloon1.setCapacity(30);
            }
            System.err.println("Saloon persisted before !!!");
            saloons.add(saloon1);
        });

        domainObject.setAdmin(admin);
        domainObject.setSaloons(saloons);
        return gymRepository.save(domainObject);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        gymRepository.deleteById(id);
    }

    @Override
    public Gym findByName(String name) {
        return gymRepository.findByName(name);
    }
}
