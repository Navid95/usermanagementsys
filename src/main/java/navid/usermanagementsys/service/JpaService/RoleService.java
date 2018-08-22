package navid.usermanagementsys.service.JpaService;

import navid.usermanagementsys.domain.Role;

public interface RoleService extends CRUDService<Role> {

    Role findByRoleName(String s);
}
