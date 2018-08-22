package navid.usermanagementsys.service.Security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public boolean hasAuthority( String s) {
        Authentication authentication;
        authentication = getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(s))){
            return true;
        }
        else {
            return false;
        }
    }

}
