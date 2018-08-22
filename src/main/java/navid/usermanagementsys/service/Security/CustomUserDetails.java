package navid.usermanagementsys.service.Security;

import navid.usermanagementsys.domain.User;
import navid.usermanagementsys.service.JpaService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userDetailsService")
@Transactional
public class CustomUserDetails implements UserDetailsService {

    private UserService userService;

    private Converter<User,UserDetails> converter;

    @Autowired
    @Qualifier(value = "userToUserDetails")
    public void setConverter(Converter<User, UserDetails> converter) {
        this.converter = converter;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return converter.convert(userService.findByUsername(s));
    }

//    private List<? extends GrantedAuthority> getAuthorities(
//            List<Role> roles) {
//
//        return getGrantedAuthorities(getPermissions(roles));
//    }
//
//    private List<String> getPermissions(List<Role> roles) {
//
//        List<String> permissions = new ArrayList<>();
//        List<Permission> collection = new ArrayList<>();
//        for (Role role : roles) {
//            collection.addAll(role.getPermissions());
//        }
//        for (Permission item : collection) {
//            permissions.add(item.getName());
//        }
//        return permissions;
//    }
//
//    private List<GrantedAuthority> getGrantedAuthorities(List<String> permissions) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (String permission : permissions) {
//            authorities.add(new SimpleGrantedAuthority(permission));
//        }
//        return authorities;
//    }
}
