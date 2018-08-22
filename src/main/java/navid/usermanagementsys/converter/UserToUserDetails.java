package navid.usermanagementsys.converter;

import navid.usermanagementsys.domain.Permission;
import navid.usermanagementsys.domain.Role;
import navid.usermanagementsys.domain.User;
import navid.usermanagementsys.service.Security.UserDetailsImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserToUserDetails implements Converter<User, UserDetails> {

    @Override
    public UserDetails convert(User user) {
        UserDetailsImpl userDetails = new UserDetailsImpl();

        if (user != null) {
            userDetails.setUsername(user.getUsername());
            userDetails.setPassword(user.getEncryptedPassword());
            userDetails.setEnabled(true);
            userDetails.setAuthorities((List<SimpleGrantedAuthority>) getAuthorities(user.getRoles()));
        }

        return userDetails;
    }

    private List<? extends GrantedAuthority> getAuthorities(
            List<Role> roles) {

        return getGrantedAuthorities(getPermissions(roles));
    }

    private List<String> getPermissions(List<Role> roles) {

        List<String> permissions = new ArrayList<>();
        List<Permission> collection = new ArrayList<>();
        for (Role role : roles) {
            collection.addAll(role.getPermissions());
        }
        for (Permission item : collection) {
            permissions.add(item.getName());
        }
        return permissions;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> permissions) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }
        return authorities;
    }
}
