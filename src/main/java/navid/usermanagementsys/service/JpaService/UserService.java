package navid.usermanagementsys.service.JpaService;

import navid.usermanagementsys.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

//@Service
public interface UserService extends CRUDService<User> {

//    @Nullable
    User findByUsername(String username);
}
