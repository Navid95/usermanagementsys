package navid.usermanagementsys.service.JpaService;

import navid.usermanagementsys.domain.Permission;

public interface PermissionService extends CRUDService<Permission> {

    Permission findByName(String name);

}
