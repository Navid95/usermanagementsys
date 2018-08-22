package navid.usermanagementsys.service.JpaService;

import navid.usermanagementsys.domain.User;
import navid.usermanagementsys.repository.PersonnelRepository;
import navid.usermanagementsys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

//    private PersonnelService personelService;

    private PersonnelRepository personnelRepository;

    private RoleService roleService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setPersonnelRepository(PersonnelRepository personnelRepository) {
        this.personnelRepository = personnelRepository;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

//    @Autowired
//    public void setPersonnelService(PersonnelService personelService) {
//        this.personelService = personelService;
//    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
//    @Nullable
    public User findByUsername(String username) {
            return userRepository.findByUsername(username);
    }

    @Override
    public Page<User> listAllByPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<?> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional
    public User saveOrUpdate(User domainObject) {
        if(domainObject.getPassword() != null){
            domainObject.setEncryptedPassword(bCryptPasswordEncoder.encode(domainObject.getPassword()));
        }
//        try {
//            Personnel personnel;
//            personnel = personnelRepository.findById(domainObject.getId()).get();
//            if (personnel != null){
//                System.out.println("its a personnel");
//                User user = personnel;
//                personnelRepository.save(personnel);
//                return userRepository.save(user);
//            }
//        }catch (Exception e){}

        return userRepository.save(domainObject);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
