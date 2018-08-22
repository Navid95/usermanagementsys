package navid.usermanagementsys.repository;

import navid.usermanagementsys.domain.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PermissionRepository extends PagingAndSortingRepository<Permission , Integer> {
    Permission findPermissionByName(String name);
}
