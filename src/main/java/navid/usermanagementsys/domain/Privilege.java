package navid.usermanagementsys.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Privilege extends AbstractDomainClass {

    @Column(unique = true)
    private String objectName;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id")
//    private List<Permission> permissions = new ArrayList<>();

//    ********************************** METHODS *******************************************


    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

//    public List<Permission> getPermissions() {
//        return permissions;
//    }
//
//    public void setPermissions(List<Permission> permissions) {
//        this.permissions = permissions;
//    }
//
//    public void addPermission(Permission permission){
//        if (! this.permissions.contains(permission)){
//            this.permissions.add(permission);
//        }
//    }
//
//    public void deletePermission(Permission permission){
//        this.permissions.remove(permission);
//    }


}
