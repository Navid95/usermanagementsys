package navid.usermanagementsys.service.JpaService;

import navid.usermanagementsys.domain.Role;
import navid.usermanagementsys.domain.User;
import navid.usermanagementsys.repository.RoleRepository;
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
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Page<Role> listAllByPage(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public List<?> listAll() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles ::add);
        return roles;
    }

    @Override
    public Role getById(Integer id) {
        return roleRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Role saveOrUpdate(Role domainObject) {

//        List<User> users = new ArrayList<>();
//        domainObject.getUsers().forEach(user -> {
//            if (userService.findByUsername(user.getUsername())==null) {
//                User user1 = new User();
//                user1.setUsername(user.getUsername());
//                user1.setPassword(user.getPassword());
//                user1.setEnabled(user.isEnabled());
//                user1.setEncryptedPassword(user.getEncryptedPassword());
//                user1.setRoles(user.getRoles());
//                users.add(userService.saveOrUpdate(user1));
//                System.out.println(user1.getUsername());
//            }else {
//                System.out.println(user.getUsername());
//                users.add(user);
//            }
//        });
//        domainObject.setUsers(users);
        return roleRepository.save(domainObject);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role findByRoleName(String s) {
        return roleRepository.findByRoleName(s);
    }
}
