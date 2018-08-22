package navid.usermanagementsys.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Role extends AbstractDomainClass {

    @Column(unique = true)
    private String roleName;

    @ManyToMany(fetch = FetchType.EAGER , mappedBy = "roles")
//    @JsonManagedReference(value = "users")
    private List<User> users = new ArrayList<>();

    @ManyToMany
    @Fetch(FetchMode.SELECT)
    private List<Permission> permissions = new ArrayList<>();

//    ********************************** METHODS *******************************************

    public Role(String s){}

    public Role(){}

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        users.forEach(user -> {
            if (! user.getRoles().contains(this)){
                user.addRole(this);
            }
        });
    }

    public void addUser(User user){
        if (! this.users.contains(user)){
            this.users.add(user);
        }
        if (! user.getRoles().contains(this)){
            user.getRoles().add(this);
        }
    }

    public void removeUser(User user){
        this.users.remove(user);
        user.getRoles().remove(this);
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public void addPermission(Permission permission){
        if (! this.permissions.contains(permission)){
            this.permissions.add(permission);
        }
    }

    public void deletePermission(Permission permission){
        this.permissions.remove(permission);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
