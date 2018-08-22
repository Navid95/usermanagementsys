package navid.usermanagementsys.service.JpaService;

import navid.usermanagementsys.domain.Permission;
import navid.usermanagementsys.repository.PermissionRepository;
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
public class PermissionServiceImpl implements PermissionService {

    @Override
    public Page<Permission> listAllByPage(Pageable pageable) {
        return permissionRepository.findAll(pageable);
    }

    private PermissionRepository permissionRepository;

    @Autowired
    public void setPermissionRepository(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<?> listAll() {
        List<Permission> permissions = new ArrayList<>();
        permissionRepository.findAll().forEach(permissions::add);
        return permissions;
    }

    @Override
    public Permission getById(Integer id) {
        return permissionRepository.findById(id).get();
    }

    @Override
    public Permission saveOrUpdate(Permission domainObject) {
        return permissionRepository.save(domainObject);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public Permission findByName(String name) {
        return permissionRepository.findPermissionByName(name);
    }
}
