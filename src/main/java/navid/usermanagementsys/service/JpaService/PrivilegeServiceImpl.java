package navid.usermanagementsys.service.JpaService;

import navid.usermanagementsys.domain.Privilege;
import navid.usermanagementsys.repository.PrivilegeRepository;
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
public class PrivilegeServiceImpl implements PrivilegeService {

    private PrivilegeRepository privilegeRepository;

    @Autowired
    public void setPrivilegeRepository(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public List<?> listAll() {
        List<Privilege> privileges = new ArrayList<>();
        privilegeRepository.findAll().forEach(privileges::add);
        return privileges;
    }

    @Override
    public Page<Privilege> listAllByPage(Pageable pageable) {
        return null;
    }

    @Override
    public Privilege getById(Integer id) {
        return privilegeRepository.findById(id).get();
    }

    @Override
    public Privilege saveOrUpdate(Privilege domainObject) {
        return privilegeRepository.save(domainObject);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        privilegeRepository.deleteById(id);
    }
}
