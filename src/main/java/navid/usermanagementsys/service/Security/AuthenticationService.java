package navid.usermanagementsys.service.Security;

import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    Authentication getAuthentication();

    boolean hasAuthority(String s);
}
