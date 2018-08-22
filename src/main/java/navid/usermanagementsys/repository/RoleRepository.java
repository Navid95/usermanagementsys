package navid.usermanagementsys.repository;

import navid.usermanagementsys.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role ,Integer> {

    Role findByRoleName(String s);
}
