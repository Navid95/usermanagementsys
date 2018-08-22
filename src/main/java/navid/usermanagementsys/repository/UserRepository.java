package navid.usermanagementsys.repository;

import navid.usermanagementsys.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.Nullable;

public interface UserRepository extends PagingAndSortingRepository<User , Integer> {

//    @Nullable
    User findByUsername( String username);
}
